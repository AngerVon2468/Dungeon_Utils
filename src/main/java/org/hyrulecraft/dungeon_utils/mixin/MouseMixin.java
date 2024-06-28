package org.hyrulecraft.dungeon_utils.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;

import org.hyrulecraft.lockon_port.keybinds.DungeonUtilsKeybinds;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(Mouse.class)
public class MouseMixin {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "updateMouse",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void onTurn(CallbackInfo ci, double d0, double d1, double d4, double d5, double d6, double d2, double d3, int i) {
        if (DungeonUtilsKeybinds.handleKeyPress(this.client.clientPlayer, d2, d3)) {
            ci.cancel();
        }
    }
}