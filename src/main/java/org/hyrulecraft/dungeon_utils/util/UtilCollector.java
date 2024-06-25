package org.hyrulecraft.dungeon_utils.util;

import net.fabricmc.loader.api.FabricLoader;

import org.hyrulecraft.dungeon_utils.util.event.ChampionEventCallbacks;
import org.hyrulecraft.dungeon_utils.util.event.ClientSendMessageCallbacks;
import org.hyrulecraft.dungeon_utils.util.event.*;
import org.hyrulecraft.dungeon_utils.util.command.DungeonUtilsCommands;

public class UtilCollector {

    public static void registerAllUtilClasses() {

        // Events.
        ChampionEventCallbacks.allowDeathEvent();
        ChampionEventCallbacks.allowDamageEvent();

        // Commands.
        DungeonUtilsCommands.kys();
        DungeonUtilsCommands.suicide();
        DungeonUtilsCommands.trashHand();
        DungeonUtilsCommands.hcDiscord();
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            DungeonUtilsCommands.dungeonUtils();
            DungeonUtilsCommands.wiiu();
        }
    }

    public static void registerAllUtilClassesInClient() {

        // Events.
        LoadScreenCallback.loadScreenEvent();
        ClientSendMessageCallbacks.onSendClientMessage();
    }
}