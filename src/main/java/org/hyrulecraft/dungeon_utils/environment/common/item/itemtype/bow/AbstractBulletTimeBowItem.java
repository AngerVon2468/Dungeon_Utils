package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bow;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

public abstract class AbstractBulletTimeBowItem extends BowItem {

    public AbstractBulletTimeBowItem(Settings settings) {
        super(settings);
    }

    // This is broken, fix at some point.
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {

            // Look into /tick's code. If it doesn't work on 1.20.1, then move the mod to 1.21.

        }
    }
}