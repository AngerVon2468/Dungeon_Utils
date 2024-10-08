package org.hyrulecraft.dungeon_utils.environment.client.item.model.clothing;

import net.fabricmc.api.*;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class LinkTunicModel extends BipedEntityModel<LivingEntity> {

	public LinkTunicModel(ModelPart root) {
		super(root);
		this.setVisible(false);
		this.body.visible = true;
	}

	public static TexturedModelData getTexturedModelData() { // TODO: fix the arms, currently using a mix of this model and an armour chestplate.
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(40, 16).cuboid(-4.0F, 4.0F, -2.1F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F))
		.uv(32, 32).cuboid(-4.0F, 2.0F, 2.1F, 8.0F, 10.0F, 0.0F, new Dilation(0.0F))
		.uv(40, 38).cuboid(4.1F, 4.0F, -2.0F, 0.0F, 8.0F, 4.0F, new Dilation(0.0F))
		.uv(32, 38).cuboid(-4.1F, 4.0F, -2.0F, 0.0F, 8.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 16).cuboid(-4.0F, 0.0F, -2.1F, 2.0F, 4.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 4).cuboid(2.0F, 0.0F, -2.1F, 2.0F, 4.0F, 0.0F, new Dilation(0.0F))
		.uv(46, 0).cuboid(-2.0F, 2.0F, -2.1F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(44, 2).cuboid(1.0F, 2.0F, -2.1F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(28, 35).cuboid(-1.0F, 3.0F, -2.1F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 4).cuboid(-4.0F, -0.1F, -2.0F, 2.0F, 0.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.0F, -0.1F, -2.0F, 2.0F, 0.0F, 4.0F, new Dilation(0.0F))
		.uv(32, 2).cuboid(2.0F, 0.0F, 2.1F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(32, 0).cuboid(-4.0F, 0.0F, 2.1F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(50, 12).cuboid(-2.0F, 1.0F, 2.1F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(48, 49).cuboid(1.0F, 1.0F, 2.1F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(44, 0).cuboid(-3.0F, 9.0F, -2.2F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(48, 48).cuboid(-4.0F, 11.0F, -2.2F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(48, 47).cuboid(-2.0F, 8.0F, -2.2F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(48, 46).cuboid(-1.0F, 7.0F, -2.2F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(38, 18).cuboid(0.0F, 5.0F, -2.2F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(48, 45).cuboid(1.0F, 4.0F, -2.2F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(38, 16).cuboid(2.0F, 2.0F, -2.2F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(36, 18).cuboid(3.0F, 0.0F, -2.2F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 2).cuboid(3.0F, -0.2F, -2.0F, 1.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(2.0F, -0.2F, 0.0F, 1.0F, 0.0F, 2.0F, new Dilation(0.0F))
		.uv(36, 16).cuboid(2.0F, 0.0F, 2.2F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(48, 44).cuboid(1.0F, 2.0F, 2.2F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 2).cuboid(0.0F, 3.0F, 2.2F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(48, 15).cuboid(-1.0F, 5.0F, 2.2F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(48, 14).cuboid(-2.0F, 6.0F, 2.2F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-3.0F, 7.0F, 2.2F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
		.uv(48, 13).cuboid(-4.0F, 9.0F, 2.2F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(28, 31).cuboid(-4.2F, 10.0F, 0.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 31).cuboid(-4.2F, 11.0F, -2.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(48, 12).cuboid(0.0F, 8.0F, -2.2F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(46, 3).cuboid(3.0F, 10.0F, -2.2F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 35).cuboid(1.0F, 9.0F, -2.2F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(28, 30).cuboid(4.2F, 11.0F, -2.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 30).cuboid(4.2F, 10.0F, 0.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(28, 34).cuboid(2.0F, 9.0F, 2.2F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 34).cuboid(0.0F, 8.0F, 2.2F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(46, 2).cuboid(-1.0F, 7.0F, 2.2F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(12, 32).cuboid(-4.0F, -8.0F, 1.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.2F, 19.3F, 2.0F, 0.0F, -1.5708F, 0.1571F));
		ModelPartData cube_r2 = body.addChild("cube_r2", ModelPartBuilder.create().uv(24, 4).cuboid(-4.0F, -8.0F, 1.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.2F, 19.0F, 2.0F, 0.0F, -1.5708F, -0.1571F));
		ModelPartData cube_r3 = body.addChild("cube_r3", ModelPartBuilder.create().uv(40, 24).cuboid(-4.0F, -4.0F, 1.0F, 8.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.35F, 1.6F, 0.1571F, 0.0F, 0.0F));
		ModelPartData cube_r4 = body.addChild("cube_r4", ModelPartBuilder.create().uv(40, 28).cuboid(-4.0F, -8.0F, 1.0F, 8.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 19.0F, -4.1F, -0.1571F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
}