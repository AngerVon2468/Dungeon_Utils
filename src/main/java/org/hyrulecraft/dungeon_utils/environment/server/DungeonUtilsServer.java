package org.hyrulecraft.dungeon_utils.environment.server;

import net.fabricmc.api.DedicatedServerModInitializer;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;

public class DungeonUtilsServer implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {

        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its main server class.");
    }
}