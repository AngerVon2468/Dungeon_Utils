package org.hyrulecraft.dungeon_utils.item.itemtype;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.item.ModItems;

import org.jetbrains.annotations.NotNull;

public class MogmaMittsItem extends Item {

    public MogmaMittsItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {
            //
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity player, @NotNull Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(ModItems.MOGMA_MITTS)) {

            //

        }

        return TypedActionResult.consume(stack);
    }

    public void isBeingUsed(@NotNull PlayerEntity player) {
        ItemStack stack = player.getStackInHand(player.getActiveHand());

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.mogma_mitts.is_being_used", "is_being_used");

        stack.setNbt(nbtData);
    }
}