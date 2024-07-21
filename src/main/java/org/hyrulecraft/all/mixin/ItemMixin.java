package org.hyrulecraft.all.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.*;
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

    @ModifyReturnValue(method = "useOnEntity", at = @At(value = "RETURN"))
    public ActionResult useOnEntity(ActionResult original, @Local(argsOnly = true) @NotNull ItemStack stack, @Local(argsOnly = true) @NotNull PlayerEntity user, @Local(argsOnly = true) LivingEntity entity, @Local(argsOnly = true) Hand hand) {
        World world = user.getWorld();
        if (stack.isOf(Items.GLASS_BOTTLE) && world != null && !world.isClient()) {
            if (entity instanceof CowEntity) {

                world.playSound(user, user.getX(), user.getY(), user.getZ(), Sounds.ITEM_BOTTLE_FILL, SoundCategories.NEUTRAL, 1.0f, 1.0f);
                stack.decrement(1);
                ItemStack milkBottleStack = Items.EGG.getDefaultStack(); // Make a milk bottle item.
                user.getInventory().insertStack(milkBottleStack);
                return ActionResult.SUCCESS;

            }
            if (entity instanceof FishEntity fish) {

                world.playSound(user, user.getX(), user.getY(), user.getZ(), Sounds.ITEM_BOTTLE_FILL, SoundCategories.NEUTRAL, 1.0f, 1.0f);
                stack.decrement(1);
                ItemStack fishBottleStack = Items.BAMBOO.getDefaultStack(); // Make a fish bottle item.
                NbtUtil.setNbt(fishBottleStack, "dungeon_utils.fish_bottle.type", fish.getClass().getSimpleName());
                user.getInventory().insertStack(fishBottleStack);
                fish.discard();
                return ActionResult.SUCCESS;

            }
            if (entity instanceof EndermiteEntity || entity instanceof SilverfishEntity) {

                world.playSound(user, user.getX(), user.getY(), user.getZ(), Sounds.ITEM_BOTTLE_FILL, SoundCategories.NEUTRAL, 1.0f, 1.0f);
                stack.decrement(1);
                ItemStack bugBottleStack = Items.BAMBOO.getDefaultStack(); // Make a bug bottle item.
                NbtUtil.setNbt(bugBottleStack, "dungeon_utils.bug_bottle.type", entity.getClass().getSimpleName());
                user.getInventory().insertStack(bugBottleStack);
                entity.discard();
                return ActionResult.SUCCESS;

            }
            // Add vex as poe soul bottle
            // add fairy (as well as code the entity) and allay as fairy bottle or spirit bottle
        }
        return original;
    }
}