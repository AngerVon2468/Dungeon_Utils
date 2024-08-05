package org.hyrulecraft.dungeon_utils.util;

import net.fabricmc.fabric.api.client.render.fluid.v1.*;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import org.jetbrains.annotations.NotNull;

public class ClientUtil {

    public static void registerPullPredicates(Item @NotNull ... items) {

        for (Item item : items) {

            ModelPredicateProviderRegistry.createModelPredicate(item, new Identifier("pull"), (stack, world, entity, seed) -> {
                if (entity == null) {
                    return 0.0f;
                }
                if (entity.getActiveItem() != stack) {
                    return 0.0f;
                }
                return (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
            });
        }
    }

    public static void registerPullingPredicates(Item @NotNull ... items) {

        for (Item item : items) {

            ModelPredicateProviderRegistry.createModelPredicate(item, new Identifier("pulling"), (stack, world, entity, seed) -> {
                if (entity == null) {
                    return 0.0f;
                }
                return entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0f : 0.0f;
            });
        }
    }

    public static void registerFluidRenderers(FluidRendererCollector @NotNull ... fluidRendererCollectors) {

        for (FluidRendererCollector fluidRendererCollector : fluidRendererCollectors) {

            FluidRenderHandlerRegistry.INSTANCE.register(fluidRendererCollector.getStillFluid(), fluidRendererCollector.getFlowingFluid(),
                    SimpleFluidRenderHandler.coloredWater(fluidRendererCollector.getColour())
            );
        }
    }
}