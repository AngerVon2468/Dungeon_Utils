package org.hyrulecraft.dungeon_utils.util;

import org.hyrulecraft.dungeon_utils.util.event.*;
import org.hyrulecraft.dungeon_utils.util.command.CommandInit;

public class UtilCollector {

    public static void registerAllUtilClasses() {

        // Events.
        ChampionEventCallbacks.allowDeathEvent();
        ChampionEventCallbacks.allowDamageEvent();

        // Commands.
        CommandInit.kys();
        CommandInit.suicide();
        CommandInit.trashHand();
        CommandInit.wiiu();
        CommandInit.hcDiscord();
        CommandInit.rtp();
    }

    public static void registerAllUtilClassesInClient() {

        // Events.
        LoadScreenCallback.loadScreenEvent();
    }
}