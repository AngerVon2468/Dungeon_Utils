package me.ideallykafei.hyrulecraft_switches.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

import org.jetbrains.annotations.*;

import java.util.stream.Stream;

public class YellowSwitchBlock extends Block {

    public static final BooleanProperty IS_STEPPED_ON = BooleanProperty.of("is_stepped_on");

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public YellowSwitchBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(IS_STEPPED_ON, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
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
    public void onSteppedOn(@NotNull World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.getBlockState(pos).get(IS_STEPPED_ON)){

            world.setBlockState(pos, state.with(IS_STEPPED_ON, true));

        } else {

            world.setBlockState(pos, state.with(IS_STEPPED_ON, false));

        }

        super.onSteppedOn(world, pos, state, entity);
    }
}