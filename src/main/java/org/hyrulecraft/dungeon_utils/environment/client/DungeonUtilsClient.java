package org.hyrulecraft.dungeon_utils.environment.client;

import dev.emi.trinkets.api.client.*;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.*;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.environment.client.block.PedestalBlockEntityRenderer;
import org.hyrulecraft.dungeon_utils.environment.client.entity.model.*;
import org.hyrulecraft.dungeon_utils.environment.client.screen.overlay.DungeonUtilsOverlays;
import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;
import org.hyrulecraft.dungeon_utils.environment.common.block.*;
import org.hyrulecraft.dungeon_utils.environment.common.entity.DungeonUtilsEntities;
import org.hyrulecraft.dungeon_utils.environment.client.entity.renderer.*;
import org.hyrulecraft.dungeon_utils.environment.common.fluid.DungeonUtilsFluids;
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.util.*;

public class DungeonUtilsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its main client class.");

        // Initializing classes.
        UtilCollector.registerAllUtilClassesInClient();
        DungeonUtilsOverlays.registerOverlays();

        // Rendering, predicates and models.
        this.registerTrinketRenderers();
        this.registerEntityModelLayers();
        this.registerEntityRenderers();
        this.registerModelPredicates();
        this.registerBlockRenderLayers();
        ClientUtil.registerFluidRenderers(
                FluidRendererCollector.of(DungeonUtilsFluids.STILL_SPRING_WATER, DungeonUtilsFluids.FLOWING_SPRING_WATER, 0x00e7ff),
                FluidRendererCollector.of(DungeonUtilsFluids.STILL_HOT_SPRING_WATER, DungeonUtilsFluids.FLOWING_HOT_SPRING_WATER, 0x00e7ff)
        );
    }

    public void registerBlockRenderLayers() {

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                DungeonUtilsBlocks.CLOVER_PATCH,
                DungeonUtilsBlocks.BOMB_FLOWER
        );
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                DungeonUtilsFluids.STILL_SPRING_WATER,
                DungeonUtilsFluids.STILL_HOT_SPRING_WATER,
                DungeonUtilsFluids.FLOWING_SPRING_WATER,
                DungeonUtilsFluids.FLOWING_HOT_SPRING_WATER
        );
    }

    public void registerTrinketRenderers() {

        TrinketRendererRegistry.registerRenderer(DungeonUtilsItems.LINK_CAP, (TrinketRenderer) DungeonUtilsItems.LINK_CAP);
        TrinketRendererRegistry.registerRenderer(DungeonUtilsItems.LINK_TUNIC, (TrinketRenderer) DungeonUtilsItems.LINK_TUNIC);
    }

    public void registerEntityModelLayers() {

        EntityModelLayerRegistry.registerModelLayer(CrateEntityModel.LAYER_LOCATION, CrateEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(BombEntityModel.LAYER_LOCATION, BombEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(FairyEntityModel.LAYER_LOCATION, FairyEntityModel::getTexturedModelData);
    }

    public void registerEntityRenderers() {

        EntityRendererRegistry.register(DungeonUtilsEntities.MASTER_SWORD_BEAM, MasterSwordBeamEntityRenderer::new);
        EntityRendererRegistry.register(DungeonUtilsEntities.CRATE, CrateEntityRenderer::new);
        EntityRendererRegistry.register(DungeonUtilsEntities.BOMB, BombEntityRenderer::new);
        EntityRendererRegistry.register(DungeonUtilsEntities.FAIRY, FairyEntityRenderer::new);
        BlockEntityRendererFactories.register(DungeonUtilsBlockEntities.PEDESTAL_BLOCK_ENTITY, PedestalBlockEntityRenderer::new);
    }

    public void registerModelPredicates() {

        ModelPredicateProviderRegistry.createModelPredicate(DungeonUtilsItems.HOOKSHOT, new Identifier("launched"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            return entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0f : 0.0f;
        });
        ClientUtil.registerPullPredicates(
                DungeonUtilsItems.LYNEL_BOW_FIVE_X,
                DungeonUtilsItems.LYNEL_BOW_THREE_X,
                DungeonUtilsItems.GREAT_EAGLE_BOW
        );
        ClientUtil.registerPullingPredicates(
                DungeonUtilsItems.LYNEL_BOW_FIVE_X,
                DungeonUtilsItems.LYNEL_BOW_THREE_X,
                DungeonUtilsItems.GREAT_EAGLE_BOW
        );
    }
}