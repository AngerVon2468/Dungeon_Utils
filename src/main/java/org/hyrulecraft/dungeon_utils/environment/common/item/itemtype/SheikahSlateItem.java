package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype;

import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.client.journeymap.DungeonUtilsJourneymapPlugin;

import org.jetbrains.annotations.NotNull;

public class SheikahSlateItem extends Item {

    public SheikahSlateItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, @NotNull Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (world.isClient()) {

            MinecraftClient client = MinecraftClient.getInstance();
            double maxReach = 1000.0f; // The farthest target the cameraEntity can detect
            float tickDelta = 1.0f; // Used for tracking animation progress; no tracking is 1.0f
            boolean includeFluids = false; // Whether to detect fluids as blocks

            HitResult hit = client.cameraEntity.raycast(maxReach, tickDelta, includeFluids);

            switch(hit.getType()) {
                case MISS:

                    // nothing near enough

                    break;
                case BLOCK:

                    BlockHitResult blockHit = (BlockHitResult) hit;
                    BlockPos blockPos = blockHit.getBlockPos();;

                    if (DungeonUtilsJourneymapPlugin.getInstance() != null && FabricLoader.getInstance().isModLoaded("journeymap")) {
                        DungeonUtilsJourneymapPlugin.getInstance().createWaypoint(blockPos, user.getWorld().getRegistryKey());
                    }
                    break;
            }

        }

        return TypedActionResult.fail(stack);
    }
}