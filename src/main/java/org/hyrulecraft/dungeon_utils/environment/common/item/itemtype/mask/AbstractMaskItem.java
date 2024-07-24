package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.mask;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractMaskItem extends Item implements Equipment {

    public AbstractMaskItem(Settings settings) {
        super(settings);
    }

    abstract Item getItem();

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity playerEntity, Hand hand) {
        return this.equipAndSwap(this.getItem(), world, playerEntity, hand);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }
}