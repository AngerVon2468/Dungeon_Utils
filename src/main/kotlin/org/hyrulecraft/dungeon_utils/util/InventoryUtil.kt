package org.hyrulecraft.dungeon_utils.util

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item

object InventoryUtil {

    @JvmStatic
    fun hasPlayerStackInInventory(player: PlayerEntity, item: Item?): Boolean {
        for (i in 0 until player.inventory.size()) {
            val currentStack = player.inventory.getStack(i)
            if (!currentStack.isEmpty && currentStack.isOf(item)) {
                return true
            }
        }
        return false
    }

    @JvmStatic
    fun getFirstInventoryIndex(player: PlayerEntity, item: Item?): Int {
        for (i in 0 until player.inventory.size()) {
            val currentStack = player.inventory.getStack(i)
            if (!currentStack.isEmpty && currentStack.isOf(item)) {
                return i
            }
        }
        return -1
    }
}