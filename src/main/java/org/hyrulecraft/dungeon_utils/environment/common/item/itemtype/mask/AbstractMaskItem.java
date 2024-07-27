package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.mask;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.jetbrains.annotations.*;

import java.util.List;

public abstract class AbstractMaskItem extends Item implements Equipment {

    public boolean isEquip = false;

    public AbstractMaskItem(Settings settings) {
        super(settings);
    }

    // Constructs the masks with the wanted settings so you don't need to retype out the needed settings.
    public AbstractMaskItem() {
        this(new Settings().maxDamage(0).maxCount(1));
    }

    public void onEquip(World world, PlayerEntity player) {
        this.isEquip = true;
    }

    public void onUnequip(World world, PlayerEntity player) {
        this.isEquip = false;
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity player, Hand hand) {
        if (!world.isClient() && !this.isEquip && !player.getEquippedStack(EquipmentSlot.HEAD).isOf(this.asItem())) {
            this.onEquip(world, player);
            return this.equipAndSwap(this.asItem(), world, player, hand);
        } else {
            return TypedActionResult.fail(player.getMainHandStack());
        }
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i, boolean bl) {
        super.inventoryTick(itemStack, world, entity, i, bl);
        if (entity instanceof PlayerEntity player && !world.isClient()) {
            if (this.isEquip && !player.getEquippedStack(EquipmentSlot.HEAD).isOf(this.asItem())) {
                this.onUnequip(world, player);
            }
            if (!this.isEquip && player.getEquippedStack(EquipmentSlot.HEAD).isOf(this.asItem())) {
                this.onEquip(world, player);
            }
        }
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, @Nullable World world, @NotNull List<Text> list, TooltipContext tooltipContext) {
        list.add(Text.translatable("tooltip.dungeon_utils." + this.asItem().getTranslationKey().replace("item.dungeon_utils.", "") + "_1"));
        list.add(Text.translatable("tooltip.dungeon_utils." + this.asItem().getTranslationKey().replace("item.dungeon_utils.", "") + "_2"));
    }
}