package org.hyrulecraft.dungeon_utils.config

import eu.midnightdust.lib.config.MidnightConfig

object DungeonUtilsConfig : MidnightConfig() {

    @JvmField
    @Entry(category = "settings") var revalisGaleHeight = 1.5

    @JvmField
    @Entry(category = "settings") var shouldAddSlowFalling = true
}