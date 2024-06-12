package org.hyrulecraft.dungeon_utils.entity.renderer;

import net.fabricmc.api.*;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.DungeonUtils;
import org.hyrulecraft.dungeon_utils.entity.CrateEntity;
import org.hyrulecraft.dungeon_utils.entity.model.CrateEntityModel;

import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class CrateEntityRenderer<T extends Entity> extends EntityRenderer<CrateEntity> {

    private final CrateEntityModel<CrateEntity> entityModel;

    public CrateEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.entityModel = new CrateEntityModel<>(ctx.getPart(CrateEntityModel.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull CrateEntity crate, float yaw, float tickDelta, @NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light) {
        super.render(crate, yaw, tickDelta, matrices, vertexConsumers, light);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.entityModel.getLayer(getTexture(crate)));
        this.entityModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public Identifier getTexture(CrateEntity crate) {
        return new Identifier(DungeonUtils.MOD_ID, "textures/block/crate.png");
    }
}