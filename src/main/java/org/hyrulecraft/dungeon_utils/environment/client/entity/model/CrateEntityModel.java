package org.hyrulecraft.dungeon_utils.environment.client.entity.model;

import net.fabricmc.api.*;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;
import org.hyrulecraft.dungeon_utils.environment.common.entity.entitytype.CrateEntity;

import org.jetbrains.annotations.NotNull;

/**
 * {@link CrateEntityModel<T>}<br>
 * This link is to avoid a stupid warning that I can't be stuffed to remove.
 */
@Environment(EnvType.CLIENT)
public class CrateEntityModel<T extends CrateEntity> extends EntityModel<CrateEntity> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(DungeonUtils.MOD_ID, "crate"), "main");

	private final ModelPart main;

	public CrateEntityModel(@NotNull ModelPart root) {
		this.main = root.getChild("main");
	}

	@SuppressWarnings("unused")
	public static @NotNull TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0f, -24.0f, -8.0f, 16.0f, 16.0f, 16.0f, new Dilation(0.0f)), ModelTransform.pivot(0.0f, 24.0f, 0.0f));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(CrateEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		this.main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}