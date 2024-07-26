package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlockEntities;
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.blockentity.PedestalBlockEntity;

import org.jetbrains.annotations.*;

public class PedestalBlock extends BlockWithEntity {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public PedestalBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(@NotNull BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        if ((state.get(FACING) == Direction.NORTH || state.get(FACING) == Direction.SOUTH)) {

            return Block.createCuboidShape(3, 0, 5, 13, 4, 11);

        } else if ((state.get(FACING) == Direction.EAST || state.get(FACING) == Direction.WEST)) {

            return Block.createCuboidShape(5, 0, 3, 11, 4, 13);

        } else {

            throw new RuntimeException("A fatal error occurred whilst making the outline and physical shape of the Pedestal Block");

        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(@NotNull BlockState state, @NotNull World world, BlockPos pos, @NotNull PlayerEntity player, Hand hand, @NotNull BlockHitResult hit) {
        ItemStack stack = player.getMainHandStack();
        if (!world.isClient()) {
            BlockEntity pedestal = world.getBlockEntity(hit.getBlockPos());
            if (pedestal instanceof PedestalBlockEntity pedestalBlockEntity && stack.getItem() instanceof SwordItem) {
                String a = stack.getItem().getTranslationKey().replace("item.", "");
                String b = a.replace(".", ":");
                pedestalBlockEntity.pedestal_item_id = b;
                DungeonUtils.LOGGER.info(b);
                stack.decrement(1);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(@NotNull BlockState state, @NotNull BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(@NotNull BlockState state, @NotNull BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(@NotNull StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, @NotNull WorldView world, @NotNull BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.down());

        return !blockState.isOf(Blocks.AIR) && !blockState.isOf(Blocks.GRASS) && !blockState.isIn(BlockTags.FLOWERS) && !blockState.isIn(BlockTags.SMALL_FLOWERS) && !blockState.isIn(BlockTags.TALL_FLOWERS);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PedestalBlockEntity(blockPos, blockState);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull World world, BlockState blockState, BlockEntityType<T> type) {
        return world.isClient() ? null : checkType(type, DungeonUtilsBlockEntities.PEDESTAL_BLOCK_ENTITY, PedestalBlockEntity::serverTick);
    }
}