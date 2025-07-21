package org.swarg.mcfevent;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

/**
 * Для хука на событие перемещения предмета(-[контейнера] из рук блоком в мир
 * или в TileEntity или из блока в руки игрока
 * 25-06-22
 * @author Swarg
 */
@Cancelable
public class TransferItemContainerEvent extends Event {
    /* TileEntity в который добавляется предмет-контейнер
    Например в тфк это poterry стойка с сосудами-контейнерами */

    public final EntityPlayer player;

    /* Сумка - предмет контейнер способный содержать другие предметы, который
     * хотят разместить внутрь Тайла-контейнера.
     * Если bag == null а base != null и ((IInventory)base)[baseInvSlot] != null
     * то это - режим ВЗЯТИЯ сумки из TileEntity, т.к. есть тайлы в которые можно
     * размещать предметы через пкм (Например полки-витрины из BiblioCraft)
     * Расширил логику данного события в том числе и для прямого перемещения
     * самих монет! */
    public final ItemStack bag; // placeItem

    /* Тайл-контейнер в который хотят поставить сумку. Может быть и null!
     * Если сумка ставиться на пустое место с образованием нового тайла-контейнера
     * (Смотри как это сделано в тфк ItemPotteryBase) */
    public final TileEntity base;

    /* Номер слота в существующем контейнере, в который хотят разместить
     * предмет-контейнер, либо при bag==null из которого хотят забрать предмет */
    public final int baseInvSlot;


    public TransferItemContainerEvent(
        EntityPlayer player, ItemStack bag, TileEntity base, int baseInvSlot
    ) {
        this.player = player;
        this.bag = bag;
        this.base = base;
        this.baseInvSlot = baseInvSlot;
    }

   /**
    * [ServerSideOnly]
    * Запустить событие размещение сумки из инвентаря игрока в тайл
    *
    * @param player кто производит действие
    * @param item предмет либо сумка-предмет который может в себе хранить другие
    *             предметы который хотят поставить в тайл-контейнер
    * @param baseContainer тайл-контейнер в который можно ставить сумки
    * @param baseInvSlot номер слота в который хотят разместить сумку
    * @return true - отменить действие размещения сумки в тайле
    */
    public static boolean firePlaceBag(
        EntityPlayer player, ItemStack item, TileEntity baseContainer, int baseInvSlot
    ) {
        if (player != null && !player.worldObj.isRemote && item != null) {
            TransferItemContainerEvent event = new TransferItemContainerEvent(
                player, item, baseContainer, baseInvSlot
            );
            final boolean canceled = MinecraftForge.EVENT_BUS.post(event);
            return canceled;
        }
        return false; // NotCanceled!
    }

   /**
    * [ServerSideOnly]
    * Запустить обработку события взятия из тайла-контейнера сумки-контейнера
    * Будет срабатывать только для предметов-сумок с нбт!
    * @param player
    * @param baseContainer
    * @param baseInvSlot
    * @return true- запретить забирать сумку из блока для данного игрока
    */
    public static boolean fireTakeBag(
        EntityPlayer player, TileEntity baseContainer, int baseInvSlot
    ) {
        if (player != null && !player.worldObj.isRemote
            && isInvHasItem(baseContainer, baseInvSlot)
        ) {
            TransferItemContainerEvent event = new TransferItemContainerEvent(
                player, null, baseContainer, baseInvSlot
            );
            final boolean canceled = MinecraftForge.EVENT_BUS.post(event);
            return canceled;
        }
        return false; // NotCanceled!
    }

    /**
     * Проверка на то что тайл является инвентарём и имеет некий предмет с нбт
     * в slot`e
     * @param te проверяемый тайл
     * @param slot номер проверяемого на наличеи сумки слота
     * @return
     */
    public static boolean isInvHasBag(TileEntity te, int slot)
    {
        if (slot > -1 && te != null && te instanceof IInventory) {
            final IInventory inv = (IInventory)te;
            if (slot < inv.getSizeInventory()) {
                final ItemStack is = inv.getStackInSlot(slot);
                if (is != null && is.stackTagCompound != null) {
                    //XNBT.getNBTTagCompoundMap(nbt)
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверка наличия предмета в указанном слоте тайла-инвентаря
     * isTileInventoryAndHasItemInSLot
     * @param te
     * @param slot
     * @return
     */
    public static boolean isInvHasItem(TileEntity te, int slot)
    {
        if (slot > -1 && te != null && te instanceof IInventory) {
            final IInventory inv = (IInventory)te;
            if (slot < inv.getSizeInventory()) {
                final ItemStack is = inv.getStackInSlot(slot);
                return is != null && is.stackSize > 0;
            }
        }
        return false;
    }
}
