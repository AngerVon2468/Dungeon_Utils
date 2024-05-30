package org.hyrulecraft.dungeon_utils.util.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

import net.minecraft.entity.player.PlayerEntity;

import org.hyrulecraft.dungeon_utils.item.ModItems;
import org.hyrulecraft.dungeon_utils.util.InventoryUtil;

public class EventCallbacks {

    public static void allowDeathEvent() {
        ServerLivingEntityEvents.ALLOW_DEATH.register( (entity, damageSource, damageAmount) -> {

            if (entity instanceof PlayerEntity player) {

                if (InventoryUtil.hasPlayerStackInInventory(player, ModItems.MIPHAS_GRACE)) {

                    player.setHealth(20.0f);
                    return false;

                } else {

                    return true;

                }

            } else {

                return true;

            }

        });
    }
}