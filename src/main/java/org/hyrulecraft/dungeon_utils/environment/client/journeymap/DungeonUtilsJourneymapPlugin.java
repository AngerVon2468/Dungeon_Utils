package org.hyrulecraft.dungeon_utils.environment.client.journeymap;

import journeymap.client.api.*;
import journeymap.client.api.display.Waypoint;
import journeymap.client.api.event.ClientEvent;

import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

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
    public void initialize(@NotNull IClientAPI jmClientApi) {
        this.jmAPI = jmClientApi;
    }

    @Override
    public String getModId() {
        return DungeonUtils.MOD_ID;
    }

    @Override
    public void onEvent(@NotNull ClientEvent event) {

    }

    public int randomColour() {
        Random random = new Random();

        if (random.nextDouble(1, 16) == 0) {

            return 0x1000000; // Black

        } else if (random.nextDouble(1, 16) == 1) {

            return 0x0000ff; // Blue

        } else if (random.nextDouble(1, 16) == 2) {

            return 0x800000; // Brown

        } else if (random.nextDouble(1, 16) == 3) {

            return 0x00ffff; // Cyan

        } else if (random.nextDouble(1, 16) == 4) {

            return 0x808080; // Grey

        } else if (random.nextDouble(1, 16) == 5) {

            return 0x008000; // Green

        } else if (random.nextDouble(1, 16) == 6) {

            return 0x00ff00; // Lime

        } else if (random.nextDouble(1, 16) == 7) {

            return 0xff00ff; // Magenta

        } else if (random.nextDouble(1, 16) == 8) {

            return 0x000080; // Navy

        } else if (random.nextDouble(1, 16) == 9) {

            return 0xff6600; // Orange

        } else if (random.nextDouble(1, 16) == 10) {

            return 0xff00ff; // Pink

        } else if (random.nextDouble(1, 16) == 11) {

            return 0x800080; // Purple

        } else if (random.nextDouble(1, 16) == 12) {

            return 0xff0000; // Red

        } else if (random.nextDouble(1, 16) == 13) {

            return 0xc0c0c0; // Silver

        } else if (random.nextDouble(1, 16) == 14) {

            return 0xffffff; // White

        } else if (random.nextDouble(1, 16) == 15) {

            return 	0xffff00; // Yellow

        } else {

            return 0;

        }
    }

    public Waypoint createWaypoint(BlockPos blockPos, RegistryKey<World> dimension) {
        Waypoint waypoint = null;
        try {

            waypoint = new Waypoint(DungeonUtils.MOD_ID, blockPos.getX() + "_" + blockPos.getZ(), blockPos.getX() + ", " + blockPos.getZ(), dimension, blockPos)
                    .setColor(randomColour())
                    .setPersistent(true)
                    .setEditable(false);

            // Add or update
            this.jmAPI.show(waypoint);

        } catch (Throwable t) {
            DungeonUtils.LOGGER.error(t.getMessage(), t);
        }

        return waypoint;
    }
}