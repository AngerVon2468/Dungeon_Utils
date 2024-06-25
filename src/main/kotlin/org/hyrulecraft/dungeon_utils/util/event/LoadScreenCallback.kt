package org.hyrulecraft.dungeon_utils.util.event

import de.keksuccino.auudio.audio.AudioClip

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents.AFTER_INIT

import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.*
import net.minecraft.sound.SoundCategory
import net.minecraft.util.Identifier

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils

class LoadScreenCallback {

    companion object {

        var START_GAME_CLIP: AudioClip? = AudioClip.buildInternalClip(Identifier(DungeonUtils.MOD_ID, "sounds/event/game/start_game.ogg"), SoundCategory.MUSIC)

        @JvmStatic
        fun loadScreenEvent() {
            AFTER_INIT.register { client, screen, scaledWidth, scaledHeight ->

                if (screen is LevelLoadingScreen) {

                    START_GAME_CLIP?.play()

                }

                if (screen is ConnectScreen) {

                    START_GAME_CLIP?.play()

                }

                MinecraftClient.getInstance().window.setTitle("WiiU")

            }
        }
    }
}