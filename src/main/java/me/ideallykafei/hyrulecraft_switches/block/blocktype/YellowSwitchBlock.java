package me.ideallykafei.hyrulecraft_switches.block.blocktype;

import me.ideallykafei.hyrulecraft_switches.HyrulecraftSwitches;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

import org.jetbrains.annotations.*;

public class YellowSwitchBlock extends Block {

    public static final BooleanProperty IS_STEPPED_ON = BooleanProperty.of("is_stepped_on");

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public YellowSwitchBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(IS_STEPPED_ON, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube(); // Change later
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
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.getBlockState(pos).get(IS_STEPPED_ON)){
            // Summoning the Lighting Bolt at the block as a test
            LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(world);
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos));
            world.spawnEntity(lightningEntity);
            world.setBlockState(pos, state.with(IS_STEPPED_ON, true));
        } else {

            HyrulecraftSwitches.LOGGER.info("lol anti crashing");

        }

        world.setBlockState(pos, state.with(IS_STEPPED_ON, true));
        super.onSteppedOn(world, pos, state, entity);
    }
}