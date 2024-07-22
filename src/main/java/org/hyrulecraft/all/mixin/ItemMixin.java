package org.hyrulecraft.all.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.entity.entity_type.FairyEntity;
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;
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
                ItemStack milkBottleStack = DungeonUtilsItems.MILK_BOTTLE.getDefaultStack();
                user.getInventory().insertStack(milkBottleStack);
                return ActionResult.SUCCESS;

            }
            if (entity instanceof FishEntity fish) {

                world.playSound(user, user.getX(), user.getY(), user.getZ(), Sounds.ITEM_BOTTLE_FILL, SoundCategories.NEUTRAL, 1.0f, 1.0f);
                stack.decrement(1);
                ItemStack fishBottleStack = DungeonUtilsItems.FISH_BOTTLE.getDefaultStack();
                NbtUtil.setNbt(fishBottleStack, "dungeon_utils.fish_bottle.type", fish.getClass().getSimpleName());
                user.getInventory().insertStack(fishBottleStack);
                fish.discard();
                return ActionResult.SUCCESS;

            }
            if (entity instanceof EndermiteEntity || entity instanceof SilverfishEntity) {

                world.playSound(user, user.getX(), user.getY(), user.getZ(), Sounds.ITEM_BOTTLE_FILL, SoundCategories.NEUTRAL, 1.0f, 1.0f);
                stack.decrement(1);
                ItemStack bugBottleStack = DungeonUtilsItems.BUG_BOTTLE.getDefaultStack();
                NbtUtil.setNbt(bugBottleStack, "dungeon_utils.bug_bottle.type", entity.getClass().getSimpleName());
                user.getInventory().insertStack(bugBottleStack);
                entity.discard();
                return ActionResult.SUCCESS;

            }
            if (entity instanceof FairyEntity || entity instanceof AllayEntity) {

                world.playSound(user, user.getX(), user.getY(), user.getZ(), Sounds.ITEM_BOTTLE_FILL, SoundCategories.NEUTRAL, 1.0f, 1.0f);
                stack.decrement(1);
                ItemStack fairyBottleStack = DungeonUtilsItems.FAIRY_BOTTLE.getDefaultStack();
                NbtUtil.setNbt(fairyBottleStack, "dungeon_utils.fairy_bottle.type", entity.getClass().getSimpleName());
                user.getInventory().insertStack(fairyBottleStack);
                return ActionResult.SUCCESS;

            }
            // Add vex as poe soul bottle
        }
        return original;
    }
}