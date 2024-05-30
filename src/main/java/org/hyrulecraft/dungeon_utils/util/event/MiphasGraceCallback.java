package org.hyrulecraft.dungeon_utils.util.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import net.minecraft.nbt.NbtCompound;
import org.hyrulecraft.dungeon_utils.item.ModItems;
import org.hyrulecraft.dungeon_utils.util.InventoryUtil;
import org.jetbrains.annotations.NotNull;

public class MiphasGraceCallback {

    public static void allowDeathEvent() {
        ServerLivingEntityEvents.ALLOW_DEATH.register( (entity, damageSource, damageAmount) -> {

            if (entity instanceof PlayerEntity player) {

                ItemStack stack = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, ModItems.MIPHAS_GRACE));
                if (InventoryUtil.hasPlayerStackInInventory(player, ModItems.MIPHAS_GRACE) && !player.getItemCooldownManager().isCoolingDown(ModItems.MIPHAS_GRACE)) {

                    if (!stack.hasNbt()) {

                        player.setHealth(20.0f);
                        addIsUsed(player);
                        player.getItemCooldownManager().set((ModItems.MIPHAS_GRACE), ( 20 * 60 ) * 24);
                        return false;

                    } else {

                        return true;

                    }

                } else {

                    return true;

                }

            } else {

                return true;

            }

        });
    }

    public static void addIsUsed(@NotNull PlayerEntity player) {
        ItemStack stack = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, ModItems.MIPHAS_GRACE));

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.miphas_gale.is_used", "is_used");

        stack.setNbt(nbtData);
    }
}