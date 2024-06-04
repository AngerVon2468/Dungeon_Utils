package org.hyrulecraft.dungeon_utils;

import net.fabricmc.api.DedicatedServerModInitializer;

public class DungeonUtilsServer implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {

        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its main server class.");
    }
}