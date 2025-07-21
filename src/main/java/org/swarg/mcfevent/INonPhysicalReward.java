package org.swarg.mcfevent;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

/**
 * 21-07-2025
 * @author Swarg
 */
public interface INonPhysicalReward {

    /**
     */
    public ItemStack give(EntityPlayerMP player, ItemStack itemStack);
}
