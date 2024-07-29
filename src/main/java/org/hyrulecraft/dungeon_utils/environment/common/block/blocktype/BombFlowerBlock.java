package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlockEntities;
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.blockentity.BombFlowerBlockEntity;

import org.jetbrains.annotations.*;

public class BombFlowerBlock extends BlockWithEntity {

    public BombFlowerBlockEntity blockEntity;

    public static final BooleanProperty IS_GROWN = BooleanProperty.of("is_grown");

    public BombFlowerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(IS_GROWN, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public void scheduledTick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) {
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState blockState, @NotNull World world, BlockPos blockPos, @NotNull PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        if (!world.isClient() && this.blockEntity != null) {
            player.sendMessage(Text.literal("Growth: " + this.blockEntity.growth));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(@NotNull StateManager.Builder<Block, BlockState> builder) {
        builder.add(IS_GROWN);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
        this.blockEntity = new BombFlowerBlockEntity(blockPos, blockState);
        return this.blockEntity;
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull World world, BlockState blockState, BlockEntityType<T> type) {
        return world.isClient() || this.blockEntity == null ? null : checkType(type, DungeonUtilsBlockEntities.BOMB_FLOWER_BLOCK_ENTITY, this.blockEntity::serverTick);
    }
}