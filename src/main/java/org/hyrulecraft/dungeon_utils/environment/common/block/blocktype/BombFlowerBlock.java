package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;

public class BombFlowerBlock extends PlantBlock implements Fertilizable {
    public static final int MAX_AGE = 7;
    public static final IntProperty AGE = Properties.AGE_2;
    private static final VoxelShape[] AGE_TO_SHAPE;

    public BombFlowerBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(AGE, 0));
    }

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

    public void randomTick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) {
        if (serverWorld.getBaseLightLevel(blockPos, 0) >= 9) {
            int i = this.getAge(blockState);
            if (i < this.getMaxAge()) {
                float f = getAvailableMoisture(this, serverWorld, blockPos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
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

    protected static float getAvailableMoisture(Block block, BlockView blockView, BlockPos blockPos) {
        float f = 1.0F;
        BlockPos blockPos2 = blockPos.down();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float g = 0.0F;
                BlockState blockState = blockView.getBlockState(blockPos2.add(i, 0, j));
                if (blockState.isOf(Blocks.FARMLAND)) {
                    g = 1.0F;
                    if ((Integer)blockState.get(FarmlandBlock.MOISTURE) > 0) {
                        g = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    g /= 4.0F;
                }

                f += g;
            }
        }

        BlockPos blockPos3 = blockPos.north();
        BlockPos blockPos4 = blockPos.south();
        BlockPos blockPos5 = blockPos.west();
        BlockPos blockPos6 = blockPos.east();
        boolean bl = blockView.getBlockState(blockPos5).isOf(block) || blockView.getBlockState(blockPos6).isOf(block);
        boolean bl2 = blockView.getBlockState(blockPos3).isOf(block) || blockView.getBlockState(blockPos4).isOf(block);
        if (bl && bl2) {
            f /= 2.0F;
        } else {
            boolean bl3 = blockView.getBlockState(blockPos5.north()).isOf(block) || blockView.getBlockState(blockPos6.north()).isOf(block) || blockView.getBlockState(blockPos6.south()).isOf(block) || blockView.getBlockState(blockPos5.south()).isOf(block);
            if (bl3) {
                f /= 2.0F;
            }
        }

        return f;
    }

    public boolean canPlaceAt(BlockState blockState, WorldView worldView, BlockPos blockPos) {
        return (worldView.getBaseLightLevel(blockPos, 0) >= 8 || worldView.isSkyVisible(blockPos)) && super.canPlaceAt(blockState, worldView, blockPos);
    }

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

    static {
        AGE_TO_SHAPE = new VoxelShape[]{Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};
    }

    protected ItemConvertible getSeedsItem() {
        return DungeonUtilsItems.BOMB;
    }
}