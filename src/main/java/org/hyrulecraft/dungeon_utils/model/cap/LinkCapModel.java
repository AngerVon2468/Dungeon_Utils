package org.hyrulecraft.dungeon_utils.model.cap;

import net.fabricmc.api.*;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class LinkCapModel extends BipedEntityModel<LivingEntity> {

	public LinkCapModel(ModelPart root) {
		super(root);
		this.setVisible(false);
		this.head.visible = true;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.of(0.0F, 30.0F, 9.0F, -1.1345F, 0.0F, 0.0F));

		head.addChild("c_r1", ModelPartBuilder.create().uv(-4, -4).cuboid(-1.0F, -4.5F, -2.0F, 2.0F, 3.0F, 6.0F, new Dilation(-0.3F)), ModelTransform.of(0.0F, 0.0F, -3.0F, -0.1745F, 0.0F, 0.0F));

		head.addChild("b_r1", ModelPartBuilder.create().uv(-4, -3).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 5.0F, 5.0F, new Dilation(-0.3F)), ModelTransform.of(0.0F, 1.0F, -5.0F, 0.2182F, 0.0F, 0.0F));

		head.addChild("a_r1", ModelPartBuilder.create().uv(-8, -6).cuboid(-3.0F, -4.0F, -7.0F, 6.0F, 6.0F, 8.0F, new Dilation(-0.3F)), ModelTransform.of(0.0F, -1.0F, -7.0F, 0.48F, 0.0F, 0.0F));

		ModelPartData outer = head.addChild("outer", ModelPartBuilder.create().uv(-9, -6).cuboid(-4.0F, -8.1F, -4.0F, 8.0F, 0.1F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		outer.addChild("h_r1", ModelPartBuilder.create().uv(-9, -6).cuboid(-4.0F, -0.1F, -7.0F, 8.0F, 0.1F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -4.0F, 4.0F, -1.5708F, 0.0F, 1.5708F));

		outer.addChild("w_r1", ModelPartBuilder.create().uv(-9, -6).cuboid(-4.0F, 0.0F, -7.0F, 8.0F, 0.1F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -4.0F, 3.0F, 0.0F, 0.0F, 1.5708F));

		outer.addChild("f_r1", ModelPartBuilder.create().uv(-9, -6).cuboid(-4.0F, -0.1F, -7.0F, 8.0F, 0.1F, 8.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -4.0F, 3.0F, 0.0F, 0.0F, 1.5708F));
		return TexturedModelData.of(modelData, 16, 16);
	}
}