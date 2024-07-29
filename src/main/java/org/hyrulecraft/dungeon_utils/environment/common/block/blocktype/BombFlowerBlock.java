package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlockEntities;
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.blockentity.BombFlowerBlockEntity;
import org.hyrulecraft.dungeon_utils.environment.common.entity.entitytype.BombEntity;

import org.jetbrains.annotations.*;

import java.util.stream.Stream;

public class BombFlowerBlock extends BlockWithEntity {

    public BombFlowerBlockEntity blockEntity;

    public static final BooleanProperty IS_GROWN = BooleanProperty.of("is_grown");

    public BombFlowerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(IS_GROWN, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, @NotNull World world, BlockPos pos, @NotNull PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        if (!world.isClient() && this.blockEntity != null) {
            player.sendMessage(Text.literal("Growth: " + this.blockEntity.growth));
            if (state.get(IS_GROWN)) {
                world.setBlockState(pos, state.with(IS_GROWN, false));
                BombEntity bombEntity = BombEntity.create(world);
                bombEntity.refreshPositionAfterTeleport(Vec3d.ofCenter(pos).offset(Direction.DOWN, 0.45));
                world.spawnEntity(bombEntity);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(@NotNull BlockState state, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {

        if (!state.get(IS_GROWN)) {

            return Stream.of(
                    Block.createCuboidShape(1, 0.109, 1, 4, 0.109, 4),
                    Block.createCuboidShape(2, 0.109, 4, 4, 0.109, 5),
                    Block.createCuboidShape(4, 0.109, 2, 5, 0.109, 6),
                    Block.createCuboidShape(5, 0.109, 4, 6, 0.109, 8),
                    Block.createCuboidShape(4, 0.109, 7, 5, 0.109, 8),
                    Block.createCuboidShape(6, 0.109, 5, 8, 0.109, 8),
                    Block.createCuboidShape(7, 0.109, 4, 8, 0.109, 5),
                    Block.createCuboidShape(1, 0.109, 12, 4, 0.109, 15),
                    Block.createCuboidShape(4, 0.109, 12, 5, 0.109, 14),
                    Block.createCuboidShape(2, 0.109, 11, 6, 0.109, 12),
                    Block.createCuboidShape(4, 0.109, 10, 8, 0.109, 11),
                    Block.createCuboidShape(7, 0.109, 11, 8, 0.109, 12),
                    Block.createCuboidShape(5, 0.109, 8, 8, 0.109, 10),
                    Block.createCuboidShape(4, 0.109, 8, 5, 0.109, 9),
                    Block.createCuboidShape(12, 0.109, 12, 15, 0.109, 15),
                    Block.createCuboidShape(12, 0.109, 11, 14, 0.109, 12),
                    Block.createCuboidShape(11, 0.109, 10, 12, 0.109, 14),
                    Block.createCuboidShape(10, 0.109, 8, 11, 0.109, 12),
                    Block.createCuboidShape(11, 0.109, 8, 12, 0.109, 9),
                    Block.createCuboidShape(8, 0.109, 8, 10, 0.109, 11),
                    Block.createCuboidShape(8, 0.109, 11, 9, 0.109, 12),
                    Block.createCuboidShape(12, 0.109, 1, 15, 0.109, 4),
                    Block.createCuboidShape(11, 0.109, 2, 12, 0.109, 4),
                    Block.createCuboidShape(10, 0.109, 4, 14, 0.109, 5),
                    Block.createCuboidShape(8, 0.109, 5, 12, 0.109, 6),
                    Block.createCuboidShape(8, 0.109, 4, 9, 0.109, 5),
                    Block.createCuboidShape(8, 0.109, 6, 11, 0.109, 8),
                    Block.createCuboidShape(11, 0.109, 7, 12, 0.109, 8),
                    Block.createCuboidShape(1, 0.009, 6, 15, 0.009, 10),
                    Block.createCuboidShape(2, 0.009, 10, 14, 0.009, 12),
                    Block.createCuboidShape(2, 0.009, 4, 14, 0.009, 6),
                    Block.createCuboidShape(3, 0.009, 12, 13, 0.009, 13),
                    Block.createCuboidShape(3, 0.009, 3, 13, 0.009, 4),
                    Block.createCuboidShape(4, 0.009, 13, 12, 0.009, 14),
                    Block.createCuboidShape(4, 0.009, 2, 12, 0.009, 3),
                    Block.createCuboidShape(6, 0.009, 14, 10, 0.009, 15),
                    Block.createCuboidShape(6, 0.009, 1, 10, 0.009, 2)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else {

            return Stream.of(
                    Block.createCuboidShape(1, 0.109, 1, 4, 0.109, 4),
                    Block.createCuboidShape(2, 0.109, 4, 4, 0.109, 5),
                    Block.createCuboidShape(4, 0.109, 2, 5, 0.109, 6),
                    Block.createCuboidShape(5, 0.109, 4, 6, 0.109, 8),
                    Block.createCuboidShape(4, 0.109, 7, 5, 0.109, 8),
                    Block.createCuboidShape(6, 0.109, 5, 8, 0.109, 8),
                    Block.createCuboidShape(7, 0.109, 4, 8, 0.109, 5),
                    Block.createCuboidShape(1, 0.109, 12, 4, 0.109, 15),
                    Block.createCuboidShape(4, 0.109, 12, 5, 0.109, 14),
                    Block.createCuboidShape(2, 0.109, 11, 6, 0.109, 12),
                    Block.createCuboidShape(4, 0.109, 10, 8, 0.109, 11),
                    Block.createCuboidShape(7, 0.109, 11, 8, 0.109, 12),
                    Block.createCuboidShape(5, 0.109, 8, 8, 0.109, 10),
                    Block.createCuboidShape(4, 0.109, 8, 5, 0.109, 9),
                    Block.createCuboidShape(12, 0.109, 12, 15, 0.109, 15),
                    Block.createCuboidShape(12, 0.109, 11, 14, 0.109, 12),
                    Block.createCuboidShape(11, 0.109, 10, 12, 0.109, 14),
                    Block.createCuboidShape(10, 0.109, 8, 11, 0.109, 12),
                    Block.createCuboidShape(11, 0.109, 8, 12, 0.109, 9),
                    Block.createCuboidShape(8, 0.109, 8, 10, 0.109, 11),
                    Block.createCuboidShape(8, 0.109, 11, 9, 0.109, 12),
                    Block.createCuboidShape(12, 0.109, 1, 15, 0.109, 4),
                    Block.createCuboidShape(11, 0.109, 2, 12, 0.109, 4),
                    Block.createCuboidShape(10, 0.109, 4, 14, 0.109, 5),
                    Block.createCuboidShape(8, 0.109, 5, 12, 0.109, 6),
                    Block.createCuboidShape(8, 0.109, 4, 9, 0.109, 5),
                    Block.createCuboidShape(8, 0.109, 6, 11, 0.109, 8),
                    Block.createCuboidShape(11, 0.109, 7, 12, 0.109, 8),
                    Block.createCuboidShape(1, 0.009, 6, 15, 0.009, 10),
                    Block.createCuboidShape(2, 0.009, 10, 14, 0.009, 12),
                    Block.createCuboidShape(2, 0.009, 4, 14, 0.009, 6),
                    Block.createCuboidShape(3, 0.009, 12, 13, 0.009, 13),
                    Block.createCuboidShape(3, 0.009, 3, 13, 0.009, 4),
                    Block.createCuboidShape(4, 0.009, 13, 12, 0.009, 14),
                    Block.createCuboidShape(4, 0.009, 2, 12, 0.009, 3),
                    Block.createCuboidShape(6, 0.009, 14, 10, 0.009, 15),
                    Block.createCuboidShape(6.49, 7.99, 6.49, 9.51, 9.01, 9.51),
                    Block.createCuboidShape(4.99, 0.99, 3.99, 11.01, 7.01, 12.01),
                    Block.createCuboidShape(3.99, 0.99, 4.99, 12.01, 7.01, 11.01),
                    Block.createCuboidShape(4.99, -0.01, 4.99, 11.01, 8.01, 11.01),
                    Block.createCuboidShape(6, 0.009, 1, 10, 0.009, 2)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        }
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