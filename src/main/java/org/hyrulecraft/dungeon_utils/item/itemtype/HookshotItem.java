package org.hyrulecraft.dungeon_utils.item.itemtype;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;
import org.jetbrains.annotations.NotNull;

public class HookshotItem extends Item {

    public HookshotItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, @NotNull World world, @NotNull Entity entity, int slot, boolean selected) {
        BlockState state = world.getBlockState(entity.getBlockPos().offset(Direction.DOWN, 1));

        if (entity instanceof PlayerEntity user) {

            if ((state.isOf(Blocks.AIR) || state.isOf(Blocks.CAVE_AIR) || state.isOf(Blocks.VOID_AIR)) && user.getStackInHand(user.getActiveHand()).isOf(DungeonUtilsItems.HOOKSHOT)) {

                entity.setNoGravity(true);

            } else {

                entity.setNoGravity(false);

            }

        }

    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, @NotNull Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (user.getHorizontalFacing() == Direction.NORTH) {

            user.addVelocity(0, -0,-0.3);

        }
        if (user.getHorizontalFacing() == Direction.SOUTH) {

            user.addVelocity(0, -0,0.3);

        }
        if (user.getHorizontalFacing() == Direction.EAST) {

            user.addVelocity(0.3, -0,0);

        }
        if (user.getHorizontalFacing() == Direction.WEST) {

            user.addVelocity(-0.3, -0,0);

        }

        return TypedActionResult.consume(stack);
    }
}