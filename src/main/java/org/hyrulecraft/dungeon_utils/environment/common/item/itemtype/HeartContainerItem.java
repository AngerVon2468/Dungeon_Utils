package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import virtuoel.pehkui.api.*;

public class HeartContainerItem extends Item {

    public HeartContainerItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, @NotNull Hand hand) {

        if (!world.isClient()) {

            ItemStack stack = user.getStackInHand(hand);
            ScaleData playerHealth = ScaleTypes.HEALTH.getScaleData(user);
            playerHealth.setScale(playerHealth.getScale() + 0.1f);
            stack.decrement(1);
            return TypedActionResult.consume(stack);

        } else {

            return TypedActionResult.consume(user.getStackInHand(hand));

        }
    }
}