package org.hyrulecraft.dungeon_utils.environment.common.fluid;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import org.jetbrains.annotations.NotNull;

public class SpringWaterFluid extends FlowableFluid {

    @Override
    public Fluid getFlowing() {
        return DungeonUtilsFluids.FLOWING_SPRING_WATER;
    }

    @Override
    public Fluid getStill() {
        return DungeonUtilsFluids.STILL_SPRING_WATER;
    }

    @Override
    protected boolean isInfinite(World world) {
        return false;
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess worldAccess, BlockPos blockPos, @NotNull BlockState blockState) {
        final BlockEntity blockEntity = blockState.hasBlockEntity() ? worldAccess.getBlockEntity(blockPos) : null;
        Block.dropStacks(blockState, worldAccess, blockPos, blockEntity);
    }

    @Override
    protected int getFlowSpeed(WorldView worldView) {
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView worldView) {
        return 1;
    }

    @Override
    public Item getBucketItem() {
        return DungeonUtilsFluids.SPRING_WATER_BUCKET;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState fluidState, BlockView blockView, BlockPos blockPos, Fluid fluid, Direction direction) {
        return false;
    }

    @Override
    public int getTickRate(WorldView worldView) {
        return 5;
    }

    @Override
    protected float getBlastResistance() {
        return 100f;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return DungeonUtilsFluids.SPRING_WATER_BLOCK.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    @Override
    public boolean isStill(FluidState fluidState) {
        return false;
    }

    @Override
    public int getLevel(FluidState fluidState) {
        return 0;
    }

    public static class Flowing extends SpringWaterFluid {

        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(@NotNull FluidState state) {
            return state.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends SpringWaterFluid {

        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }
}