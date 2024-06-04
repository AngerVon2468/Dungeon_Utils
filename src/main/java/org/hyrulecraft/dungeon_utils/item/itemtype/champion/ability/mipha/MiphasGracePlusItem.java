package org.hyrulecraft.dungeon_utils.item.itemtype.champion.ability.mipha;

import dev.emi.trinkets.api.TrinketItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.sound.DungeonUtilsSounds;
import org.hyrulecraft.dungeon_utils.util.event.ChampionEventCallbacks;

public class MiphasGracePlusItem extends TrinketItem {

    public MiphasGracePlusItem(Settings settings) {
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

            } else if (!player.getItemCooldownManager().isCoolingDown(DungeonUtilsItems.MIPHAS_GRACE_PLUS) && stack.getNbt().contains("dungeon_utils.miphas_gale.is_used") && stack.isOf(DungeonUtilsItems.MIPHAS_GRACE_PLUS)) {

                player.playSound(DungeonUtilsSounds.getMIPHAS_GRACE_RECHARGE(), SoundCategory.PLAYERS, 1f, 1f);
                stack.removeSubNbt("dungeon_utils.miphas_gale.is_used");

            } else if (player.getItemCooldownManager().isCoolingDown(DungeonUtilsItems.MIPHAS_GRACE_PLUS) && !stack.getNbt().contains("dungeon_utils.miphas_gale.is_used") && stack.isOf(DungeonUtilsItems.MIPHAS_GRACE_PLUS)) {

                ChampionEventCallbacks.addIsUsedPlus(player);

            }
        }
    }
}