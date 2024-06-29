package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class CloverPatchBlock extends Block {

    public CloverPatchBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos blockPos, BlockState blockState, Entity entity) {
        Random random = new Random();
        if (random.nextDouble(0, 5) == 0) {

            entity.dropStack(Items.STICK.getDefaultStack());

        }
    }
}