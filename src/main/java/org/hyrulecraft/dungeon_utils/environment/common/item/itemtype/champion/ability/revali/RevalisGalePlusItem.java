package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.champion.ability.revali;

import net.minecraft.block.*;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.config.DungeonUtilsConfig;

import org.jetbrains.annotations.NotNull;

public class RevalisGalePlusItem extends RevalisGaleItem {

    public RevalisGalePlusItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, @NotNull Hand hand) {
        ItemStack stack = user.getMainHandStack();
        BlockState blockState = world.getBlockState(user.getBlockPos().offset(Direction.DOWN, 1));

        if (!stack.hasNbt() && !user.getItemCooldownManager().isCoolingDown(this) && !blockState.isOf(Blocks.AIR)) {

            user.setVelocity(0, DungeonUtilsConfig.revalisGaleHeight, 0);
            if (DungeonUtilsConfig.shouldAddSlowFalling) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20, 0));
            }
            addSecondUsage(user);
            return TypedActionResult.consume(stack);

        } else if (stack.getNbt() != null && stack.getNbt().contains("dungeon_utils.revalis_gale.usage_two") && !user.getItemCooldownManager().isCoolingDown(this) && !blockState.isOf(Blocks.AIR)) {

            user.setVelocity(0, DungeonUtilsConfig.revalisGaleHeight, 0);
            if (DungeonUtilsConfig.shouldAddSlowFalling) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20, 0));
            }
            addThirdUsage(user);
            return TypedActionResult.consume(stack);

        } else if (stack.getNbt() != null && stack.getNbt().contains("dungeon_utils.revalis_gale.usage_three") && !user.getItemCooldownManager().isCoolingDown(this) && !blockState.isOf(Blocks.AIR)) {

            user.setVelocity(0, DungeonUtilsConfig.revalisGaleHeight, 0);
            if (DungeonUtilsConfig.shouldAddSlowFalling) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20, 0));
            }
            user.getItemCooldownManager().set(this, ( 20 * 60 ) * 2);
            addAntiSpam(user);
            return TypedActionResult.consume(stack);

        } else if (user.getItemCooldownManager().isCoolingDown(this)) {

            return TypedActionResult.fail(stack);

        } else {

            return TypedActionResult.fail(stack); // Can't have a runtime exception here anymore due to the nature of the if statement logic.

        }
    }
}