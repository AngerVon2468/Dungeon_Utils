package org.hyrulecraft.dungeon_utils.item.itemtype.champion.ability.revali;

import dev.emi.trinkets.api.TrinketItem;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.*;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.config.DungeonUtilsConfig;
import org.hyrulecraft.dungeon_utils.item.ModItems;
import org.hyrulecraft.dungeon_utils.sound.SoundInit;

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
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {

            if (!stack.hasNbt()) {

                // Why are we still here... just to suffer?

            } else if (!player.getItemCooldownManager().isCoolingDown(ModItems.REVALIS_GALE) && stack.getNbt().contains("dungeon_utils.revalis_gale.anti_spam") && stack.isOf(ModItems.REVALIS_GALE)) {

                player.playSound(SoundInit.getREVALIS_GALE_RECHARGE(), SoundCategory.PLAYERS, 1f, 1f);
                stack.removeSubNbt("dungeon_utils.revalis_gale.anti_spam");

            } else if (player.getItemCooldownManager().isCoolingDown(ModItems.REVALIS_GALE) && !stack.getNbt().contains("dungeon_utils.revalis_gale.anti_spam") && stack.isOf(ModItems.REVALIS_GALE)) {

                addAntiSpam(player);

            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, @NotNull Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        BlockState blockState = world.getBlockState(user.getBlockPos().offset(Direction.DOWN, 1));

        if (!stack.hasNbt() && !user.getItemCooldownManager().isCoolingDown(ModItems.REVALIS_GALE) && !blockState.isOf(Blocks.AIR)) {

            user.setVelocity(0, DungeonUtilsConfig.revalisGaleHeight, 0);
            if (DungeonUtilsConfig.shouldAddSlowFalling) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20, 0));
            }
            addSecondUsage(user);
            return TypedActionResult.consume(stack);

        } else if (stack.hasNbt() && stack.getNbt().contains("dungeon_utils.revalis_gale.usage_two") && !user.getItemCooldownManager().isCoolingDown(ModItems.REVALIS_GALE) && !blockState.isOf(Blocks.AIR)) {

            user.setVelocity(0, DungeonUtilsConfig.revalisGaleHeight, 0);
            if (DungeonUtilsConfig.shouldAddSlowFalling) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20, 0));
            }
            addThirdUsage(user);
            return TypedActionResult.consume(stack);

        } else if (stack.hasNbt() && stack.getNbt().contains("dungeon_utils.revalis_gale.usage_three") && !user.getItemCooldownManager().isCoolingDown(ModItems.REVALIS_GALE) && !blockState.isOf(Blocks.AIR)) {

            user.setVelocity(0, DungeonUtilsConfig.revalisGaleHeight, 0);
            if (DungeonUtilsConfig.shouldAddSlowFalling) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20, 0));
            }
            user.getItemCooldownManager().set((ModItems.REVALIS_GALE), ( 20 * 60 ) * 6);
            addAntiSpam(user);
            return TypedActionResult.consume(stack);

        } else if (user.getItemCooldownManager().isCoolingDown(ModItems.REVALIS_GALE)) {

            return TypedActionResult.fail(stack);

        } else {

            return TypedActionResult.fail(stack); // Can't have a runtime exception here anymore due to the nature of the if statement logic.

        }
    }

    public void addAntiSpam(@NotNull PlayerEntity player) {
        ItemStack stack = player.getStackInHand(player.getActiveHand());

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.revalis_gale.anti_spam", "anti_spam");

        stack.setNbt(nbtData);
    }

    public void addSecondUsage(@NotNull PlayerEntity player) {
        ItemStack stack = player.getStackInHand(player.getActiveHand());

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.revalis_gale.usage_two", "two");

        stack.setNbt(nbtData);
    }

    public void addThirdUsage(@NotNull PlayerEntity player) {
        ItemStack stack = player.getStackInHand(player.getActiveHand());

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.revalis_gale.usage_three", "three");

        stack.setNbt(nbtData);
    }
}