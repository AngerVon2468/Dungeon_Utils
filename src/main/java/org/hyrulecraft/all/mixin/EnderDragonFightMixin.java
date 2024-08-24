package org.hyrulecraft.all.mixin;

import net.minecraft.entity.boss.dragon.*;

import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderDragonFight.class)
public abstract class EnderDragonFightMixin {

	@Inject(method = "dragonKilled", at = @At("TAIL"))
	private void dragonKilled(@NotNull EnderDragonEntity enderDragonEntity, CallbackInfo ci) {

		enderDragonEntity.dropStack(DungeonUtilsItems.HEART_CONTAINER.getDefaultStack());

	}
}