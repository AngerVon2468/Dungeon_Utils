package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

public class StaminaContainerItem extends Item {

    public StaminaContainerItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, PlayerEntity user, Hand hand) {

        if (!world.isClient()) {

            ItemStack stack = user.getMainHandStack();
            user.increaseMaxStamina(1.0f);
            stack.decrement(1);
            return TypedActionResult.consume(stack);

        } else {

            return TypedActionResult.consume(user.getMainHandStack());

        }
    }
}