package org.hyrulecraft.dungeon_utils.sound

import org.hyrulecraft.dungeon_utils.DungeonUtils.*

import net.minecraft.registry.*
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

class SoundInit {

    companion object {

        @JvmStatic val SWITCH = register("switch")

        @JvmStatic val HAMMER_HIT = register("hammer_hit")

        @JvmStatic val HAMMER_SWING = register("hammer_swing")

        @JvmStatic
        fun register(id: String?): SoundEvent? {
            val sound = SoundEvent.of(Identifier(MOD_ID, id))
            return Registry.register(Registries.SOUND_EVENT, Identifier(MOD_ID, id), sound)
        }

        @JvmStatic
        fun registerDungeonUtilsSounds() {
            LOGGER.info("$NAME has registered its sounds.")
        }
    }
}