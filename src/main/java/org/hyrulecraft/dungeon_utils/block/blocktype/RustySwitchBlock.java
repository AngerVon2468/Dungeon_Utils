package org.hyrulecraft.dungeon_utils.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

import org.hyrulecraft.dungeon_utils.DungeonUtils;
import org.hyrulecraft.dungeon_utils.sound.SoundInit;

import org.jetbrains.annotations.*;

import java.util.stream.Stream;

public class RustySwitchBlock extends HorizontalFacingBlock {

    public static final BooleanProperty IS_STEPPED_ON = BooleanProperty.of("is_stepped_on");

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public RustySwitchBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(IS_STEPPED_ON, false));
    }

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

            DungeonUtils.LOGGER.info("You messed up!");
            return VoxelShapes.fullCube();

        }
    }

    @Override
    public @Nullable BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()).with(IS_STEPPED_ON, false);
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
        builder.add(new Property[]{FACING, IS_STEPPED_ON});
    }

    @Override
    public boolean canPlaceAt(BlockState state, @NotNull WorldView world, @NotNull BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.down());

        if (!blockState.isOf(Blocks.AIR) && !blockState.isOf(Blocks.GRASS) && !blockState.isIn(BlockTags.FLOWERS) && !blockState.isIn(BlockTags.SMALL_FLOWERS) && !blockState.isIn(BlockTags.TALL_FLOWERS)) {

            return true;

        } else {

            return false;

        }
    }

    @Override
    public ActionResult onUse(@NotNull BlockState state, World world, BlockPos pos, @NotNull PlayerEntity player, Hand hand, @NotNull BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(Items.NETHER_STAR)) { // Replace with hammer

            player.playSound(SoundInit.getSWITCH(), 1.0f, 1.0f);
            world.setBlockState(pos, state.with(IS_STEPPED_ON, true));
            this.updateNeighbors(world, pos);
            return ActionResult.SUCCESS;

        } else {

            world.setBlockState(pos, state.with(IS_STEPPED_ON, false));
            return ActionResult.FAIL;

        }
    }

    @Override
    public int getWeakRedstonePower(@NotNull BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(IS_STEPPED_ON) ? 15 : 0;
    }

    @Override
    public int getStrongRedstonePower(@NotNull BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(IS_STEPPED_ON) ? 15 : 0;
    }

    @Override
    public boolean emitsRedstonePower(@NotNull BlockState state) {
        return state.get(IS_STEPPED_ON);
    }

    protected void updateNeighbors(@NotNull World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.down(), this);
    }
}