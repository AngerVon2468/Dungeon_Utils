package org.hyrulecraft.dungeon_utils.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;
import org.jetbrains.annotations.*;

import java.util.stream.Stream;

public class PedestalBlock extends HorizontalFacingBlock {

    public static final IntProperty ITEM = IntProperty.of("item", 1, 5);

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public PedestalBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(ITEM, 1));
    }


    @Override
    public VoxelShape getOutlineShape(@NotNull BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        if ((state.get(ITEM) == 2 || state.get(ITEM) == 3) && (state.get(FACING) == Direction.NORTH || state.get(FACING) == Direction.SOUTH)) {

            return Stream.of(
                    Block.createCuboidShape(3, 0, 5, 13, 4, 11),
                    Block.createCuboidShape(5.75, 4, 7.676780000000001, 10.25, 15, 8.67678),
                    Block.createCuboidShape(6.5, 19, 7.676780000000001, 9.5, 26, 8.67678),
                    Block.createCuboidShape(4, 15.75, 7.676780000000001, 12, 19, 8.67678),
                    Block.createCuboidShape(4, 14.75, 7.676780000000001, 5, 15.75, 8.67678),
                    Block.createCuboidShape(12, 14.25, 7.676780000000001, 13.75, 17.75, 8.67678),
                    Block.createCuboidShape(11, 14.75, 7.676780000000001, 12, 15.75, 8.67678),
                    Block.createCuboidShape(2.25, 14.25, 7.676780000000001, 4, 17.75, 8.67678),
                    Block.createCuboidShape(6.5, 15, 7.676780000000001, 9.5, 15.75, 8.67678)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if ((state.get(ITEM) == 2 || state.get(ITEM) == 3) && (state.get(FACING) == Direction.EAST || state.get(FACING) == Direction.WEST)) {

            return Stream.of(
                    Block.createCuboidShape(4.91161, 0, 3.0883900000000004, 10.91161, 4, 13.08839),
                    Block.createCuboidShape(7.58839, 4, 5.83839, 8.58839, 15, 10.33839),
                    Block.createCuboidShape(7.58839, 19, 6.58839, 8.58839, 26, 9.58839),
                    Block.createCuboidShape(7.58839, 15.75, 4.08839, 8.58839, 19, 12.08839),
                    Block.createCuboidShape(7.58839, 14.75, 11.08839, 8.58839, 15.75, 12.08839),
                    Block.createCuboidShape(7.58839, 14.25, 2.3383900000000004, 8.58839, 17.75, 4.08839),
                    Block.createCuboidShape(7.58839, 14.75, 4.08839, 8.58839, 15.75, 5.08839),
                    Block.createCuboidShape(7.58839, 14.25, 12.08839, 8.58839, 17.75, 13.83839),
                    Block.createCuboidShape(7.58839, 15, 6.58839, 8.58839, 15.75, 9.58839)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if ((state.get(ITEM) == 4 || state.get(ITEM) == 5) && (state.get(FACING) == Direction.NORTH || state.get(FACING) == Direction.SOUTH)) {

            return Stream.of(
                    Block.createCuboidShape(3, 0, 5, 13, 4, 11),
                    Block.createCuboidShape(5.75, 4, 7.5, 10.1, 12.8, 8.5),
                    Block.createCuboidShape(6.05, 12.8, 7.5, 9.799999999999999, 13.8, 8.5),
                    Block.createCuboidShape(5.05, 13.8, 7.5, 10.799999999999999, 16.8, 8.5),
                    Block.createCuboidShape(2.3, 13.200000000000001, 7.5, 5.049999999999999, 16.1, 8.5),
                    Block.createCuboidShape(5.05, 13.250000000000002, 7.5, 5.699999999999999, 13.8, 8.5),
                    Block.createCuboidShape(10.15, 13.250000000000002, 7.5, 10.799999999999999, 13.8, 8.5),
                    Block.createCuboidShape(10.8, 13.200000000000001, 7.5, 13.549999999999999, 16.1, 8.5),
                    Block.createCuboidShape(6.449999999999999, 16.8, 7.5, 9.45, 19.5, 8.5),
                    Block.createCuboidShape(5.749999999999999, 19.5, 7.5, 10.05, 23.2, 8.5)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if ((state.get(ITEM) == 4 || state.get(ITEM) == 5) && (state.get(FACING) == Direction.EAST || state.get(FACING) == Direction.WEST)) {

            return Stream.of(
                    Block.createCuboidShape(4.925000000000001, 0, 2.9250000000000007, 10.925, 4, 12.925),
                    Block.createCuboidShape(7.425000000000001, 4, 5.825000000000001, 8.425, 12.8, 10.175),
                    Block.createCuboidShape(7.425000000000001, 12.8, 6.125000000000002, 8.425, 13.8, 9.875),
                    Block.createCuboidShape(7.425000000000001, 13.8, 5.125000000000002, 8.425, 16.8, 10.875),
                    Block.createCuboidShape(7.425000000000001, 13.200000000000001, 10.875000000000002, 8.425, 16.1, 13.625),
                    Block.createCuboidShape(7.425000000000001, 13.250000000000002, 10.225000000000001, 8.425, 13.8, 10.875),
                    Block.createCuboidShape(7.425000000000001, 13.250000000000002, 5.125000000000002, 8.425, 13.8, 5.775),
                    Block.createCuboidShape(7.425000000000001, 13.200000000000001, 2.3750000000000018, 8.425, 16.1, 5.125),
                    Block.createCuboidShape(7.425000000000001, 16.8, 6.475000000000001, 8.425, 19.5, 9.475000000000001),
                    Block.createCuboidShape(7.425000000000001, 19.5, 5.875, 8.425, 23.2, 10.175)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(ITEM) == 1 && (state.get(FACING) == Direction.NORTH || state.get(FACING) == Direction.SOUTH)) {

            return Block.createCuboidShape(3, 0, 5, 13, 4, 11);

        } else if (state.get(ITEM) == 1 && (state.get(FACING) == Direction.EAST || state.get(FACING) == Direction.WEST)) {

            return Block.createCuboidShape(5, 0, 3, 11, 4, 13);

        } else {

            throw new RuntimeException("A fatal error occurred whilst making the outline and physical shape of the Pedestal Block");

        }
    }


    @Override
    public ActionResult onUse(@NotNull BlockState state, World world, BlockPos pos, @NotNull PlayerEntity player, Hand hand, @NotNull BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(DungeonUtilsItems.THE_MASTER_SWORD) && state.get(ITEM) == 1) {

            player.playSound(SoundEvents.BLOCK_STONE_FALL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 2));
            if (!player.isCreative()) {
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;

        } else if (state.get(ITEM) == 2) {

            player.playSound(SoundEvents.BLOCK_STONE_FALL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 1));
            player.getInventory().insertStack(DungeonUtilsItems.THE_MASTER_SWORD.getDefaultStack());
            return ActionResult.SUCCESS;

        } else if (stack.isOf(DungeonUtilsItems.THE_MASTER_SWORD_AWAKENED) && state.get(ITEM) == 1) {

            player.playSound(SoundEvents.BLOCK_STONE_FALL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 3));
            if (!player.isCreative()) {
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;

        } else if (state.get(ITEM) == 3) {

            player.playSound(SoundEvents.BLOCK_STONE_FALL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 1));
            player.getInventory().insertStack(DungeonUtilsItems.THE_MASTER_SWORD_AWAKENED.getDefaultStack());
            return ActionResult.SUCCESS;

        } else if (stack.isOf(Items.DIAMOND_SWORD) && state.get(ITEM) == 1) {

            player.playSound(SoundEvents.BLOCK_STONE_FALL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 4));
            if (!player.isCreative()) {
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;

        } else if (state.get(ITEM) == 4) {

            player.playSound(SoundEvents.BLOCK_STONE_FALL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 1));
            player.getInventory().insertStack(Items.DIAMOND_SWORD.getDefaultStack());
            return ActionResult.SUCCESS;

        } else if (stack.isOf(Items.NETHERITE_SWORD) && state.get(ITEM) == 1) {

            player.playSound(SoundEvents.BLOCK_STONE_FALL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 5));
            if (!player.isCreative()) {
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;

        } else if (state.get(ITEM) == 5) {

            player.playSound(SoundEvents.BLOCK_STONE_FALL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 1));
            player.getInventory().insertStack(Items.NETHERITE_SWORD.getDefaultStack());
            return ActionResult.SUCCESS;

        } else {

            return ActionResult.FAIL;

        }

    }

    @Override
    public @Nullable BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(@NotNull BlockState state, @NotNull BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(@NotNull BlockState state, @NotNull BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(@NotNull StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, ITEM});
    }

    @Override
    public boolean canPlaceAt(BlockState state, @NotNull WorldView world, @NotNull BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.down());

        return !blockState.isOf(Blocks.AIR) && !blockState.isOf(Blocks.GRASS) && !blockState.isIn(BlockTags.FLOWERS) && !blockState.isIn(BlockTags.SMALL_FLOWERS) && !blockState.isIn(BlockTags.TALL_FLOWERS);
    }
}