package org.hyrulecraft.dungeon_utils.environment.client.entity.renderer;

import net.fabricmc.api.*;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;
import org.hyrulecraft.dungeon_utils.environment.common.entity.MasterSwordBeamEntity;
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;

import org.jetbrains.annotations.NotNull;

import org.joml.*;

import java.time.*;

@Environment(EnvType.CLIENT)
public class MasterSwordBeamEntityRenderer extends EntityRenderer<MasterSwordBeamEntity> {

    public MasterSwordBeamEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(@NotNull MasterSwordBeamEntity beam, float yaw, float tickDelta, @NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        matrices.multiply(RotationAxis.NEGATIVE_X.rotationDegrees(-90f));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-beam.getYaw()));
        matrices.scale(2.0f, 2.0f, 2.0f);

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(getTexture(beam)));
        MatrixStack.Entry matrixEntry = matrices.peek();
        Matrix4f modelMatrix = matrixEntry.getPositionMatrix();
        Matrix3f normalMatrix = matrixEntry.getNormalMatrix();
        vertexConsumer.vertex(modelMatrix, -0.25f,  0.25f, 0.0f).color(255, 255, 255, 255).texture(0.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0f, 1f, 0f).next();
        vertexConsumer.vertex(modelMatrix,  0.25f,  0.25f, 0.0f).color(255, 255, 255, 255).texture(1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0f, 1f, 0f).next();
        vertexConsumer.vertex(modelMatrix,  0.25f, -0.25f, 0.0f).color(255, 255, 255, 255).texture(1.0f, 0.0f).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0f, 1f, 0f).next();
        vertexConsumer.vertex(modelMatrix, -0.25f, -0.25f, 0.0f).color(255, 255, 255, 255).texture(0.0f, 0.0f).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0f, 1f, 0f).next();

        matrices.pop();
        super.render(beam, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(@NotNull MasterSwordBeamEntity beam) {

        Entity owner = beam.getOwner();
        LocalDateTime localDateAndTime = LocalDateTime.now();
        if (localDateAndTime.getMonth() == Month.JUNE || localDateAndTime.getMonth() == Month.SEPTEMBER && localDateAndTime.getDayOfMonth() == 11) {

            return new Identifier(DungeonUtils.MOD_ID, "textures/item/rupee/pride_rupee.png");

        } else if (owner instanceof PlayerEntity playerOwner && playerOwner.getEquippedStack(EquipmentSlot.MAINHAND).isOf(DungeonUtilsItems.THE_MASTER_SWORD_AWAKENED)) {

            return new Identifier(DungeonUtils.MOD_ID, "textures/item/rupee/blue_rupee.png");

        } else if (owner instanceof PlayerEntity playerOwner && playerOwner.getEquippedStack(EquipmentSlot.MAINHAND).isOf(DungeonUtilsItems.THE_MASTER_SWORD)) {

            return new Identifier(DungeonUtils.MOD_ID, "textures/item/rupee/green_rupee.png");

        } else {

            return new Identifier(DungeonUtils.MOD_ID, "textures/item/rupee/rupoor.png");

        }
    }
}