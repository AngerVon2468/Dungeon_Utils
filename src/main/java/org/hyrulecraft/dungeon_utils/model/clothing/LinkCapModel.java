package org.hyrulecraft.dungeon_utils.model.clothing;

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
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(31, 4).cuboid(-4.0F, -8.1F, -4.0F, 8.0F, 0.1F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        head.addChild("g_r1", ModelPartBuilder.create().uv(48, 21).cuboid(-1.0F, -3.3714F, 1.0509F, 2.0F, 3.0F, 6.0F, new Dilation(-0.3F)), ModelTransform.of(0.0F, -1.6413F, 7.1915F, -1.1345F, 0.0F, 0.0F));

        head.addChild("f_r1", ModelPartBuilder.create().uv(4, 52).cuboid(-2.0F, -3.2463F, -1.7823F, 4.0F, 5.0F, 5.0F, new Dilation(-0.3F)), ModelTransform.of(0.0F, -1.6413F, 7.1915F, -0.7418F, 0.0F, 0.0F));

        head.addChild("e_r1", ModelPartBuilder.create().uv(37, 49).cuboid(-3.0F, -3.9814F, -6.353F, 6.0F, 6.0F, 6.0F, new Dilation(-0.3F)), ModelTransform.of(0.0F, -1.6413F, 7.1915F, -0.48F, 0.0F, 0.0F));

        head.addChild("c_r1", ModelPartBuilder.create().uv(3, 0).cuboid(-4.0F, -0.1F, -7.0F, 8.0F, 0.1F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -4.0F, 4.0F, -1.5708F, 0.0F, 1.5708F));

        head.addChild("d_r1", ModelPartBuilder.create().uv(3, 14).cuboid(-4.0F, 0.0F, -7.0F, 8.0F, 0.1F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -4.0F, 3.0F, 0.0F, 0.0F, 1.5708F));

        head.addChild("b_r1", ModelPartBuilder.create().uv(3, 32).cuboid(-4.0F, -0.1F, -7.0F, 8.0F, 0.1F, 8.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -4.0F, 3.0F, 0.0F, 0.0F, 1.5708F));
        return TexturedModelData.of(modelData, 64, 64);
    }
}