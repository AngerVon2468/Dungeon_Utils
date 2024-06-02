package org.hyrulecraft.dungeon_utils.item.itemtype.clothing.glove;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

public class ClimbingGlovesItem extends Item {

    public ClimbingGlovesItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockpos);
        PlayerEntity player = context.getPlayer();
        assert player != null;
        return ActionResult.CONSUME_PARTIAL;
    }
}