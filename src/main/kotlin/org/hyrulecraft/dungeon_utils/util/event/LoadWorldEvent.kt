package org.hyrulecraft.dungeon_utils.util.event

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents.AFTER_INIT

import net.minecraft.client.gui.screen.LevelLoadingScreen
import net.minecraft.sound.SoundCategory

import org.hyrulecraft.dungeon_utils.DungeonUtils
import org.hyrulecraft.dungeon_utils.sound.SoundInit.Companion.START_GAME

class LoadWorldEvent {

    companion object {

        @JvmStatic
        fun titleScreenLoadedEvent() {
            AFTER_INIT.register { client, screen, scaledWidth, scaledHeight ->

                if (screen is LevelLoadingScreen) {

                    DungeonUtils.LOGGER.info("Loading screen lol")
                    /*client.player!!.playSound(START_GAME, SoundCategory.MUSIC, 1f, 1f)*/

                    // Figure out how to play the sound!!

                }

            }
        }
    }
}