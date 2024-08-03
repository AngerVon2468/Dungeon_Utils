package org.hyrulecraft.dungeon_utils.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;

import org.jetbrains.annotations.NotNull;

public interface IEquipmentUtil extends Equipment {

    default void unequipAndSwap(@NotNull PlayerEntity playerEntity) {
        EquipmentSlot equipmentSlot = EquipmentSlot.HEAD;
        ItemStack equippedStack = playerEntity.getEquippedStack(equipmentSlot);
        if (!EnchantmentHelper.hasBindingCurse(equippedStack)) {
            ItemStack itemStack3 = equippedStack.copyAndEmpty();
            playerEntity.giveItemStack(itemStack3);
        }
    }
}