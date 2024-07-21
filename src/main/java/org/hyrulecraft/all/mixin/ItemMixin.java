package org.hyrulecraft.all.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.util.NbtUtil;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;

@Mixin(Item.class)
public abstract class ItemMixin {

    @ModifyReturnValue(method = "useOnBlock", at = @At(value = "RETURN"))
    public ActionResult useOnBlock(ActionResult original, @Local(argsOnly = true) ItemUsageContext context) {
        return original;
    }

    @Unique
    public ItemStack fill(ItemStack itemStack, @NotNull PlayerEntity playerEntity, ItemStack itemStack2) {
        return ItemUsage.exchangeStack(itemStack, playerEntity, itemStack2);
    }

    @ModifyReturnValue(method = "useOnEntity", at = @At(value = "RETURN"))
    public ActionResult useOnEntity(ActionResult original, @Local(argsOnly = true) @NotNull ItemStack stack, @Local(argsOnly = true) @NotNull PlayerEntity user, @Local(argsOnly = true) LivingEntity entity, @Local(argsOnly = true) Hand hand) {
        World world = user.getWorld();
        if (stack.isOf(Items.GLASS_BOTTLE) && world != null && !world.isClient()) {
            if (entity instanceof CowEntity) {

                world.playSound(user, user.getX(), user.getY(), user.getZ(), Sounds.ITEM_BOTTLE_FILL, SoundCategories.NEUTRAL, 1.0f, 1.0f);
                ItemStack milkBottleStack = this.fill(stack, user, Items.EGG.getDefaultStack()); // Make a milk bottle item.
                return ActionResult.SUCCESS;

            }
            if (entity instanceof FishEntity fish) {

                world.playSound(user, user.getX(), user.getY(), user.getZ(), Sounds.ITEM_BOTTLE_FILL, SoundCategories.NEUTRAL, 1.0f, 1.0f);
                ItemStack fishBottleStack = this.fill(stack, user, Items.BAMBOO.getDefaultStack()); // Make a fish bottle item.
                NbtUtil.setNbt(fishBottleStack, "dungeon_utils.fish_bottle.type", fish.getClass().getSimpleName());
                fish.discard();
                return ActionResult.SUCCESS;

            }
        }
        return original;
    }
}