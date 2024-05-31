package org.hyrulecraft.dungeon_utils.item.itemtype;

import dev.emi.trinkets.api.*;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

public class ParagliderTrinketItem extends TrinketItem {

    public ParagliderTrinketItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, @NotNull Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        BlockState blockState = world.getBlockState(user.getBlockPos().offset(Direction.DOWN, 1));

        if (blockState.isOf(Blocks.AIR)) {

            user.setNoGravity(true); // TODO: Adjust values
            if (user.getHorizontalFacing() == Direction.NORTH) {
                user.addVelocity(0, -0.02,-0.2);
            }
            if (user.getHorizontalFacing() == Direction.SOUTH) {
                user.addVelocity(0, -0.02,0.2);
            }
            if (user.getHorizontalFacing() == Direction.EAST) {
                user.addVelocity(0.2, -0.02,0);
            }
            if (user.getHorizontalFacing() == Direction.WEST) {
                user.addVelocity(-0.2, -0.02,0);
            }

        } else {
            user.setNoGravity(false);
        }
        return TypedActionResult.pass(stack);
    }
}