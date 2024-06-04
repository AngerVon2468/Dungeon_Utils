package org.hyrulecraft.dungeon_utils;

import dev.emi.trinkets.api.client.*;

import eu.midnightdust.lib.config.MidnightConfig;

import net.fabricmc.api.ModInitializer;

import org.hyrulecraft.dungeon_utils.config.DungeonUtilsConfig;
import org.hyrulecraft.dungeon_utils.item.*;
import org.hyrulecraft.dungeon_utils.block.ModBlocks;
import org.hyrulecraft.dungeon_utils.itemgroup.DungeonUtilsItemGroups;
import org.hyrulecraft.dungeon_utils.sound.SoundInit;
import org.hyrulecraft.dungeon_utils.util.UtilCollector;

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
        UtilCollector.registerAllUtilClasses();

        // Config.
        MidnightConfig.init(DungeonUtils.MOD_ID, DungeonUtilsConfig.class);

        // Trinket Render.
        TrinketRendererRegistry.registerRenderer(ModItems.LINK_CAP, (TrinketRenderer) ModItems.LINK_CAP);
    }
}