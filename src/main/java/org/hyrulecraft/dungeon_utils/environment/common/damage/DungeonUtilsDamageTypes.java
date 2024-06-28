package org.hyrulecraft.dungeon_utils.environment.common.damage;

import net.minecraft.entity.damage.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;

public class DungeonUtilsDamageTypes {

    public static final RegistryKey<DamageType> MALICE = RegistryKey.of(RegistryKeys.DAMAGE, new Identifier(DungeonUtils.MOD_ID, "malice"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE).entryOf(key));
    }
}