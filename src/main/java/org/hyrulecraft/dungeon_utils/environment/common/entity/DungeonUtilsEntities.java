package org.hyrulecraft.dungeon_utils.environment.common.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.*;

import net.minecraft.entity.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;
import org.hyrulecraft.dungeon_utils.environment.common.entity.entity_type.*;

public class DungeonUtilsEntities {

    public static final EntityType<MasterSwordBeamEntity> MASTER_SWORD_BEAM = Registry.register(
            RegistryTypes.ENTITY,
            new Identifier(DungeonUtils.MOD_ID, "master_sword_beam"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, MasterSwordBeamEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,0.1f))
                    .fireImmune()
                    .build()
    );

    public static final EntityType<CrateEntity> CRATE = Registry.register(
            RegistryTypes.ENTITY,
            new Identifier(DungeonUtils.MOD_ID, "crate"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, CrateEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f))
                    .fireImmune()
                    .trackRangeBlocks(5000)
                    .spawnableFarFromPlayer()
                    .build()
    );

    public static final EntityType<BombEntity> BOMB = Registry.register(
            RegistryTypes.ENTITY,
            new Identifier(DungeonUtils.MOD_ID, "bomb"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, BombEntity::new)
                    .dimensions(EntityDimensions.fixed(0.55f,0.75f))
                    .fireImmune()
                    .trackRangeBlocks(5000)
                    .spawnableFarFromPlayer()
                    .build()
    );

    public static final EntityType<FairyEntity> FAIRY = Registry.register(
            RegistryTypes.ENTITY,
            new Identifier(DungeonUtils.MOD_ID, "fairy"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FairyEntity::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 0.75f))
                    .build()
    );

    public static void registerDungeonUtilsEntities() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its entities.");
        FabricDefaultAttributeRegistry.register(DungeonUtilsEntities.FAIRY, FairyEntity.createMobAttributes());
    }
}