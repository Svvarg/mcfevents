package org.swarg.mcfevent;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

/**
 * Для возможности создавать награды(за квесты) в виде неких свойств и действий
 * с игроком а не в виде обычных предметов.
 * Например для выдачи свободных пунктов опыта для RPG систем, которые внутри
 * мода на квесты выглядят как обычный предмет (сфера опыта), а при выдаче
 * превращаются в очки передаваемые игроку напрямую без предмета.
 * Больше деталей смотри в ./doc/integrations/extra-reward.md
 *
 * По задумке если после вызова методов give вместо inputItemStack возвращается
 * null тогда считается что награда уже выдана внутри метода give и сам предмет
 * inputItemStack выдавать игроку не нужно.
 *
 * 21-07-2025
 * @author Swarg
 */
public interface IExtraReward {

    /**
     * player - кто получает награду
     * reward - предмет к выдаче если это IExtraReward то до выдачи будет вызван
     *         метод IExtraReward.give и то что он вернёт(может быть null)
     *         и будет выдано игрку в виде предмета(остальные бонусы внутри give)
     * issuer - тот кто выдаёт награду (Entity или TileEntity либо что-то еще
     * Например в модах на квесты это может быть NPC выдающий награду)
     *
     * return null - если не нужно выдавать сам предмет reward
     *        WARN если выдача идёт из негоко списка награды за задание то
     *        возращать в своём моде на квесты следует копию от инстанса reward
     *        (Например так сделано в моде CustomNPC)
     */
    public ItemStack give(EntityPlayerMP player, ItemStack reward, Object issuer);

}
