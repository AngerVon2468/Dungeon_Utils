package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.bonepile;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.AbstractSelfDroppingBlock;

public abstract class AbstractBonePileBlock extends AbstractSelfDroppingBlock {

    public AbstractBonePileBlock(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
        return Block.createCuboidShape(0, 0, 0, 16, 15.25, 16);
    }
}