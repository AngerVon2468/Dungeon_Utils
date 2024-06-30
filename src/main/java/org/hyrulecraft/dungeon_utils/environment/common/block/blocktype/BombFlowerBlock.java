package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;

import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;

public class BombFlowerBlock extends PlantBlock implements Fertilizable {

    public static final IntProperty AGE = Properties.AGE_2;

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};

    public BombFlowerBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.getStateManager().getDefaultState().with(AGE, 0);
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
        return AGE_TO_SHAPE[this.getAge(blockState)];
    }

    protected boolean canPlantOnTop(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return blockState.isOf(Blocks.FARMLAND);
    }

    public int getMaxAge() {
        return 2;
    }

    public int getAge(BlockState blockState) {
        return blockState.get(AGE);
    }

    public BlockState withAge(int i) {
        return this.getDefaultState().with(AGE, i);
    }

    public final boolean isMature(BlockState blockState) {
        return this.getAge(blockState) >= this.getMaxAge();
    }

    public boolean hasRandomTicks(BlockState blockState) {
        return !this.isMature(blockState);
    }

    @SuppressWarnings("deprecation")
    public void randomTick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) {
        if (serverWorld.getBaseLightLevel(blockPos, 0) >= 9) {
            int i = this.getAge(blockState);
            if (i < this.getMaxAge()) {
                if (random.nextInt(6 / 2) == 0) {
                    serverWorld.setBlockState(blockPos, this.withAge(i + 1), 2);
                }
            }
        }

    }

    public void applyGrowth(World world, BlockPos blockPos, BlockState blockState) {
        int i = this.getAge(blockState) + this.getGrowthAmount(world);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        world.setBlockState(blockPos, this.withAge(i), 2);
    }

    protected int getGrowthAmount(World world) {
        return MathHelper.nextInt(world.random, 2, 5);
    }

    public boolean canPlaceAt(BlockState blockState, WorldView worldView, BlockPos blockPos) {
        return (worldView.getBaseLightLevel(blockPos, 0) >= 8 || worldView.isSkyVisible(blockPos)) && super.canPlaceAt(blockState, worldView, blockPos);
    }

    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState blockState, World world, BlockPos blockPos, Entity entity) {
        if (entity instanceof RavagerEntity && world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
            world.breakBlock(blockPos, true, entity);
        }

        super.onEntityCollision(blockState, world, blockPos, entity);
    }

    public ItemStack getPickStack(BlockView blockView, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(this.getSeedsItem());
    }

    public boolean isFertilizable(WorldView worldView, BlockPos blockPos, BlockState blockState, boolean bl) {
        return !this.isMature(blockState);
    }

    public boolean canGrow(World world, Random random, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    public void grow(ServerWorld serverWorld, Random random, BlockPos blockPos, BlockState blockState) {
        this.applyGrowth(serverWorld, blockPos, blockState);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    protected ItemConvertible getSeedsItem() {
        return DungeonUtilsItems.BOMB;
    }
}