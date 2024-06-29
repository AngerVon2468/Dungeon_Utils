package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public abstract class AbstractBonePileBlock extends Block {

    public AbstractBonePileBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
        return Block.createCuboidShape(0, 0, 0, 16, 15.25, 16);
    }
}