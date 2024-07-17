package org.hyrulecraft.dungeon_utils.util;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

import virtuoel.pehkui.api.*;

public class CarryOverHeartContainers {

    public static void onPlayerRespawn() {
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            ScaleData oldPlayerHealth = ScaleTypes.HEALTH.getScaleData(oldPlayer);
            float newValue = oldPlayerHealth.getScale();

            ScaleData newPlayerHealth = ScaleTypes.HEALTH.getScaleData(newPlayer);
            newPlayerHealth.setScale(newValue);
        });
    }
}