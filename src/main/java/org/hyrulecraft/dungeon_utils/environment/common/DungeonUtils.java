package org.hyrulecraft.dungeon_utils.environment.common;

import eu.midnightdust.lib.config.MidnightConfig;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import org.hyrulecraft.dungeon_utils.config.*;
import org.hyrulecraft.dungeon_utils.environment.common.entity.DungeonUtilsEntities;
import org.hyrulecraft.dungeon_utils.environment.common.fluid.DungeonUtilsFluids;
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.environment.common.block.*;
import org.hyrulecraft.dungeon_utils.environment.common.itemgroup.DungeonUtilsItemGroups;
import org.hyrulecraft.dungeon_utils.environment.common.sound.DungeonUtilsSounds;
import org.hyrulecraft.dungeon_utils.environment.common.tags.DungeonUtilsTags;
import org.hyrulecraft.dungeon_utils.util.UtilCollector;
import org.hyrulecraft.dungeon_utils.util.plugin.IDungeonUtilsPlugin;

import org.hyrulecraft.dungeon_utils.util.plugin.event.lifecycle.PostInitEvent;
import org.hyrulecraft.dungeon_utils.util.plugin.event.lifecycle.PreInitEvent;
import org.slf4j.*;

import java.util.*;

// TODO: Make the master sword disable shields like an axe when used
public class DungeonUtils implements ModInitializer {

    public static final List<IDungeonUtilsPlugin> PLUGINS = new ArrayList<>();

    public static final String MOD_ID = "dungeon_utils";

    public static final String NAME = "Dungeon Utils";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    @Override
    public void onInitialize() {

        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its main class.");

        // Initializing classes.
        DungeonUtilsItemGroups.registerDungeonUtilsItemGroups();
        DungeonUtilsItems.registerDungeonUtilsItems();
        DungeonUtilsBlocks.registerDungeonUtilsBlocks();
        DungeonUtilsSounds.registerDungeonUtilsSounds();
        DungeonUtilsTags.registerDungeonUtilsTags();
        UtilCollector.registerAllUtilClasses();
        DungeonUtilsEntities.registerDungeonUtilsEntities();
        DungeonUtilsBlockEntities.registerDungeonUtilsBlockEntities();
        DungeonUtilsFluids.registerDungeonUtilsFluids();

        // Config.
        MidnightConfig.init(DungeonUtils.MOD_ID, DungeonUtilsConfig.class);

        FabricLoader.getInstance().getEntrypointContainers("dungeon_utils", IDungeonUtilsPlugin.class).forEach(plugin -> {
            plugin.getEntrypoint().onEvent(new PreInitEvent());
            plugin.getEntrypoint().init();
            LOGGER.info("{} has registered a {} plugin. ({})", plugin.getProvider().getMetadata().getName(), DungeonUtils.NAME, plugin.getEntrypoint().getClass().getName());
            PLUGINS.add(plugin.getEntrypoint());
            plugin.getEntrypoint().onEvent(new PostInitEvent());
        });
    }

    public static List<EntrypointContainer<IDungeonUtilsPlugin>> getPlugins() {
        return FabricLoader.getInstance().getEntrypointContainers("dungeon_utils", IDungeonUtilsPlugin.class);
    }
}