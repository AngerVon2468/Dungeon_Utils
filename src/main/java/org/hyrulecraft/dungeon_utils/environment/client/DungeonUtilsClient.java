package org.hyrulecraft.dungeon_utils.environment.client;

import dev.emi.trinkets.api.client.*;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.*;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.environment.client.screen.overlay.RupeeOverlay;
import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;
import org.hyrulecraft.dungeon_utils.environment.common.entity.DungeonUtilsEntities;
import org.hyrulecraft.dungeon_utils.environment.client.entity.model.CrateEntityModel;
import org.hyrulecraft.dungeon_utils.environment.client.entity.renderer.CrateEntityRenderer;
import org.hyrulecraft.dungeon_utils.environment.client.entity.renderer.MasterSwordBeamEntityRenderer;
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.util.UtilCollector;
import org.hyrulecraft.lockon_port.keybinds.DungeonUtilsKeybinds;

public class DungeonUtilsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its main client class.");

        // Initializing classes.
        UtilCollector.registerAllUtilClassesInClient();
        RupeeOverlay.registerOverlay();

        // Rendering and models.
        DungeonUtilsClient.registerTrinketRenderers();
        DungeonUtilsClient.registerEntityModelLayers();
        DungeonUtilsClient.registerEntityRenderers();
        DungeonUtilsClient.registerModelPredicates();
        DungeonUtilsKeybinds.registerDungeonUtilsKeybinds();
    }

    public static void registerTrinketRenderers() {

        TrinketRendererRegistry.registerRenderer(DungeonUtilsItems.LINK_CAP, (TrinketRenderer) DungeonUtilsItems.LINK_CAP);
        TrinketRendererRegistry.registerRenderer(DungeonUtilsItems.LINK_TUNIC, (TrinketRenderer) DungeonUtilsItems.LINK_TUNIC);
    }

    public static void registerEntityModelLayers() {

        EntityModelLayerRegistry.registerModelLayer(CrateEntityModel.LAYER_LOCATION, CrateEntityModel::getTexturedModelData);
    }

    public static void registerEntityRenderers() {

        EntityRendererRegistry.register(DungeonUtilsEntities.MASTER_SWORD_BEAM, MasterSwordBeamEntityRenderer::new);
        EntityRendererRegistry.register(DungeonUtilsEntities.CRATE, CrateEntityRenderer::new);
    }

    public static void registerModelPredicates() {

        // Lynel Bow 5x
        ModelPredicateProviderRegistry.createModelPredicate(DungeonUtilsItems.LYNEL_BOW_FIVE_X, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (entity.getActiveItem() != stack) {
                return 0.0f;
            }
            return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
        });
        ModelPredicateProviderRegistry.createModelPredicate(DungeonUtilsItems.LYNEL_BOW_FIVE_X, new Identifier("pulling"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            return entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });
        // Lynel Bow 3x
        ModelPredicateProviderRegistry.createModelPredicate(DungeonUtilsItems.LYNEL_BOW_THREE_X, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (entity.getActiveItem() != stack) {
                return 0.0f;
            }
            return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
        });
        ModelPredicateProviderRegistry.createModelPredicate(DungeonUtilsItems.LYNEL_BOW_THREE_X, new Identifier("pulling"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            return entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });
        // Great Eagle Bow
        ModelPredicateProviderRegistry.createModelPredicate(DungeonUtilsItems.GREAT_EAGLE_BOW, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (entity.getActiveItem() != stack) {
                return 0.0f;
            }
            return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
        });
        ModelPredicateProviderRegistry.createModelPredicate(DungeonUtilsItems.GREAT_EAGLE_BOW, new Identifier("pulling"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            return entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });
        // Hookshot
        ModelPredicateProviderRegistry.createModelPredicate(DungeonUtilsItems.HOOKSHOT, new Identifier("launched"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            return entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });
    }
}