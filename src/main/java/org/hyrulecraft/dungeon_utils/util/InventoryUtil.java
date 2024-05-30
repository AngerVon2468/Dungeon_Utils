package org.hyrulecraft.dungeon_utils.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;

import org.jetbrains.annotations.NotNull;

public class InventoryUtil {

    public static boolean hasPlayerStackInInventory(@NotNull PlayerEntity player, Item item) {
        for(int i = 0; i < player.getInventory().size(); i++) {
            ItemStack currentStack = player.getInventory().getStack(i);
            if (!currentStack.isEmpty() && currentStack.isOf(item)) {
                return true;
            }
        }

        return false;
    }

    public static int getFirstInventoryIndex(@NotNull PlayerEntity player, Item item) {
        for(int i = 0; i < player.getInventory().size(); i++) {
            ItemStack currentStack = player.getInventory().getStack(i);
            if (!currentStack.isEmpty() && currentStack.isOf(item)) {
                return i;
            }
        }

        return -1;
    }
}