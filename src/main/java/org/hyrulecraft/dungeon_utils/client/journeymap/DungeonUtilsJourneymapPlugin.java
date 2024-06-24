package org.hyrulecraft.dungeon_utils.client.journeymap;

import journeymap.client.api.*;
import journeymap.client.api.event.ClientEvent;

import org.hyrulecraft.dungeon_utils.DungeonUtils;

public class DungeonUtilsJourneymapPlugin implements IClientPlugin {

    private static DungeonUtilsJourneymapPlugin INSTANCE;

    public DungeonUtilsJourneymapPlugin() {
        INSTANCE = this;
    }

    // Make sure this is not null when being called.
    public static DungeonUtilsJourneymapPlugin getInstance() {
        return INSTANCE;
    }

    @Override
    public void initialize(IClientAPI jmClientApi) {

    }

    @Override
    public String getModId() {
        return DungeonUtils.MOD_ID;
    }

    @Override
    public void onEvent(ClientEvent event) {

    }
}