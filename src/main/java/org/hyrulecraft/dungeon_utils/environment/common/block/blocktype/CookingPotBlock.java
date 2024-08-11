package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlockEntities;
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.blockentity.CookingPotBlockEntity;

import org.jetbrains.annotations.*;

import java.util.stream.Stream;

public class CookingPotBlock extends BlockWithEntity {

    public CookingPotBlockEntity blockEntity;

    public CookingPotBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
        this.blockEntity = new CookingPotBlockEntity(blockPos, blockState);
        return this.blockEntity;
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull World world, BlockState blockState, BlockEntityType<T> type) {
        return world.isClient() || this.blockEntity == null ? null : checkType(type, DungeonUtilsBlockEntities.COOKING_POT_BLOCK_ENTITY, this.blockEntity::serverTick);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
        return Stream.of(
                Block.createCuboidShape(1, 8, 1, 15, 10, 3),
                Block.createCuboidShape(1, 8, 13, 15, 10, 15),
                Block.createCuboidShape(13, 8, 1, 15, 10, 15),
                Block.createCuboidShape(1, 8, 1, 3, 10, 15),
                Block.createCuboidShape(1, 7, 1, 15, 8, 15),
                Block.createCuboidShape(0, 6, 0, 16, 7, 16),
                Block.createCuboidShape(0, 0, 0, 2, 9, 2),
                Block.createCuboidShape(14, 0, 0, 16, 9, 2),
                Block.createCuboidShape(0, 0, 14, 2, 9, 16),
                Block.createCuboidShape(14, 0, 14, 16, 9, 16)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onStateReplaced(@NotNull BlockState state, World world, BlockPos pos, @NotNull BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CookingPotBlockEntity) {
                // implement itemscatterer here later
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }
}