package org.hyrulecraft.dungeon_utils.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class DungeonUtilsConfig extends MidnightConfig {

    @Entry(category = "settings") public static double revalisGaleHeight = 1.5;

    @Entry(category = "settings") public static boolean shouldAddSlowFalling = true;

    @Entry(category = "settings", min = 20.0, max = 1000.0) public static double hookShotRange = 20;
}