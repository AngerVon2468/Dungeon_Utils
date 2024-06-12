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
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(31, 4).cuboid(-4.0f, -8.1f, -4.0f, 8.0f, 0.1f, 8.0f, new Dilation(0.0f)), ModelTransform.pivot(0.0f, 24.0f, 0.0f));

        head.addChild("g_r1", ModelPartBuilder.create().uv(48, 21).cuboid(-1.0f, -3.3714f, 1.0509f, 2.0f, 3.0f, 6.0f, new Dilation(-0.3f)), ModelTransform.of(0.0f, -1.6413f, 7.1915f, -1.1345f, 0.0f, 0.0f));
        head.addChild("f_r1", ModelPartBuilder.create().uv(4, 52).cuboid(-2.0f, -3.2463f, -1.7823f, 4.0f, 5.0f, 5.0f, new Dilation(-0.3f)), ModelTransform.of(0.0f, -1.6413f, 7.1915f, -0.7418f, 0.0f, 0.0f));
        head.addChild("e_r1", ModelPartBuilder.create().uv(37, 49).cuboid(-3.0f, -3.9814f, -6.353f, 6.0f, 6.0f, 6.0f, new Dilation(-0.3f)), ModelTransform.of(0.0f, -1.6413f, 7.1915f, -0.48f, 0.0f, 0.0f));
        head.addChild("c_r1", ModelPartBuilder.create().uv(3, 0).cuboid(-4.0f, -0.1f, -7.0f, 8.0f, 0.1f, 8.0f, new Dilation(0.0f)), ModelTransform.of(-3.0f, -4.0f, 4.0f, -1.5708f, 0.0f, 1.5708F));
        head.addChild("d_r1", ModelPartBuilder.create().uv(3, 14).cuboid(-4.0f, 0.0f, -7.0f, 8.0f, 0.1f, 8.0f, new Dilation(0.0f)), ModelTransform.of(-4.0f, -4.0f, 3.0f, 0.0f, 0.0f, 1.5708f));
        head.addChild("b_r1", ModelPartBuilder.create().uv(3, 32).cuboid(-4.0f, -0.1f, -7.0f, 8.0f, 0.1f, 8.0f, new Dilation(0.0f)), ModelTransform.of(4.0f, -4.0f, 3.0f, 0.0f, 0.0f, 1.5708f));
        return TexturedModelData.of(modelData, 64, 64);
    }
}