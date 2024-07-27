package org.hyrulecraft.dungeon_utils.environment.client.entity.renderer;

import net.fabricmc.api.*;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.environment.client.entity.model.BombEntityModel;
import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;
import org.hyrulecraft.dungeon_utils.environment.common.entity.entitytype.BombEntity;

import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class BombEntityRenderer extends EntityRenderer<BombEntity> {

    private final BombEntityModel<BombEntity> entityModel;

    public BombEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.entityModel = new BombEntityModel<>(ctx.getPart(BombEntityModel.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull BombEntity bombEntity, float yaw, float tickDelta, @NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light) {
        super.render(bombEntity, yaw, tickDelta, matrices, vertexConsumers, light);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.entityModel.getLayer(getTexture(bombEntity)));
        this.entityModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public Identifier getTexture(BombEntity bombEntity) {
        return new Identifier(DungeonUtils.MOD_ID, "textures/item/bomb_flower.png");
    }
}