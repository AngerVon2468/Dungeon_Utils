package org.hyrulecraft.dungeon_utils.util;

import net.fabricmc.loader.api.FabricLoader;

import org.hyrulecraft.dungeon_utils.util.event.*;
import org.hyrulecraft.dungeon_utils.util.command.DungeonUtilsCommands;

public class UtilCollector {

    public static void registerAllUtilClasses() {

        // Events.
        ChampionEventCallbacks.allowDeathEvent();
        ChampionEventCallbacks.allowDamageEvent();
        MobDeathEvent.onEntityDiesEvent();
        CarryOverHeartContainers.onPlayerRespawn();

        // Commands.
        DungeonUtilsCommands.kys();
        DungeonUtilsCommands.suicide();
        DungeonUtilsCommands.trashHand();
        DungeonUtilsCommands.hcDiscord();
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            DungeonUtilsCommands.wiiu();
        }
        DungeonUtilsCommands.showoff();
    }

    public static void registerAllUtilClassesInClient() {

        // Events.
        LoadScreenCallback.loadScreenEvent();
        ClientSendMessageCallbacks.onSendClientMessage();
    }
}