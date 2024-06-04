package org.hyrulecraft.dungeon_utils.sound

import org.hyrulecraft.dungeon_utils.DungeonUtils.*

import net.minecraft.registry.*
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

class DungeonUtilsSounds {

    companion object {

        @JvmStatic val SWITCH = register("switch")

        @JvmStatic val HAMMER_HIT = register("hammer_hit")

        @JvmStatic val HAMMER_SWING = register("hammer_swing")

        @JvmStatic val REVALIS_GALE_RECHARGE = register("revalis_gale_recharge")

        @JvmStatic val MIPHAS_GRACE_RECHARGE = register("miphas_grace_recharge")

        @JvmStatic val IT_IS_MY_PLEASURE = register("it_is_my_pleasure")

        @JvmStatic val START_GAME = register("start_game")

        @JvmStatic val GET_STANDARD = register("get_standard")

        @JvmStatic val GET_DELUXE = register("get_deluxe")

        @JvmStatic val GET_HEART = register("get_heart")

        @JvmStatic val PARRY = register("parry")

        @JvmStatic val KASS_ONE = register("kass_one")

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