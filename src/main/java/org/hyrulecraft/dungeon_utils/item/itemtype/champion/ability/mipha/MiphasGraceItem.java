package org.hyrulecraft.dungeon_utils.item.itemtype.champion.ability.mipha;

import dev.emi.trinkets.api.TrinketItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.item.ModItems;
import org.hyrulecraft.dungeon_utils.sound.SoundInit;
import org.hyrulecraft.dungeon_utils.util.event.MiphasGraceCallback;

public class MiphasGraceItem extends TrinketItem {

    public MiphasGraceItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {

            if (!stack.hasNbt()) {

                // Why are we still here... just to suffer?

            } else if (!player.getItemCooldownManager().isCoolingDown(ModItems.MIPHAS_GRACE) && stack.getNbt().contains("dungeon_utils.miphas_gale.is_used") && stack.isOf(ModItems.MIPHAS_GRACE)) {

                player.playSound(SoundInit.getREVALIS_GALE_RECHANGE(), SoundCategory.PLAYERS, 1f, 1f);
                stack.removeSubNbt("dungeon_utils.miphas_gale.is_used");

            } else if (player.getItemCooldownManager().isCoolingDown(ModItems.MIPHAS_GRACE) && !stack.getNbt().contains("dungeon_utils.miphas_gale.is_used") && stack.isOf(ModItems.MIPHAS_GRACE)) {

                MiphasGraceCallback.addIsUsed(player);

            }
        }
    }
}