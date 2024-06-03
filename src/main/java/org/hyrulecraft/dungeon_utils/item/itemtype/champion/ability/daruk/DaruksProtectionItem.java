package org.hyrulecraft.dungeon_utils.item.itemtype.champion.ability.daruk;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;
import org.hyrulecraft.dungeon_utils.item.ModItems;
import org.hyrulecraft.dungeon_utils.sound.SoundInit;
import org.hyrulecraft.dungeon_utils.util.event.ChampionEventCallbacks;


public class DaruksProtectionItem extends Item {

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

            } else if (!player.getItemCooldownManager().isCoolingDown(ModItems.DARUKS_PROTECTION) && stack.getNbt().contains("dungeon_utils.daruks_protection.anti_spam") && stack.isOf(ModItems.DARUKS_PROTECTION)) {

                player.playSound(SoundInit.getREVALIS_GALE_RECHARGE(), SoundCategory.PLAYERS, 1f, 1f);
                stack.removeSubNbt("dungeon_utils.daruks_protection.anti_spam");

            } else if (player.getItemCooldownManager().isCoolingDown(ModItems.DARUKS_PROTECTION) && !stack.getNbt().contains("dungeon_utils.daruks_protection.anti_spam") && stack.isOf(ModItems.DARUKS_PROTECTION)) {

                ChampionEventCallbacks.addAntiSpam(player);

            }
        }
    }
}