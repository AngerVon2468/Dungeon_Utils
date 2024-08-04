package org.hyrulecraft.dungeon_utils.environment.common.sound

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils.*

import net.minecraft.registry.*
import net.minecraft.sound.Sound
import net.minecraft.util.Identifier

class DungeonUtilsSounds {

    companion object {

        @JvmField val SWITCH = register("switch")

        @JvmField val HAMMER_HIT = register("hammer_hit")

        @JvmField val HAMMER_SWING = register("hammer_swing")

        @JvmField val REVALIS_GALE_RECHARGE = register("revalis_gale_recharge")

        @JvmField val MIPHAS_GRACE_RECHARGE = register("miphas_grace_recharge")

        @JvmField val IT_IS_MY_PLEASURE = register("it_is_my_pleasure")

        @JvmField val START_GAME = register("start_game")

        @JvmField val GET_STANDARD = register("get_standard")

        @JvmField val GET_DELUXE = register("get_deluxe")

        @JvmField val GET_HEART = register("get_heart")

        @JvmField val PARRY = register("parry")

        @JvmField val KASS_ONE = register("kass_one")

        @JvmStatic
        fun register(id: String): Sound {
            val sound = Sound.of(Identifier(MOD_ID, id))
            return Registry.register(RegistryTypes.SOUND, Identifier(MOD_ID, id), sound)
        }

        @JvmStatic
        fun registerDungeonUtilsSounds() {
            LOGGER.info("$NAME has registered its sounds.")
        }
    }
}