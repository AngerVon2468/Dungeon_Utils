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
        HeartContainerCallbacks.onPlayerRespawn();

        // Commands.
        DungeonUtilsCommands.kys();
        DungeonUtilsCommands.suicide();
        DungeonUtilsCommands.trashHand();
        DungeonUtilsCommands.hcDiscord();
        DungeonUtilsCommands.setMana();
        DungeonUtilsCommands.addMana();
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            DungeonUtilsCommands.wiiu();
        }
        DungeonUtilsCommands.showoff();
        DungeonUtilsCommands.getStamina();
        DungeonUtilsCommands.setStamina();
        DungeonUtilsCommands.getMaxStamina();
        DungeonUtilsCommands.setMaxStamina();
    }

    public static void registerAllUtilClassesInClient() {

        // Events.
        LoadScreenCallback.loadScreenEvent();
        ClientSendMessageCallbacks.onSendClientMessage();
    }
}