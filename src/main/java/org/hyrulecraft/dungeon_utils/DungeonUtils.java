package org.hyrulecraft.dungeon_utils;

import dev.emi.trinkets.api.client.*;

import eu.midnightdust.lib.config.MidnightConfig;

import net.fabricmc.api.ModInitializer;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.config.DungeonUtilsConfig;
import org.hyrulecraft.dungeon_utils.item.*;
import org.hyrulecraft.dungeon_utils.block.ModBlocks;
import org.hyrulecraft.dungeon_utils.itemgroup.DungeonUtilsItemGroups;
import org.hyrulecraft.dungeon_utils.sound.SoundInit;
import org.hyrulecraft.dungeon_utils.util.event.*;

import org.slf4j.*;

public class DungeonUtils implements ModInitializer {

    public static final String MOD_ID = "dungeon_utils";

    public static final String NAME = "Dungeon Utils";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    @Override
    public void onInitialize() {

        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its main class.");

        // Initializing classes.
        DungeonUtilsItemGroups.registerDungeonUtilsItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        SoundInit.registerDungeonUtilsSounds();

        // Events
        MiphasGraceCallback.allowDeathEvent();
        LoadWorldScreenEvent.titleScreenLoadedEvent();

        // To do with bows.
        ModelPredicateProviderRegistry.register(ModItems.LYNEL_BOW, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (entity.getActiveItem() != stack) {
                return 0.0f;
            }
            return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
        });

        // Config
        MidnightConfig.init(DungeonUtils.MOD_ID, DungeonUtilsConfig.class);

        // Trinket Render
        TrinketRendererRegistry.registerRenderer(ModItems.LINK_CAP, (TrinketRenderer) ModItems.LINK_CAP);
    }
}