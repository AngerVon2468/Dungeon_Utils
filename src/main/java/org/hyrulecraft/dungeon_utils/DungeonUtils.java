package org.hyrulecraft.dungeon_utils;

import org.hyrulecraft.dungeon_utils.item.*;
import org.hyrulecraft.dungeon_utils.block.ModBlocks;
import org.hyrulecraft.dungeon_utils.sound.SoundInit;

import net.fabricmc.api.ModInitializer;

import org.slf4j.*;

public class DungeonUtils implements ModInitializer {

    public static final String MOD_ID ="hyrulecraft_switches";

    public static final String NAME ="HyruleCraft Switches";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    @Override
    public void onInitialize() {

        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its main class.");

        // Initializing classes.
        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        SoundInit.registerDungeonUtilsSounds();
    }
}