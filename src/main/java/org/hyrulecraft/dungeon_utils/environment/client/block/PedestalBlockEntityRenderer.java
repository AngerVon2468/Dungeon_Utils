package org.hyrulecraft.dungeon_utils.environment.client.block;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.*;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryTypes;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;

import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks;
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.blockentity.PedestalBlockEntity;

import org.jetbrains.annotations.NotNull;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {

    public ItemStack stack;

    private final ItemRenderer itemRenderer;

    public PedestalBlockEntityRenderer(BlockEntityRendererFactory. @NotNull Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(@NotNull PedestalBlockEntity blockEntity, float tickDelta, @NotNull MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        if (blockEntity.pedestal_item_id != null) {

            String[] parts = blockEntity.pedestal_item_id.split(":");
            if (!parts[0].contains("minecraft")) {

                this.stack = new ItemStack(RegistryTypes.ITEM.get(new Identifier(parts[0], parts[1])));

            } else {

                this.stack = new ItemStack(RegistryTypes.ITEM.get(new Identifier(parts[1])));

            }

        } else {

            this.stack = ItemStack.EMPTY;

        }

        // Mandatory when doing GL calls
        matrices.push();

        // Move the item
        matrices.translate(0.5, 1.25, 0.5);

        // Rotate the item
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));
        BlockState state = blockEntity.getWorld().getBlockState(blockEntity.getPos());
        if (state.isOf(DungeonUtilsBlocks.PEDESTAL_BLOCK)) {

            if (state.get(Properties.HORIZONTAL_FACING) == Direction.NORTH || state.get(Properties.HORIZONTAL_FACING) == Direction.SOUTH) {

                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.get(Properties.HORIZONTAL_FACING).asRotation()));

            } else if (state.get(Properties.HORIZONTAL_FACING) == Direction.EAST || state.get(Properties.HORIZONTAL_FACING) == Direction.WEST) {

                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-state.get(Properties.HORIZONTAL_FACING).asRotation()));

            }

        }

        // Render the item
        int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
        if (this.stack != null) {

            this.itemRenderer.renderItem(this.stack, ModelTransformationMode.GROUND, lightAbove, overlay, matrices, vertexConsumers, blockEntity.getWorld(), 0);

        }

        // Mandatory call after GL calls
        matrices.pop();

    }
}