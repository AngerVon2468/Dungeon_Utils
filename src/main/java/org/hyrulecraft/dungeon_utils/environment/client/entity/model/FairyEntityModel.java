package org.hyrulecraft.dungeon_utils.environment.client.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;
import org.hyrulecraft.dungeon_utils.environment.common.entity.entitytype.FairyEntity;

import org.jetbrains.annotations.NotNull;

public class FairyEntityModel extends EntityModel<FairyEntity> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(DungeonUtils.MOD_ID, "fairy"), "main");

	private final ModelPart main;

	public FairyEntityModel(@NotNull ModelPart root) {
		this.main = root.getChild("main");
	}

	public static @NotNull TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -5.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(20, 17).cuboid(-2.0F, -5.0F, -3.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(10, 17).cuboid(-2.0F, -5.0F, 2.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 13).cuboid(2.0F, -5.0F, -2.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(12, 9).cuboid(-3.0F, -5.0F, -2.0F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(12, 4).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 8).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		main.addChild("cube_r1", ModelPartBuilder.create().uv(8, 22).cuboid(0.3725F, -3.5F, 2.163F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(23, 9).cuboid(0.3725F, -9.5F, 4.153F, 0.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(7, 21).cuboid(0.3725F, -2.5F, 2.163F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(1, 2).cuboid(0.3725F, -8.5F, 3.163F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(7, 21).cuboid(0.3725F, -7.5F, 2.163F, 0.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.5268F, 0.0F, 0.7854F, 0.0F));
		main.addChild("cube_r2", ModelPartBuilder.create().uv(8, 21).cuboid(0.0F, -1.5F, -1.495F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 22).cuboid(0.0F, 1.5F, -0.505F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(7, 21).cuboid(0.0F, -0.5F, -1.495F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(8, 22).cuboid(0.0F, 0.5F, 0.495F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.85F, -1.5F, 2.85F, 0.0F, -0.7854F, 0.0F));
		main.addChild("cube_r3", ModelPartBuilder.create().uv(8, 22).cuboid(0.3725F, -2.5F, 2.163F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 22).cuboid(0.3725F, -3.5F, 3.163F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(23, 9).cuboid(0.3725F, -12.5F, 4.153F, 0.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.7F, 2.5F, 1.2268F, 0.0F, 0.7854F, 0.0F));
		main.addChild("cube_r4", ModelPartBuilder.create().uv(13, 22).cuboid(-0.3725F, -4.5F, 4.153F, 0.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.7F, -5.5F, 1.2268F, 0.0F, -0.7854F, 0.0F));
		main.addChild("cube_r5", ModelPartBuilder.create().uv(23, 9).cuboid(0.3725F, -4.5F, 4.153F, 0.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.4F, -6.5F, 1.9268F, 0.0F, 0.7854F, 0.0F));
		main.addChild("cube_r6", ModelPartBuilder.create().uv(13, 22).cuboid(-0.3725F, -4.5F, 4.153F, 0.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(1, 0).cuboid(-0.3725F, -3.5F, 3.163F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(1, 21).cuboid(-0.3725F, -2.5F, 2.163F, 0.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.5F, 0.5268F, 0.0F, -0.7854F, 0.0F));
		main.addChild("cube_r7", ModelPartBuilder.create().uv(13, 22).cuboid(-0.3725F, -4.5F, 4.153F, 0.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.4F, -6.5F, 1.9268F, 0.0F, -0.7854F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void setAngles(FairyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}