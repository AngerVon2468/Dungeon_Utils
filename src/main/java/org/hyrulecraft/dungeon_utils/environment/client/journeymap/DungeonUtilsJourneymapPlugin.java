package org.hyrulecraft.dungeon_utils.environment.client.journeymap;

import journeymap.client.api.*;
import journeymap.client.api.display.Waypoint;
import journeymap.client.api.event.ClientEvent;

import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.DungeonUtils;

public class DungeonUtilsJourneymapPlugin implements IClientPlugin {

    private static DungeonUtilsJourneymapPlugin INSTANCE;

    public IClientAPI jmAPI;

    public DungeonUtilsJourneymapPlugin() {
        INSTANCE = this;
    }

    // Make sure this is not null when being called.
    public static DungeonUtilsJourneymapPlugin getInstance() {
        return INSTANCE;
    }

    @Override
    public void initialize(IClientAPI jmClientApi) {
        this.jmAPI = jmClientApi;
    }

    @Override
    public String getModId() {
        return DungeonUtils.MOD_ID;
    }

    @Override
    public void onEvent(ClientEvent event) {

    }

    public Waypoint createWaypoint(BlockPos bedLocation, RegistryKey<World> dimension) {
        Waypoint bedWaypoint = null;
        try {

            // Waypoint itself
            bedWaypoint = new Waypoint(DungeonUtils.MOD_ID, "dg_test", "DG_Test", dimension, bedLocation)
                    .setColor(0x00ffff)
                    .setPersistent(true);

            // Add or update
            this.jmAPI.show(bedWaypoint);

        } catch (Throwable t) {
            DungeonUtils.LOGGER.error(t.getMessage(), t);
        }

        return bedWaypoint;
    }
}