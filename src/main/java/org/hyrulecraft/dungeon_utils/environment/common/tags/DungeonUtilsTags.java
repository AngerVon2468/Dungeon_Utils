package org.hyrulecraft.dungeon_utils.environment.common.tags;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.Tag;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;

public class DungeonUtilsTags {

    public static class Blocks {

        public static final Tag<Block> HOOKSHOT = Tag.createTag(RegistryKeys.BLOCK, new Identifier(DungeonUtils.MOD_ID, "hookshot"));

        public static final Tag<Block> ACCEPTABLE_CRATE_BLOCK = Tag.createTag(RegistryKeys.BLOCK, new Identifier(DungeonUtils.MOD_ID, "acceptable_crate_block"));
    }

    public static class Items {

        public static final Tag<Item> BOW = Tag.createTag(RegistryKeys.ITEM, new Identifier(DungeonUtils.MOD_ID, "bow"));

        public static final Tag<Item> CHAMPION_ABILITY = Tag.createTag(RegistryKeys.ITEM, new Identifier(DungeonUtils.MOD_ID, "champion_ability"));
    }

    public static void registerDungeonUtilsTags() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its tags.");
    }
}