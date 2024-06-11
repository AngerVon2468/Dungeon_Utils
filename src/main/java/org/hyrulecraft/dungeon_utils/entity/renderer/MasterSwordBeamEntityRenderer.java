package org.hyrulecraft.dungeon_utils.entity.renderer;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import org.hyrulecraft.dungeon_utils.DungeonUtils;
import org.hyrulecraft.dungeon_utils.entity.MasterSwordBeamEntity;

import org.jetbrains.annotations.NotNull;

import org.joml.*;

public class MasterSwordBeamEntityRenderer<T extends Entity> extends EntityRenderer<MasterSwordBeamEntity> {

    public MasterSwordBeamEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(@NotNull MasterSwordBeamEntity entity, float yaw, float tickDelta, @NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        matrices.multiply(RotationAxis.NEGATIVE_X.rotationDegrees(-90f));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-entity.getYaw()));
        matrices.scale(2.0f, 2.0f, 2.0f);

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(getTexture(entity)));
        MatrixStack.Entry matrixEntry = matrices.peek();
        Matrix4f modelMatrix = matrixEntry.getPositionMatrix();
        Matrix3f normalMatrix = matrixEntry.getNormalMatrix();
        vertexConsumer.vertex(modelMatrix, -0.25f,  0.25f, 0.0f).color(255, 255, 255, 255).texture(0.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0, 1, 0).next();
        vertexConsumer.vertex(modelMatrix,  0.25f,  0.25f, 0.0f).color(255, 255, 255, 255).texture(1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0, 1, 0).next();
        vertexConsumer.vertex(modelMatrix,  0.25f, -0.25f, 0.0f).color(255, 255, 255, 255).texture(1.0f, 0.0f).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0, 1, 0).next();
        vertexConsumer.vertex(modelMatrix, -0.25f, -0.25f, 0.0f).color(255, 255, 255, 255).texture(0.0f, 0.0f).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0, 1, 0).next();

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(MasterSwordBeamEntity entity) {
        return new Identifier(DungeonUtils.MOD_ID, "textures/item/rupee/green_rupee.png");
    }
}