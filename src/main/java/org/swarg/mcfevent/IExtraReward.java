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
     */
    public ItemStack give(EntityPlayerMP player, ItemStack inputItemStack);
}
