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

    public static final IntProperty ITEM = IntProperty.of("item", 1, 3);

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public PedestalBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(ITEM, 1));
    }


    @Override
    public VoxelShape getOutlineShape(@NotNull BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        if (state.get(ITEM) == 2 || state.get(ITEM) == 3) {
            return Stream.of(
                    Block.createCuboidShape(3, 0, 5, 13, 4, 11),
                    Block.createCuboidShape(6, 4, 7.676780000000001, 10, 15, 8.67678),
                    Block.createCuboidShape(4, 15, 7.676780000000001, 5, 16, 8.67678),
                    Block.createCuboidShape(11, 15, 7.676780000000001, 12, 16, 8.67678),
                    Block.createCuboidShape(4, 16, 7.676780000000001, 12, 19, 8.67678),
                    Block.createCuboidShape(12, 14, 7.676780000000001, 14, 18, 8.67678),
                    Block.createCuboidShape(6.5, 19, 7.676780000000001, 9.5, 26, 8.67678),
                    Block.createCuboidShape(2, 14, 7.676780000000001, 4, 18, 8.67678),
                    Block.createCuboidShape(7, 15, 7.676780000000001, 9, 16, 8.67678)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
        } else if (state.get(ITEM) == 1) {

            return Block.createCuboidShape(3, 0, 5, 13, 4, 11);

        } else {

            throw new RuntimeException("A fatal error occurred whilst making the outline and physical shape of the Pedestal Block");

        }
    }


    @Override
    public ActionResult onUse(@NotNull BlockState state, World world, BlockPos pos, @NotNull PlayerEntity player, Hand hand, @NotNull BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(DungeonUtilsItems.THE_MASTER_SWORD) && state.get(ITEM) == 1) {

            player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 2).with(FACING, player.getHorizontalFacing().getOpposite()));
            if (!player.isCreative()) {
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;

        } else if (state.get(ITEM) == 2) {

            player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 1).with(FACING, player.getHorizontalFacing().getOpposite()));
            player.getInventory().insertStack(DungeonUtilsItems.THE_MASTER_SWORD.getDefaultStack());
            return ActionResult.SUCCESS;

        } if (stack.isOf(DungeonUtilsItems.THE_MASTER_SWORD_AWAKENED) && state.get(ITEM) == 1) {

            player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 3).with(FACING, player.getHorizontalFacing().getOpposite()));
            if (!player.isCreative()) {
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;

        } else if (state.get(ITEM) == 3) {

            player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 1).with(FACING, player.getHorizontalFacing().getOpposite()));
            player.getInventory().insertStack(DungeonUtilsItems.THE_MASTER_SWORD_AWAKENED.getDefaultStack());
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