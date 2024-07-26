package org.hyrulecraft.dungeon_utils.environment.client.block;

import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.*;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.blockentity.PedestalBlockEntity;

import org.jetbrains.annotations.NotNull;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {

    private final ItemRenderer itemRenderer;

    public PedestalBlockEntityRenderer(BlockEntityRendererFactory. @NotNull Context ctx) {
        itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(@NotNull PedestalBlockEntity blockEntity, float tickDelta, @NotNull MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemStack stack = blockEntity.pedestal_item_id == null ? ItemStack.EMPTY : new ItemStack(RegistryTypes.ITEM.get(Identifier.tryParse(blockEntity.pedestal_item_id)));
        // Mandatory when doing GL calls
        matrices.push();
        // Move the item
        matrices.translate(0.5, 0.25, 0.5);

        // Rotate the item
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90));
        int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
        this.itemRenderer.renderItem(stack, ModelTransformationMode.GROUND, lightAbove, overlay, matrices, vertexConsumers, blockEntity.getWorld(), 0);

        // Mandatory call after GL calls
        matrices.pop();
    }
}