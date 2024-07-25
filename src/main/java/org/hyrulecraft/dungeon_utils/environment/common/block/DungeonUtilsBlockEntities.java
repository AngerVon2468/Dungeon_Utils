package org.hyrulecraft.dungeon_utils.environment.common.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.blockentity.PedestalBlockEntity;

public class DungeonUtilsBlockEntities {

    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BLOCK_ENTITY = Registry.register(
            RegistryTypes.BLOCK_ENTITY,
            new Identifier(DungeonUtils.MOD_ID, "pedestal_block_entity"),
            FabricBlockEntityTypeBuilder.create(PedestalBlockEntity::new, DungeonUtilsBlocks.PEDESTAL_BLOCK).build()
    );

    public static void registerDungeonUtilsBlockEntities() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its block entities.");
    }
}