package org.hyrulecraft.dungeon_utils.item.itemtype;

import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.config.DungeonUtilsConfig;
import org.hyrulecraft.dungeon_utils.tags.DungeonUtilsTags;
import org.hyrulecraft.dungeon_utils.util.DirectionCheckUtil;

import org.jetbrains.annotations.NotNull;

public class HookshotItem extends Item {

    public HookshotItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, @NotNull Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        MinecraftClient client = MinecraftClient.getInstance();
        double maxReach = DungeonUtilsConfig.hookShotRange; // The farthest target the cameraEntity can detect
        float tickDelta = 1.0F; // Used for tracking animation progress; no tracking is 1.0F
        boolean includeFluids = false; // Whether to detect fluids as blocks

        HitResult hit = client.cameraEntity.raycast(maxReach, tickDelta, includeFluids);

        switch(hit.getType()) {
            case MISS:

                // nothing near enough

                break;
            case BLOCK:

                BlockHitResult blockHit = (BlockHitResult) hit;
                BlockPos blockPos = blockHit.getBlockPos();
                BlockState blockState = world.getBlockState(blockPos);
                Vec3d playerPos = user.getBlockPos().toCenterPos();
                Vec3d hookshotBlockPos = blockHit.getBlockPos().toCenterPos();
                if (user.getHorizontalFacing() == Direction.NORTH && blockState.isIn(DungeonUtilsTags.Blocks.HOOKSHOT) && DirectionCheckUtil.caseNorth(playerPos.x, hookshotBlockPos.x, playerPos.z, hookshotBlockPos.z)) {

                    user.addVelocity(0, 0.14,-0.4);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 255));

                }
                if (user.getHorizontalFacing() == Direction.SOUTH && blockState.isIn(DungeonUtilsTags.Blocks.HOOKSHOT) && DirectionCheckUtil.caseSouth(playerPos.x, hookshotBlockPos.x, playerPos.z, hookshotBlockPos.z)) {

                    user.addVelocity(0, 0.14,0.4);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 255));

                }
                if (user.getHorizontalFacing() == Direction.EAST && blockState.isIn(DungeonUtilsTags.Blocks.HOOKSHOT) && DirectionCheckUtil.caseEast(playerPos.x, hookshotBlockPos.x, playerPos.z, hookshotBlockPos.z)) {

                    user.addVelocity(0.4, 0.14,0);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 255));

                }
                if (user.getHorizontalFacing() == Direction.WEST && blockState.isIn(DungeonUtilsTags.Blocks.HOOKSHOT) && DirectionCheckUtil.caseWest(playerPos.x, hookshotBlockPos.x, playerPos.z, hookshotBlockPos.z)) {

                    user.addVelocity(-0.4, 0.14,0);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 255));

                }

                break;
            case ENTITY:

                EntityHitResult entityHit = (EntityHitResult) hit;
                Entity entity = entityHit.getEntity();
                if (user.getHorizontalFacing() == Direction.NORTH) {

                    user.addVelocity(0, 0.14,-0.4);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 255));

                }
                if (user.getHorizontalFacing() == Direction.SOUTH) {

                    user.addVelocity(0, 0.14,0.4);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 255));

                }
                if (user.getHorizontalFacing() == Direction.EAST) {

                    user.addVelocity(0.4, 0.14,0);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 255));

                }
                if (user.getHorizontalFacing() == Direction.WEST) {

                    user.addVelocity(-0.4, 0.14,0);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 255));

                }

                break;
        }

        return TypedActionResult.consume(stack);
    }
}