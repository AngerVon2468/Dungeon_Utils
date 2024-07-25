package org.hyrulecraft.dungeon_utils.environment.client.block;

import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.*;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.*;
import net.minecraft.util.math.RotationAxis;

import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.blockentity.PedestalBlockEntity;
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;

import org.jetbrains.annotations.NotNull;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {

    private static final ItemStack STACK = new ItemStack(DungeonUtilsItems.THE_MASTER_SWORD, 1);

    private final ItemRenderer itemRenderer;

    public PedestalBlockEntityRenderer(BlockEntityRendererFactory. @NotNull Context ctx) {
        itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(@NotNull PedestalBlockEntity blockEntity, float tickDelta, @NotNull MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        // Mandatory when doing GL calls
        matrices.push();
        // Calculate the current offset in the y value
        double offset = Math.sin((blockEntity.getWorld().getTime() + tickDelta) / 8.0) / 4.0;
        // Move the item
        matrices.translate(0.5, 1.25 + offset, 0.5);

        // Rotate the item
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((blockEntity.getWorld().getTime() + tickDelta) * 4));
        int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
        this.itemRenderer.renderItem(STACK, ModelTransformationMode.GROUND, lightAbove, overlay, matrices, vertexConsumers, blockEntity.getWorld(), 0);

        // Mandatory call after GL calls
        matrices.pop();
    }
}