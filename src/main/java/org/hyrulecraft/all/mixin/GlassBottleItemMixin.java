package org.hyrulecraft.all.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.fluid.DungeonUtilsFluids;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.sugar.Local;

@Mixin(GlassBottleItem.class)
public class GlassBottleItemMixin {

    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/registry/tag/Tag;)Z"), cancellable = true)
    public void use(@NotNull World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir, @Local @NotNull BlockHitResult hit) {
        if (DungeonUtilsFluids.isStateOfSpringWater(world, hit.getBlockPos())) {

            world.playSound(player, hit.getBlockPos(), Sounds.ITEM_BOTTLE_FILL, SoundCategories.BLOCKS, 1.0f, 1.0f);
            player.getMainHandStack().decrement(1);
            ItemStack milkBottleStack = DungeonUtilsFluids.SPRING_WATER_BOTTLE.getDefaultStack();
            player.getInventory().insertStack(milkBottleStack);
            cir.setReturnValue(TypedActionResult.success(player.getMainHandStack()));

        }
        if (DungeonUtilsFluids.isStateOfHotSpringWater(world, hit.getBlockPos())) {

            world.playSound(player, hit.getBlockPos(), Sounds.ITEM_BOTTLE_FILL, SoundCategories.BLOCKS, 1.0f, 1.0f);
            player.getMainHandStack().decrement(1);
            ItemStack milkBottleStack = DungeonUtilsFluids.HOT_SPRING_WATER_BOTTLE.getDefaultStack();
            player.getInventory().insertStack(milkBottleStack);
            cir.setReturnValue(TypedActionResult.success(player.getMainHandStack()));

        }
    }
}