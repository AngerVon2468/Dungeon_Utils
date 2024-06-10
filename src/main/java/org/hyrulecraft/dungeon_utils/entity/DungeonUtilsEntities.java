package org.hyrulecraft.dungeon_utils.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.DungeonUtils;

public class DungeonUtilsEntities {

    public static final EntityType<BeamEntity> BEAM = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(DungeonUtils.MOD_ID, "beam"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, BeamEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f,0.1f))
                    .build()
    );

    public static void registerDungeonUtilsEntities() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its entities.");
    }
}