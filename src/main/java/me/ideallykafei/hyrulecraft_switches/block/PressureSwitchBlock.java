/*package me.ideallykafei.hyrulecraft_switches.block;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class PressureSwitchBlock extends AbstractPressurePlateBlock {
    public static final BooleanProperty POWERED = Properties.POWERED;

    public PressureSwitchBlock() {
        super(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK), BlockSetType.IRON);
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, false));
    }


    @Override
    protected MapCodec<? extends AbstractPressurePlateBlock> getCodec() {
        return null;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getRedstoneOutput(state) > 0 ? PRESSED_SHAPE : DEFAULT_SHAPE;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = this.getRedstoneOutput(state);
        if (i > 0) {
            this.updatePlateState(null, world, pos, state, i);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient) {
            return;
        }
        int i = this.getRedstoneOutput(state);
        if (i == 0) {
            this.updatePlateState(entity, world, pos, state, i);
        }
    }

    private void updatePlateState(@Nullable Entity entity, World world, BlockPos pos, BlockState state, int output) {
        boolean bl2;
        int i = this.getRedstoneOutput(world, pos);
        boolean bl = output > 0;
        boolean bl3 = bl2 = i > 0;
        if (output != i) {
            BlockState blockState = this.setRedstoneOutput(state, i);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            this.updateNeighbors(world, pos);
            world.scheduleBlockRerenderIfNeeded(pos, state, blockState);
        }
        if (!bl2 && bl) {
            world.playSound(null, pos, this.blockSetType.pressurePlateClickOff(), SoundCategory.BLOCKS);
            world.emitGameEvent(entity, GameEvent.BLOCK_DEACTIVATE, pos);
        } else if (bl2 && !bl) {
            world.playSound(null, pos, this.blockSetType.pressurePlateClickOn(), SoundCategory.BLOCKS);
            world.emitGameEvent(entity, GameEvent.BLOCK_ACTIVATE, pos);
        }
        if (bl2) {
            world.scheduleBlockTick(new BlockPos(pos), this, this.getTickRate());
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (moved || state.isOf(newState.getBlock())) {
            return;
        }
        if (this.getRedstoneOutput(state) > 0) {
            this.updateNeighbors(world, pos);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return this.getRedstoneOutput(state);
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (direction == Direction.UP) {
            return this.getRedstoneOutput(state);
        }
        return 0;
    }

    protected static int getEntityCount(World world, Box box, Class<? extends Entity> entityClass) {
        return world.getEntitiesByClass(entityClass, box, EntityPredicates.EXCEPT_SPECTATOR.and(entity -> !entity.canAvoidTraps())).size();
    }

    @Override
    protected int getRedstoneOutput(World world, BlockPos pos) {
        return 0;
    }

    @Override
    protected int getRedstoneOutput(BlockState state) {
        return 0;
    }

    @Override
    protected BlockState setRedstoneOutput(BlockState state, int rsOut) {
        return null;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);

    }
}*/