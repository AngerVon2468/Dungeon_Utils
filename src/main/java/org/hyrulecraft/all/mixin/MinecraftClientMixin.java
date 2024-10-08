package org.hyrulecraft.all.mixin;

import net.minecraft.client.MinecraftClient;

import org.jetbrains.annotations.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Contract(pure = true)
    @ModifyReturnValue(method = "getWindowTitle", at = @At("RETURN"))
    private @NotNull String getWindowTitle(@NotNull String original) {
        return original.replace("*", "");
    }
}