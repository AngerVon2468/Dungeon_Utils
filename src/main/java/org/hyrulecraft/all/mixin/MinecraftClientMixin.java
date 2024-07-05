package org.hyrulecraft.all.mixin;

import net.minecraft.client.MinecraftClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @ModifyReturnValue(method = "getWindowTitle", at = @At(value = "RETURN"))
    private String getWindowTitle(String original) {
        return "Dungeon Utils";
    }
}