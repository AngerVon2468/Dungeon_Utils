package org.hyrulecraft.all.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.stat.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;

@Mixin(GlassBottleItem.class)
public abstract class GlassBottleItemMixin extends Item {

    public GlassBottleItemMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    protected ItemStack fill(ItemStack itemStack, @NotNull PlayerEntity playerEntity, ItemStack itemStack2) {
        playerEntity.incrementStat(Stats.USED.getOrCreateStat(((GlassBottleItem)(Object)this)));
        return ItemUsage.exchangeStack(itemStack, playerEntity, itemStack2);
    }

    @ModifyReturnValue(method = "use", at = @At(value = "RETURN"))
    public TypedActionResult<ItemStack> use(TypedActionResult<ItemStack> original, @Local(argsOnly = true) World world, @Local(argsOnly = true) PlayerEntity playerEntity, @Local(argsOnly = true) Hand hand) {
        return original;
    }
}