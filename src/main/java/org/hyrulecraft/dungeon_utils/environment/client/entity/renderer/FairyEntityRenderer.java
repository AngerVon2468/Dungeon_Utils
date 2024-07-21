package org.hyrulecraft.dungeon_utils.environment.client.entity.renderer;

import net.minecraft.client.render.entity.*;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.environment.client.entity.model.FairyEntityModel;
import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;
import org.hyrulecraft.dungeon_utils.environment.common.entity.entity_type.FairyEntity;

public class FairyEntityRenderer extends MobEntityRenderer<FairyEntity, FairyEntityModel> {

    public FairyEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new FairyEntityModel(context.getPart(FairyEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public Identifier getTexture(FairyEntity entity) {
        return new Identifier(DungeonUtils.MOD_ID, "textures/entity/fairy.png");
    }
}