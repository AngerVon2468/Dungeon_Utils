package org.hyrulecraft.dungeon_utils.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.stat.Stats;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

public interface IMaskUtil extends Equipment {

    default void unequipAndSwap(World world, @NotNull PlayerEntity playerEntity) {
        EquipmentSlot equipmentSlot = EquipmentSlot.HEAD;
        ItemStack equippedStack = playerEntity.getEquippedStack(equipmentSlot);
        if (!EnchantmentHelper.hasBindingCurse(equippedStack) && !world.isClient()) {
            ItemStack itemStack3 = equippedStack.copyAndEmpty();
            playerEntity.giveItemStack(itemStack3);
        }
    }

    default TypedActionResult<ItemStack> equipAndSwap(Item item, World world, @NotNull PlayerEntity player) {
        ItemStack mainHandStack = player.getMainHandStack();
        EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(mainHandStack);
        ItemStack equippedStack = player.getEquippedStack(equipmentSlot);
        if (!EnchantmentHelper.hasBindingCurse(equippedStack) && !ItemStack.areEqual(mainHandStack, equippedStack) && !world.isClient()) {

            player.incrementStat(Stats.USED.getOrCreateStat(item));
            ItemStack itemStack3 = equippedStack.isEmpty() ? mainHandStack : equippedStack.copyAndEmpty();
            ItemStack itemStack4 = mainHandStack.copyAndEmpty();
            player.equipStack(equipmentSlot, itemStack4);
            return TypedActionResult.success(itemStack3);

        } else {

            return TypedActionResult.fail(mainHandStack);

        }
    }
}