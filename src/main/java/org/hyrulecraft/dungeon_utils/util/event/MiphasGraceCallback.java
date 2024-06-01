package org.hyrulecraft.dungeon_utils.util.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;

import net.minecraft.world.World;
import org.hyrulecraft.dungeon_utils.item.ModItems;
import org.hyrulecraft.dungeon_utils.sound.SoundInit;
import org.hyrulecraft.dungeon_utils.util.InventoryUtil;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MiphasGraceCallback {

    public static void allowDeathEvent() {
        ServerLivingEntityEvents.ALLOW_DEATH.register( (entity, damageSource, damageAmount) -> {

            if (entity instanceof PlayerEntity player) {

                if (InventoryUtil.hasPlayerStackInInventory(player, ModItems.MIPHAS_GRACE) && !player.getItemCooldownManager().isCoolingDown(ModItems.MIPHAS_GRACE)) {
                    ItemStack stack = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, ModItems.MIPHAS_GRACE));

                    if (!stack.hasNbt()) {

                        player.setHealth(20.0f);
                        addParticlesToWorld(player, player.getWorld()); // This doesn't work as the World call doesn't seem to work right.
                        player.playSound(SoundInit.getIT_IS_MY_PLEASURE(), SoundCategory.PLAYERS, 1f, 1f);
                        addIsUsed(player);
                        player.getItemCooldownManager().set((ModItems.MIPHAS_GRACE), ( 20 * 60 ) * 24);
                        return false;

                    } else {

                        return true;

                    }

                }

                if (InventoryUtil.hasPlayerStackInInventory(player, ModItems.MIPHAS_GRACE_PLUS) && !player.getItemCooldownManager().isCoolingDown(ModItems.MIPHAS_GRACE_PLUS)) {
                    ItemStack stack2 = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, ModItems.MIPHAS_GRACE_PLUS));

                    if (!stack2.hasNbt()) {

                        player.setHealth(20.0f);
                        addParticlesToWorld(player, player.getWorld()); // This doesn't work as the World call doesn't seem to work right.
                        player.playSound(SoundInit.getIT_IS_MY_PLEASURE(), SoundCategory.PLAYERS, 1f, 1f);
                        addIsUsedPlus(player);
                        player.getItemCooldownManager().set((ModItems.MIPHAS_GRACE_PLUS), ( 20 * 60 ) * 8);
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

    public static void addIsUsedPlus(@NotNull PlayerEntity player) {
        ItemStack stack = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, ModItems.MIPHAS_GRACE_PLUS));

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.miphas_gale.is_used", "is_used");

        stack.setNbt(nbtData);
    }

    public static void addParticlesToWorld(@NotNull PlayerEntity player, @NotNull World world) {
        if (world.isClient) {

            Random random = new Random();
            double a = player.getX() + random.nextDouble();
            double b = player.getY() + random.nextDouble();
            double c = player.getZ() + random.nextDouble();
            //
            double d = player.getX() - random.nextDouble();
            double e = player.getY() + random.nextDouble();
            double f = player.getZ() - random.nextDouble();
            //
            double g = player.getX() - random.nextDouble();
            double h = player.getY() + random.nextDouble();
            double i = player.getZ() + random.nextDouble();
            //
            double j = player.getX() + random.nextDouble();
            double k = player.getY() + random.nextDouble();
            double l = player.getZ() - random.nextDouble();
            //
            world.addParticle(ParticleTypes.BUBBLE, a, b, c, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.BUBBLE_POP, a, b, c, 0.0, 0.0, 0.0);
            //
            world.addParticle(ParticleTypes.BUBBLE, d, e, f, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.BUBBLE_POP, d, e, f, 0.0, 0.0, 0.0);
            //
            world.addParticle(ParticleTypes.BUBBLE, g, h, i, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.BUBBLE_POP, g, h, i, 0.0, 0.0, 0.0);
            //
            world.addParticle(ParticleTypes.BUBBLE, j, k, l, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.BUBBLE_POP, j, k, l, 0.0, 0.0, 0.0);

        }
    }
}