package org.hyrulecraft.dungeon_utils.item.itemtype.clothing.link;

import dev.emi.trinkets.api.*;
import dev.emi.trinkets.api.client.TrinketRenderer;

import net.fabricmc.api.*;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.DungeonUtils;
import org.hyrulecraft.dungeon_utils.model.clothing.LinkTunicModel;

import org.jetbrains.annotations.*;

import wiiu.mavity.mavity_lib.config.MavityLibConfig;

import java.util.List;

public class TunicTrinketItem extends TrinketItem implements TrinketRenderer {

    private static final Identifier TEXTURE = new Identifier(DungeonUtils.MOD_ID, "textures/item/trinkets/link_tunic.png");

    private BipedEntityModel<LivingEntity> model;

    public TunicTrinketItem(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        BipedEntityModel<LivingEntity> model = this.getModel();
        model.setAngles(entity, limbAngle, limbDistance, animationProgress, animationProgress, headPitch);
        model.animateModel(entity, limbAngle, limbDistance, tickDelta);
        TrinketRenderer.followBodyRotations(entity, model);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(model.getLayer(TEXTURE));
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
    }

    @Environment(EnvType.CLIENT)
    private BipedEntityModel<LivingEntity> getModel() {
        if (this.model == null) {
            this.model = new LinkTunicModel(LinkTunicModel.getTexturedModelData().createModel());
        }
        return this.model;
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext options) {
        if (MavityLibConfig.showTooltips) {
            tooltip.add(Text.translatable("tooltip.dungeon_utils.tunic_trinket_one"));
            super.appendTooltip(stack, world, tooltip, options);
        }
    }
}