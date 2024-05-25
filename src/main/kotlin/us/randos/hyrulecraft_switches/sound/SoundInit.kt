package us.randos.hyrulecraft_switches.sound

import me.ideallykafei.hyrulecraft_switches.HyrulecraftSwitches.*

import net.minecraft.registry.*
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

class SoundInit {

    companion object {

        @JvmStatic val SWITCH = register("switch")

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