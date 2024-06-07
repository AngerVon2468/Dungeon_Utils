package org.hyrulecraft.dungeon_utils.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;

import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.sound.DungeonUtilsSounds;

import org.jetbrains.annotations.*;

import java.util.stream.Stream;

public class BlueSwitchBlock extends HorizontalFacingBlock {

    public static final BooleanProperty IS_STEPPED_ON = BooleanProperty.of("is_stepped_on");

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public BlueSwitchBlock(Settings settings) {
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

            throw new RuntimeException("A fatal error occurred whilst making the outline and physical shape of the Blue Switch Block");

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

        return !blockState.isOf(Blocks.AIR) && !blockState.isOf(Blocks.GRASS) && !blockState.isIn(BlockTags.FLOWERS) && !blockState.isIn(BlockTags.SMALL_FLOWERS) && !blockState.isIn(BlockTags.TALL_FLOWERS);
    }

    @Override
    public ActionResult onUse(@NotNull BlockState state, World world, BlockPos pos, @NotNull PlayerEntity player, Hand hand, @NotNull BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(DungeonUtilsItems.MEGATON_HAMMER)) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            player.playSound(DungeonUtilsSounds.SWITCH, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(IS_STEPPED_ON, true));
            this.updateNeighbors(world, pos);
            return ActionResult.SUCCESS;

        } else {

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

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int redstoneOutput = this.getRedstoneOutput(state);
        if (redstoneOutput > 0) {
            this.updatePlateState(null, world, pos, state, redstoneOutput);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, @NotNull World world, BlockPos pos, Entity entity) {
        if (world.isClient) {
            return;
        }
        int redstoneOutput = this.getRedstoneOutput(state);
        if (redstoneOutput == 0) {
            this.updatePlateState(entity, world, pos, state, redstoneOutput);
        }
    }

    private void updatePlateState(@Nullable Entity entity, World world, BlockPos pos, BlockState state, int output) {
        int activationFromEntites = this.getRedstoneOutput(world, pos);
        boolean isActivated = output > 0;
        boolean willBeActivatedFromEntites = activationFromEntites > 0;
        if (output != activationFromEntites) {
            BlockState blockState = this.setRedstoneOutput(state, activationFromEntites);
            world.playSound(null, pos, DungeonUtilsSounds.SWITCH, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            this.updateNeighbors(world, pos);
            world.scheduleBlockRerenderIfNeeded(pos, state, blockState);
        }
        if (!willBeActivatedFromEntites && isActivated) {
            world.playSound(null, pos, DungeonUtilsSounds.SWITCH, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.emitGameEvent(entity, GameEvent.BLOCK_DEACTIVATE, pos);
        } else if (willBeActivatedFromEntites && !isActivated) {
            world.playSound(null, pos, DungeonUtilsSounds.SWITCH, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.emitGameEvent(entity, GameEvent.BLOCK_ACTIVATE, pos);
        }
        if (willBeActivatedFromEntites) {
            world.scheduleBlockTick(new BlockPos(pos), this, this.getTickRate());
        }
    }

    protected int getTickRate() {
        return 20;
    }

    protected int getRedstoneOutput(@NotNull BlockState state) {
        return state.get(IS_STEPPED_ON) ? 15 : 0;
    }

    protected BlockState setRedstoneOutput(@NotNull BlockState state, int rsOut) {
        return state.with(IS_STEPPED_ON, rsOut > 0);
    }

    protected static final Box BOX = new Box(0.0625, 0.0, 0.0625, 0.9375, 0.7, 0.9375);
    protected int getRedstoneOutput(World world, BlockPos pos) {
        Class<Entity> entityClass = Entity.class;
        return getEntityCount(world, BOX.offset(pos), entityClass) > 0 ? 15 : 0;
    }

    protected static int getEntityCount(@NotNull World world, Box box, Class<? extends Entity> entityClass) {
        return world.getEntitiesByClass(entityClass, box, EntityPredicates.EXCEPT_SPECTATOR.and((entity) -> {
            return !entity.canAvoidTraps();
        })).size();
    }
}