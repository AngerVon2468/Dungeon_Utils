package org.hyrulecraft.dungeon_utils.item.itemtype.champion.ability.daruk;

import dev.emi.trinkets.api.TrinketItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.sound.DungeonUtilsSounds;
import org.hyrulecraft.dungeon_utils.util.event.ChampionEventCallbacks;


public class DaruksProtectionItem extends TrinketItem {

    public DaruksProtectionItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {

            if (!stack.hasNbt()) {

                // Why are we still here... just to suffer?

            } else if (!player.getItemCooldownManager().isCoolingDown(DungeonUtilsItems.DARUKS_PROTECTION) && stack.getNbt().contains("dungeon_utils.daruks_protection.anti_spam") && stack.isOf(DungeonUtilsItems.DARUKS_PROTECTION)) {

                player.playSound(DungeonUtilsSounds.REVALIS_GALE_RECHARGE, SoundCategory.PLAYERS, 1f, 1f);
                stack.removeSubNbt("dungeon_utils.daruks_protection.anti_spam");

            } else if (player.getItemCooldownManager().isCoolingDown(DungeonUtilsItems.DARUKS_PROTECTION) && !stack.getNbt().contains("dungeon_utils.daruks_protection.anti_spam") && stack.isOf(DungeonUtilsItems.DARUKS_PROTECTION)) {

                ChampionEventCallbacks.addAntiSpam(player);

            }
        }
    }
}