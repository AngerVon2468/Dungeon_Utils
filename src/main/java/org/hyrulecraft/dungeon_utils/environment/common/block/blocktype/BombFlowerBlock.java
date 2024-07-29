package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype;

import net.minecraft.block.*;
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

import org.jetbrains.annotations.NotNull;

public class BombFlowerBlock extends Block {

    public int growth;

    public boolean antiLag;

    public static final BooleanProperty IS_GROWN = BooleanProperty.of("is_grown");

    public BombFlowerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(IS_GROWN, false));
        growth = 0;
        antiLag = false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void scheduledTick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) {
        if (this.growth < (20 * 7)) {
            this.growth++;
        } else if (!this.antiLag) {
            serverWorld.setBlockState(blockPos, this.getDefaultState().with(IS_GROWN, true));
            this.antiLag = true;
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState blockState, @NotNull World world, BlockPos blockPos, @NotNull PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        if (!world.isClient()) {
            player.sendMessage(Text.literal("Growth: " + this.growth));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(@NotNull StateManager.Builder<Block, BlockState> builder) {
        builder.add(IS_GROWN);
    }
}