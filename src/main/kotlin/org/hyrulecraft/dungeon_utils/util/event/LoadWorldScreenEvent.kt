package org.hyrulecraft.dungeon_utils.util.event

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents.AFTER_INIT

import net.minecraft.client.gui.screen.LevelLoadingScreen
import net.minecraft.sound.MusicSound

import org.hyrulecraft.dungeon_utils.DungeonUtils
import org.hyrulecraft.dungeon_utils.sound.SoundInit

class LoadWorldScreenEvent {

    companion object {

        /*val MENU: MusicSound = MusicSound(SoundInit.START_GAME, 1, 2, true)*/


        @JvmStatic
        fun titleScreenLoadedEvent() {
            AFTER_INIT.register { client, screen, scaledWidth, scaledHeight ->

                if (screen is LevelLoadingScreen) {

                    DungeonUtils.LOGGER.info("Loading screen lol")


                    // Figure out how to play the sound!!

                }

            }
        }
    }
}