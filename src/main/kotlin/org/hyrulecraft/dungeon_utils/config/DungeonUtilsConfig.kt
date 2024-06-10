package org.hyrulecraft.dungeon_utils.config

import eu.midnightdust.lib.config.MidnightConfig

object DungeonUtilsConfig : MidnightConfig() {

    @JvmField
    @Entry(category = "settings") var revalisGaleHeight = 1.5

    @JvmField
    @Entry(category = "settings") var shouldAddSlowFalling = true

    @JvmField
    @Entry(category = "settings", min = 20.0, max = 1000.0) var hookShotRange = 20;
}