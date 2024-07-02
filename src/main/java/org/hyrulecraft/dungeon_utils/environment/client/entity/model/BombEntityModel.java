package org.hyrulecraft.dungeon_utils.environment.client.entity.model;

import net.fabricmc.api.*;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;
import org.hyrulecraft.dungeon_utils.environment.common.entity.BombEntity;

import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class BombEntityModel<T extends BombEntity> extends EntityModel<BombEntity> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(DungeonUtils.MOD_ID, "bomb"), "main");

	private final ModelPart main;

	public BombEntityModel(@NotNull ModelPart root) {
		this.main = root.getChild("main");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().uv(22, 20).cuboid(-3.0f, 0.0f, -3.0f, 6.0f, 8.0f, 6.0f, new Dilation(0.01f))
		.uv(22, 14).cuboid(-1.5f, 8.0f, -1.5f, 3.0f, 1.0f, 3.0f, new Dilation(0.01f))
		.uv(0, 14).cuboid(-4.0f, 1.0f, -3.0f, 8.0f, 6.0f, 6.0f, new Dilation(0.01f))
		.uv(0, 0).cuboid(-3.0f, 1.0f, -4.0f, 6.0f, 6.0f, 8.0f, new Dilation(0.01f)), ModelTransform.pivot(0.0f, 0.0f, 0.0f));

		main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5f, 9.0f, 0.0f, 3.0f, 4.0f, 0.01f, new Dilation(0.01f)), ModelTransform.of(0.0f, 0.0f, 0.0f, 0.0f, 0.7854f, 0.0f));
		main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5f, 9.0f, 11.25f, 3.0f, 4.0f, 0.01f, new Dilation(0.01f)), ModelTransform.of(8.0f, 0.0f, -8.0f, 0.0f, -0.7854f, 0.0f));
		main.addChild("cube_r3", ModelPartBuilder.create().uv(13, 0).cuboid(-3.5f, 8.5f, -3.5f, 7.0f, 0.01f, 7.0f, new Dilation(0.01f)), ModelTransform.of(0.0f, 0.0f, 0.0f, 0.0f, -0.7854f, 0.0f));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(BombEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}