package org.hyrulecraft.dungeon_utils.tags;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.DungeonUtils;

public class DungeonUtilsTags {


    public static class Blocks {

        public static final TagKey<Block> HOOKSHOT = TagKey.of(RegistryKeys.BLOCK, new Identifier(DungeonUtils.MOD_ID, "hookshot"));
    }

    public static class Items {

    }

    public static void registerDungeonUtilsTags() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its tags.");
    }
}