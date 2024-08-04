package org.hyrulecraft.dungeon_utils.environment.common.fluid;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.particle.Particles;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;

import org.jetbrains.annotations.NotNull;

public class HotSpringWaterFluid extends FlowableFluid {

    @Override
    protected void randomDisplayTick(@NotNull World world, @NotNull BlockPos pos, FluidState fluidState, Random random) {
        java.util.Random javaRandom = new java.util.Random();
        double a = pos.getX() + javaRandom.nextDouble();
        double b = pos.getY() + javaRandom.nextDouble();
        double c = pos.getZ() + javaRandom.nextDouble();
        //
        double d = pos.getX() + javaRandom.nextDouble();
        double e = pos.getY() + javaRandom.nextDouble();
        double f = pos.getZ() - javaRandom.nextDouble();
        //
        double g = pos.getX() + javaRandom.nextDouble();
        double h = pos.getY() - javaRandom.nextDouble();
        double i = pos.getZ() - javaRandom.nextDouble();
        //
        double j = pos.getX() - javaRandom.nextDouble();
        double k = pos.getY() - javaRandom.nextDouble();
        double l = pos.getZ() - javaRandom.nextDouble();
        //
        double m = pos.getX() - javaRandom.nextDouble();
        double n = pos.getY() - javaRandom.nextDouble();
        double o = pos.getZ() + javaRandom.nextDouble();
        //
        double p = pos.getX() - javaRandom.nextDouble();
        double q = pos.getY() - javaRandom.nextDouble();
        double r = pos.getZ() + javaRandom.nextDouble();
        //
        double s = pos.getX() - javaRandom.nextDouble();
        double t = pos.getY() + javaRandom.nextDouble();
        double u = pos.getZ() + javaRandom.nextDouble();
        //
        double v = pos.getX() + javaRandom.nextDouble();
        double w = pos.getY() - javaRandom.nextDouble();
        double x = pos.getZ() + javaRandom.nextDouble();
        //
        double y = pos.getX() - javaRandom.nextDouble();
        double z = pos.getY() + javaRandom.nextDouble();
        double aa = pos.getZ() - javaRandom.nextDouble();
        //
        double ab = pos.getX() - javaRandom.nextDouble();
        double ac = pos.getY() + javaRandom.nextDouble();
        double ad = pos.getZ() - javaRandom.nextDouble();
        //
        world.addParticle(Particles.BUBBLE, a, b, c, 0.0, 0.0, 0.0);
        world.addParticle(Particles.BUBBLE_POP, a, b, c, 0.0, 0.0, 0.0);
        //
        world.addParticle(Particles.BUBBLE, d, e, f, 0.0, 0.0, 0.0);
        world.addParticle(Particles.BUBBLE_POP, d, e, f, 0.0, 0.0, 0.0);
        //
        world.addParticle(Particles.BUBBLE, g, h, i, 0.0, 0.0, 0.0);
        world.addParticle(Particles.BUBBLE_POP, g, h, i, 0.0, 0.0, 0.0);
        //
        world.addParticle(Particles.BUBBLE, j, k, l, 0.0, 0.0, 0.0);
        world.addParticle(Particles.BUBBLE_POP, j, k, l, 0.0, 0.0, 0.0);
        //
        world.addParticle(Particles.BUBBLE, m, n, o, 0.0, 0.0, 0.0);
        world.addParticle(Particles.BUBBLE_POP, m, n, o, 0.0, 0.0, 0.0);
        //
        world.addParticle(Particles.BUBBLE, p, q, r, 0.0, 0.0, 0.0);
        world.addParticle(Particles.BUBBLE_POP, p, q, r, 0.0, 0.0, 0.0);
        //
        world.addParticle(Particles.BUBBLE, s, t, u, 0.0, 0.0, 0.0);
        world.addParticle(Particles.BUBBLE_POP, s, t, u, 0.0, 0.0, 0.0);
        //
        world.addParticle(Particles.BUBBLE, v, w, x, 0.0, 0.0, 0.0);
        world.addParticle(Particles.BUBBLE_POP, v, w, x, 0.0, 0.0, 0.0);
        //
        world.addParticle(Particles.BUBBLE, y, z, aa, 0.0, 0.0, 0.0);
        world.addParticle(Particles.BUBBLE_POP, y, z, aa, 0.0, 0.0, 0.0);
        //
        world.addParticle(Particles.BUBBLE, ab, ac, ad, 0.0, 0.0, 0.0);
        world.addParticle(Particles.BUBBLE_POP, ab, ac, ad, 0.0, 0.0, 0.0);
    }

    @Override
    public Fluid getFlowing() {
        return DungeonUtilsFluids.FLOWING_HOT_SPRING_WATER;
    }

    @Override
    public Fluid getStill() {
        return DungeonUtilsFluids.STILL_HOT_SPRING_WATER;
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
        return DungeonUtilsFluids.HOT_SPRING_WATER_BUCKET;
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
        return DungeonUtilsFluids.HOT_SPRING_WATER_BLOCK.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    @Override
    public boolean isStill(FluidState fluidState) {
        return false;
    }

    @Override
    public int getLevel(FluidState fluidState) {
        return 0;
    }

    public static class Flowing extends HotSpringWaterFluid {

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

    public static class Still extends HotSpringWaterFluid {

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