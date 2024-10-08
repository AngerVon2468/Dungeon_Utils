package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractSelfDroppingBlock extends Block {

    public AbstractSelfDroppingBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBreak(World world, BlockPos blockPos, BlockState blockState, PlayerEntity player) {
        super.onBreak(world, blockPos, blockState, player);
        if (!world.isClient() && !player.isCreative()) {
            ItemEntity itemEntity = new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), this.asItem().getDefaultStack());
            world.spawnEntity(itemEntity);
        }
    }
}