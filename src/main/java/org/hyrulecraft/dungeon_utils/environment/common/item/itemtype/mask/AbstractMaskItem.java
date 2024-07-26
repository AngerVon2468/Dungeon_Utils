package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.mask;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractMaskItem extends Item implements Equipment {

    public boolean wasEquipped;

    public AbstractMaskItem(Settings settings) {
        super(settings);
    }

    // Constructs the masks with the wanted settings so you don't need to retype out the needed settings.
    public AbstractMaskItem() {
        this(new Settings().maxDamage(0).maxCount(1));
    }

    abstract Item getItem();

    public void onEquip(World world, PlayerEntity player) {}

    public void onUnequip(World world, PlayerEntity player) {}

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient()) {
            this.onEquip(world, playerEntity);
            return this.equipAndSwap(this.getItem(), world, playerEntity, hand);
        } else {
            return TypedActionResult.fail(playerEntity.getStackInHand(hand));
        }
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i, boolean bl) {
        super.inventoryTick(itemStack, world, entity, i, bl);
        if (entity instanceof PlayerEntity player) {
        }
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }
}