package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.Items;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

import java.util.Random;
import java.util.stream.Stream;

public class CloverPatchBlock extends Block {

    public CloverPatchBlock(Settings settings) {
        super(settings);
    }

    /*
    @Override
    public void onSteppedOn(World world, BlockPos blockPos, BlockState blockState, Entity entity) {
        Random random = new Random();
        if (random.nextDouble(0, 5) == 0) {

            entity.dropStack(Items.STICK.getDefaultStack());

        }
    }
    */

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
        return Stream.of(
                Block.createCuboidShape(0, 5, 0, 16, 5.01, 16),
                Block.createCuboidShape(0, 4.5, 0, 16, 4.51, 16),
                Block.createCuboidShape(0, 4, 0, 16, 4.01, 16),
                Block.createCuboidShape(0, 3.5, 0, 16, 3.51, 16),
                Block.createCuboidShape(-0.5, 0, 2, 15.5, 4, 2.01),
                Block.createCuboidShape(0.5, 0, 14, 16.5, 4, 14.01),
                Block.createCuboidShape(2, 0, -0.5, 2.01, 4, 15.5),
                Block.createCuboidShape(14, 0, -0.5, 14.01, 4, 15.5)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    }
}