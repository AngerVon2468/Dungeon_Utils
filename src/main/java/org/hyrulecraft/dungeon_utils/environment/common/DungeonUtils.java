package org.hyrulecraft.dungeon_utils.environment.common;

import eu.midnightdust.lib.config.MidnightConfig;

import net.fabricmc.loader.api.*;
import net.fabricmc.api.ModInitializer;
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
import org.hyrulecraft.dungeon_utils.util.plugin.event.lifecycle.*;

import org.slf4j.*;

import java.util.*;

// TODO: Make the master sword disable shields like an axe when used
public class DungeonUtils implements ModInitializer {

    private static final List<EntrypointContainer<IDungeonUtilsPlugin>> ENTRYPOINT_CONTAINERS = new ArrayList<>();

    private static final List<ModContainer> MOD_CONTAINERS = new ArrayList<>();

    private static final List<IDungeonUtilsPlugin> PLUGINS = new ArrayList<>();

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

        FabricLoader.getInstance().getEntrypointContainers("dungeon_utils", IDungeonUtilsPlugin.class).forEach(entrypointContainer -> {
            IDungeonUtilsPlugin plugin = entrypointContainer.getEntrypoint();
            ModContainer container = entrypointContainer.getProvider();
            plugin.onEvent(new PreInitEvent());
            plugin.init();
            PLUGINS.add(plugin);
            ENTRYPOINT_CONTAINERS.add(entrypointContainer);
            MOD_CONTAINERS.add(container);
            LOGGER.info("{} has registered a {} plugin. ({})", container.getMetadata().getName(), DungeonUtils.NAME, plugin.getClass().getName());
            plugin.onEvent(new PostInitEvent());
        });
    }

    public static List<EntrypointContainer<IDungeonUtilsPlugin>> getPluginEntrypointContainers() {
        return ENTRYPOINT_CONTAINERS;
    }

    public static List<ModContainer> getModContainersWithDungeonUtilsPlugin() {
        return MOD_CONTAINERS;
    }

    public static List<IDungeonUtilsPlugin> getPlugins() {
        return PLUGINS;
    }
}