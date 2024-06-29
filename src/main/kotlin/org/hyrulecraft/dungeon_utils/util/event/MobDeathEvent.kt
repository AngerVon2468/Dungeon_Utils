package org.hyrulecraft.dungeon_utils.util.event

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents

import net.minecraft.entity.boss.WitherEntity
import net.minecraft.entity.player.PlayerEntity

import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems

import java.util.*

object MobDeathEvent {

    private val random = Random()

    @JvmStatic
    fun onEntityDiesEvent() {

        ServerLivingEntityEvents.AFTER_DEATH.register(ServerLivingEntityEvents.AfterDeath { entity, damageSource ->

            if (damageSource.source?.isAlive == true && damageSource.source is PlayerEntity) {

                if (entity is WitherEntity) {

                    if (random.nextInt(20) == 0) {

                        entity.dropStack(DungeonUtilsItems.HEART_PIECE.defaultStack)

                    } else if (random.nextInt(20) == 1) {

                        entity.dropStack(DungeonUtilsItems.HEART_PIECE.defaultStack)
                        entity.dropStack(DungeonUtilsItems.HEART_PIECE.defaultStack)

                    } else if (random.nextInt(20) == 2) {

                        entity.dropStack(DungeonUtilsItems.HEART_PIECE.defaultStack)
                        entity.dropStack(DungeonUtilsItems.HEART_PIECE.defaultStack)
                        entity.dropStack(DungeonUtilsItems.HEART_PIECE.defaultStack)

                    } else if (random.nextInt(20) == 17) {

                        entity.dropStack(DungeonUtilsItems.HEART_CONTAINER.defaultStack)

                    }

                }

            }

        })
    }
}