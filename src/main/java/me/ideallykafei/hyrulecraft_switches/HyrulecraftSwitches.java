package me.ideallykafei.hyrulecraft_switches;

import me.ideallykafei.hyrulecraft_switches.item.*;
import me.ideallykafei.hyrulecraft_switches.block.ModBlocks;

import net.fabricmc.api.ModInitializer;

import org.slf4j.*;

public class HyrulecraftSwitches implements ModInitializer {

    public static  final String MOD_ID ="hyrulecraft_switches";

    public static  final String NAME ="HyruleCraft Switches";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    @Override
    public void onInitialize() {

        HyrulecraftSwitches.LOGGER.info(HyrulecraftSwitches.NAME + " has registered its main class.");

        // Initializing classes.
        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
    }
}