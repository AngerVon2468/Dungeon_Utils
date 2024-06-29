package org.hyrulecraft.all.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

import org.hyrulecraft.lockon_port.keybinds.DungeonUtilsKeybinds;

import org.joml.Quaternionf;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {

    @Shadow
    public abstract Quaternionf getRotation();

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderer;render(Lnet/minecraft/entity/Entity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", shift = At.Shift.AFTER))
    private void renderLockOnIcon(Entity entity, double worldX, double worldY, double worldZ, float entityYRot, float partialTicks, MatrixStack poseStack, VertexConsumerProvider buffers, int light, CallbackInfo ci) {
        DungeonUtilsKeybinds.renderWorldLast(entity, poseStack, buffers, getRotation());
    }
}