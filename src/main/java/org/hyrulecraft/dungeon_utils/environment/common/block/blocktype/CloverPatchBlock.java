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
        return Block.createCuboidShape(0, 0, 0, 16, 5.01, 16);
    }
}