package org.hyrulecraft.dungeon_utils.environment.common;

import eu.midnightdust.lib.config.MidnightConfig;

import net.fabricmc.api.ModInitializer;

import org.hyrulecraft.dungeon_utils.config.*;
import org.hyrulecraft.dungeon_utils.environment.common.entity.DungeonUtilsEntities;
import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.block.DungeonUtilsBlocks;
import org.hyrulecraft.dungeon_utils.itemgroup.DungeonUtilsItemGroups;
import org.hyrulecraft.dungeon_utils.sound.DungeonUtilsSounds;
import org.hyrulecraft.dungeon_utils.environment.common.tags.DungeonUtilsTags;
import org.hyrulecraft.dungeon_utils.util.UtilCollector;

import org.slf4j.*;

// TODO: Fix all wide skin textures.
public class DungeonUtils implements ModInitializer {

    public static final String MOD_ID = "dungeon_utils";

    public static final String NAME = "Dungeon Utils";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    @Override
    public void onInitialize() {

        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its main class.");

        // Initializing classes.
        DungeonUtilsItemGroups.registerDungeonUtilsItemGroups();
        DungeonUtilsItems.registerModItems();
        DungeonUtilsBlocks.registerModBlocks();
        DungeonUtilsSounds.registerDungeonUtilsSounds();
        DungeonUtilsTags.registerDungeonUtilsTags();
        UtilCollector.registerAllUtilClasses();
        DungeonUtilsEntities.registerDungeonUtilsEntities();

        // Config.
        MidnightConfig.init(DungeonUtils.MOD_ID, DungeonUtilsConfig.class);
        DungeonUtilsExperimentalConfig.experimentalConfigTesting();
        if (DungeonUtilsExperimentalConfig.Config.isWiiUBased) {

            DungeonUtils.LOGGER.info("isWiiUBased: true");

        } else {

            DungeonUtils.LOGGER.info("isWiiUBased: false");

        }
    }
}