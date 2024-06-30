package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;

public class BombFlowerBlock extends CropBlock {

    public BombFlowerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canPlaceAt(BlockState blockState, WorldView worldView, BlockPos blockPos) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) {
        int i = this.getAge(blockState);
        if (i < this.getMaxAge()) {
            if (random.nextInt((int)(3.0f / 2) + 1) == 0) {
                serverWorld.setBlockState(blockPos, this.withAge(i + 1), 2);
            }
        }
        this.scheduledTick(blockState, serverWorld, blockPos, random);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return DungeonUtilsItems.BOMB;
    }
}