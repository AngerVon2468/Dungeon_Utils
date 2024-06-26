package org.hyrulecraft.dungeon_utils.util.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.Particles;
import net.minecraft.sound.SoundCategories;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.environment.common.sound.DungeonUtilsSounds;
import org.hyrulecraft.dungeon_utils.util.InventoryUtil;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ChampionEventCallbacks {

    public static void allowDeathEvent() {
        ServerLivingEntityEvents.ALLOW_DEATH.register( (entity, damageSource, damageAmount) -> {

            if (entity instanceof PlayerEntity player) {

                if (InventoryUtil.hasPlayerStackInInventory(player, DungeonUtilsItems.MIPHAS_GRACE) && !player.getItemCooldownManager().isCoolingDown(DungeonUtilsItems.MIPHAS_GRACE)) {
                    ItemStack stack = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, DungeonUtilsItems.MIPHAS_GRACE));

                    if (!stack.hasNbt()) {

                        player.setHealth(20.0f);
                        player.playSound(DungeonUtilsSounds.IT_IS_MY_PLEASURE, SoundCategories.PLAYERS, 1f, 1f);
                        addIsUsed(player);
                        player.getItemCooldownManager().set((DungeonUtilsItems.MIPHAS_GRACE), ( 20 * 60 ) * 24);
                        return false;

                    } else {

                        return true;

                    }

                }

                if (InventoryUtil.hasPlayerStackInInventory(player, DungeonUtilsItems.MIPHAS_GRACE_PLUS) && !player.getItemCooldownManager().isCoolingDown(DungeonUtilsItems.MIPHAS_GRACE_PLUS)) {
                    ItemStack stack2 = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, DungeonUtilsItems.MIPHAS_GRACE_PLUS));

                    if (!stack2.hasNbt()) {

                        player.setHealth(20.0f);
                        player.playSound(DungeonUtilsSounds.IT_IS_MY_PLEASURE, SoundCategories.PLAYERS, 1f, 1f);
                        addIsUsedPlus(player);
                        player.getItemCooldownManager().set((DungeonUtilsItems.MIPHAS_GRACE_PLUS), ( 20 * 60 ) * 8);
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

    public static void allowDamageEvent() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register( (entity, source, amount) -> {
            if (entity instanceof PlayerEntity player) {

                if (InventoryUtil.hasPlayerStackInInventory(player, DungeonUtilsItems.DARUKS_PROTECTION) && !player.getItemCooldownManager().isCoolingDown(DungeonUtilsItems.DARUKS_PROTECTION)) {
                    ItemStack stack = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, DungeonUtilsItems.DARUKS_PROTECTION));

                    if (!stack.hasNbt()) {

                        player.playSound(DungeonUtilsSounds.IT_IS_MY_PLEASURE, SoundCategories.PLAYERS, 1f, 1f);
                        addSecondUsage(player);
                        return false;

                    } else if (stack.getNbt() != null && stack.getNbt().contains("dungeon_utils.daruks_protection.usage_two") && !player.getItemCooldownManager().isCoolingDown(DungeonUtilsItems.DARUKS_PROTECTION)) {

                        player.playSound(DungeonUtilsSounds.IT_IS_MY_PLEASURE, SoundCategories.PLAYERS, 1f, 1f);
                        addThirdUsage(player);
                        return false;

                    } else if (stack.getNbt() != null && stack.getNbt().contains("dungeon_utils.daruks_protection.usage_three") && !player.getItemCooldownManager().isCoolingDown(DungeonUtilsItems.DARUKS_PROTECTION)) {

                        player.playSound(DungeonUtilsSounds.IT_IS_MY_PLEASURE, SoundCategories.PLAYERS, 1f, 1f);
                        addAntiSpam(player);
                        player.getItemCooldownManager().set((DungeonUtilsItems.DARUKS_PROTECTION), ( 20 * 60 ) * 18);
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
        ItemStack stack = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, DungeonUtilsItems.MIPHAS_GRACE));

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.miphas_gale.is_used", "is_used");

        stack.setNbt(nbtData);
    }

    public static void addIsUsedPlus(@NotNull PlayerEntity player) {
        ItemStack stack = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, DungeonUtilsItems.MIPHAS_GRACE_PLUS));

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.miphas_gale.is_used", "is_used");

        stack.setNbt(nbtData);
    }

    //

    public static void addAntiSpam(@NotNull PlayerEntity player) {
        ItemStack stack = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, DungeonUtilsItems.DARUKS_PROTECTION));

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.daruks_protection.anti_spam", "anti_spam");

        stack.setNbt(nbtData);
    }

    public static void addSecondUsage(@NotNull PlayerEntity player) {
        ItemStack stack = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, DungeonUtilsItems.DARUKS_PROTECTION));

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.daruks_protection.usage_two", "two");

        stack.setNbt(nbtData);
    }

    public static void addThirdUsage(@NotNull PlayerEntity player) {
        ItemStack stack = player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, DungeonUtilsItems.DARUKS_PROTECTION));

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.daruks_protection.usage_three", "three");

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
            world.addParticle(Particles.BUBBLE, a, b, c, 0.0, 0.0, 0.0);
            world.addParticle(Particles.BUBBLE_POP, a, b, c, 0.0, 0.0, 0.0);
            //
            world.addParticle(Particles.BUBBLE, d, e, f, 0.0, 0.0, 0.0);
            world.addParticle(Particles.BUBBLE_POP, d, e, f, 0.0, 0.0, 0.0);
            //
            world.addParticle(Particles.BUBBLE, g, h, i, 0.0, 0.0, 0.0);
            world.addParticle(Particles.BUBBLE_POP, g, h, i, 0.0, 0.0, 0.0);
            //
            world.addParticle(Particles.BUBBLE, j, k, l, 0.0, 0.0, 0.0);
            world.addParticle(Particles.BUBBLE_POP, j, k, l, 0.0, 0.0, 0.0);

        }
    }
}