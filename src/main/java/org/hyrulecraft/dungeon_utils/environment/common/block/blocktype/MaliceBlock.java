package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.damage.DungeonUtilsDamageTypes;

public class MaliceBlock extends Block {

    public MaliceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos blockPos, BlockState blockState, Entity entity) {
        super.onSteppedOn(world, blockPos, blockState, entity);
        entity.damage(DungeonUtilsDamageTypes.of(world, DungeonUtilsDamageTypes.MALICE), 0.5f);
    }
}