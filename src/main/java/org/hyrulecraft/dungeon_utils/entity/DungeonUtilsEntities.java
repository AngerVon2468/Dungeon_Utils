package org.hyrulecraft.dungeon_utils.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import net.minecraft.entity.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.DungeonUtils;

public class DungeonUtilsEntities {

    public static final EntityType<MasterSwordBeamEntity> MASTER_SWORD_BEAM = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(DungeonUtils.MOD_ID, "master_sword_beam"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, MasterSwordBeamEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,0.1f))
                    .build()
    );

    public static final EntityType<CrateEntity> CRATE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(DungeonUtils.MOD_ID, "crate"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, CrateEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f))
                    .build()
    );

    public static void registerDungeonUtilsEntities() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its entities.");
    }
}