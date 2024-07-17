package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bow;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

public abstract class AbstractBulletTimeBowItem extends BowItem {

    public AbstractBulletTimeBowItem(Settings settings) {
        super(settings);
    }

    // This is broken, fix at some point.
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {

            /*
            BlockPos pos = player.getBlockPos().offset(Direction.DOWN, 1);
            BlockState state = world.getBlockState(pos);
            if (!player.hasNoGravity() && state.isOf(Blocks.AIR)) {
                player.setVelocity(player.getVelocity().add(0.0f, 0.08f, 0.0f));
            }
            */

        }
    }
}