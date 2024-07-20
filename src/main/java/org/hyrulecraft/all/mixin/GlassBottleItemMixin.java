package org.hyrulecraft.all.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.*;
import net.minecraft.stat.Stats;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.world.*;

import org.hyrulecraft.dungeon_utils.util.NbtUtil;

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
    public TypedActionResult<ItemStack> use(TypedActionResult<ItemStack> original, @Local(argsOnly = true) World world, @Local(argsOnly = true) @NotNull PlayerEntity user, @Local(argsOnly = true) Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        MinecraftClient client = MinecraftClient.getInstance();
        HitResult hit = client.crosshairTarget;
        if (hit != null) {
            switch (hit.getType()) {
                case MISS -> {
                    return original;
                }
                case BLOCK -> {

                }
                case ENTITY -> {
                    EntityHitResult entityHitResult = (EntityHitResult) hit;
                    Entity entity = entityHitResult.getEntity();
                    if (entity instanceof CowEntity) {

                        world.playSound(user, user.getX(), user.getY(), user.getZ(), Sounds.ITEM_BOTTLE_FILL, SoundCategories.NEUTRAL, 1.0f, 1.0f);
                        return TypedActionResult.success(this.fill(stack, user, Items.EGG.getDefaultStack()), world.isClient()); // Make a milk bottle item.

                    }
                    if (entity instanceof FishEntity fish) {

                        world.playSound(user, user.getX(), user.getY(), user.getZ(), Sounds.ITEM_BOTTLE_FILL, SoundCategories.NEUTRAL, 1.0f, 1.0f);
                        ItemStack fishBottleStack = this.fill(stack, user, Items.BAMBOO.getDefaultStack()); // Make a fish bottle item.
                        NbtUtil.setNbt(fishBottleStack, "dungeon_utils.fish_bottle.type", fish.getClass().getSimpleName());
                        fish.discard();
                        return TypedActionResult.success(fishBottleStack, world.isClient());

                    }
                }
            }
        }
        return original;
    }
}