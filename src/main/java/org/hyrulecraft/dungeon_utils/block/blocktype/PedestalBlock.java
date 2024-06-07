package org.hyrulecraft.dungeon_utils.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.sound.DungeonUtilsSounds;
import org.jetbrains.annotations.*;

import java.util.stream.Stream;

public class PedestalBlock extends HorizontalFacingBlock {

    public static final IntProperty ITEM = IntProperty.of("item", 1, 3);

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public PedestalBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(ITEM, 1));
    }

    /*
    @Override
    public VoxelShape getOutlineShape(@NotNull BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        if (!state.get(IS_STEPPED_ON)) {
            return Stream.of(
                    Block.createCuboidShape(0, 0, 0, 16, 0.35, 16),
                    Block.createCuboidShape(4, -1.9, 4, 12, 6.1, 12),
                    Block.createCuboidShape(4, 0, 3, 12, 6, 4),
                    Block.createCuboidShape(3, 0, 4, 5, 6, 12),
                    Block.createCuboidShape(4, 0, 11, 12, 6, 13),
                    Block.createCuboidShape(10, 0, 4, 13, 6, 12)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
        } else if (state.get(IS_STEPPED_ON)) {

            return Block.createCuboidShape(0, 0, 0, 16, 0.25, 16);

        } else {

            throw new RuntimeException("A fatal error occurred whilst making the outline and physical shape of the Pedestal Block");

        }
    }
    */

    @Override
    public ActionResult onUse(@NotNull BlockState state, World world, BlockPos pos, @NotNull PlayerEntity player, Hand hand, @NotNull BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(Items.STICK) && state.get(ITEM) == 1) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            player.playSound(DungeonUtilsSounds.SWITCH, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 2));
            return ActionResult.SUCCESS;

        } else if (stack.isOf(Items.STICK) && state.get(ITEM) == 2) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            player.playSound(DungeonUtilsSounds.SWITCH, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 3));
            return ActionResult.SUCCESS;

        } else if (stack.isOf(Items.STICK) && state.get(ITEM) == 3) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            player.playSound(DungeonUtilsSounds.SWITCH, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(ITEM, 1));
            return ActionResult.SUCCESS;

        } else {

            return ActionResult.FAIL;

        }

    }

    @Override
    public @Nullable BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
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