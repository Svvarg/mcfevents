package org.swarg.mcfevent.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.swarg.mcfevent.IExtraReward;
import static org.swarg.mcfevent.common.Log.logDebug;

/**
 * Это класс-посредник для использования в своих модов, где есть задания(квесты)
 * и награды, либо для внедрения в другие моды через патчинг (мод MCFCoreFix)
 *
 * Для поддержки работы с предметами IExtraReward,
 * которые позволяют передавать некие бонусы напрямую без предметов.
 * (Например кастомный опыт для RPG систем)
 *
 * 21-07-2025
 * @author Swarg
 */
public class RewardHooks {

    /**
     * Триггерит процесс выдачи экстро наград(выдача бонуса без самого предмета).
     * Для реализации поддержки механики ExtraRewards.
     *
     * Проверяет предмет-награду reward и если это предмет реализующий интерфейс
     * IExtraReward то выполняет выдачу кастомной награды игроку
     * (с возможным удалением самого предмета, хранящего в себе параметры награды)
     * (то есть чтобы выдать игроку некий бонус не выдавая сам предмет из
     * наградного листа выполненного квеста)
     *
     * Например для того чтобы при выполнении квеста, вместо самого предмета
     * игроку напрямую давались некие не материальные бонусы, например кастомный
     * опыт для RPG систем.
     *
     * Этот метод используется например в MCFCoreFix, для его инжекта в моды:
     * - CustomNPC: noppes.npcs.NoppesUtilServer.GivePlayerItem
     *
     */
    public static ItemStack processReward(Object issuer, EntityPlayer player, ItemStack reward) {
        logDebug("RewardHooks.handle issuer: %s player:%s reward:%s", issuer, player, reward);
        if (player == null || reward == null || !(player instanceof EntityPlayerMP)) {
            return reward;
        }
        final Item item = reward.getItem();
        ItemStack out = reward;

        if (item instanceof IExtraReward) {
            out = ((IExtraReward)item).give((EntityPlayerMP)player, reward, issuer);
            logDebug("IExtraReward.give ret:%s", out);
        }

        return out;
    }

}
