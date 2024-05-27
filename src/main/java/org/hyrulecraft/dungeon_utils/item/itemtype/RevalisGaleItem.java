package org.hyrulecraft.dungeon_utils.item.itemtype;

import dev.emi.trinkets.api.TrinketItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.item.ModItems;

import org.jetbrains.annotations.NotNull;

public class RevalisGaleItem extends TrinketItem {

    public RevalisGaleItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, @NotNull Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!stack.hasNbt() && !user.getItemCooldownManager().isCoolingDown(ModItems.REVALIS_GALE)) {

            user.setVelocity(0, 1.5, 0);
            /*
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20 * 10, 0));
            */
            addSecondUsage(user);
            return TypedActionResult.consume(stack);

        } else if (stack.getNbt().contains /* NullPointerException is handled by the previous statement. IntelliJ just doesn't understand. */ ("dungeon_utils.revalis_gale.usage_two") && !user.getItemCooldownManager().isCoolingDown(ModItems.REVALIS_GALE)) {

            user.setVelocity(0, 1.5, 0);
            /*
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20 * 10, 0));
            */
            addThirdUsage(user);
            return TypedActionResult.consume(stack);

        } else if (stack.getNbt().contains("dungeon_utils.revalis_gale.usage_three") && !user.getItemCooldownManager().isCoolingDown(ModItems.REVALIS_GALE)) {

            user.setVelocity(0, 1.5, 0);
            /*
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20 * 10, 0));
            */
            user.getItemCooldownManager().set((ModItems.REVALIS_GALE), ( 20 * 60 ) /* The twenty times sixty should make it work in mins not seconds */ * 6 /* This six is the amount of minutes we want to set the cool down to */);
            stack.removeSubNbt("dungeon_utils.revalis_gale.usage_three");
            return TypedActionResult.consume(stack);

        } else if (user.getItemCooldownManager().isCoolingDown(ModItems.REVALIS_GALE)) {

            return TypedActionResult.fail(stack);

        } else {

            throw new RuntimeException("A fatal error occurred whilst using Revali's Gale.");

        }
    }

    /*
    private void addFirstUsage(@NotNull PlayerEntity player) {
        ItemStack stack = player.getStackInHand(player.getActiveHand());

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.revalis_gale.usage_one", "one");

        stack.setNbt(nbtData);
    }
    */ // No longer needed, but will leave here just in case.

    private void addSecondUsage(@NotNull PlayerEntity player) {
        ItemStack stack = player.getStackInHand(player.getActiveHand());

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.revalis_gale.usage_two", "two");

        stack.setNbt(nbtData);
    }

    private void addThirdUsage(@NotNull PlayerEntity player) {
        ItemStack stack = player.getStackInHand(player.getActiveHand());

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.revalis_gale.usage_three", "three");

        stack.setNbt(nbtData);
    }
}