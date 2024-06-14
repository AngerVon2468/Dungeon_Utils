package org.hyrulecraft.dungeon_utils.tags;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.DungeonUtils;

public class DungeonUtilsTags {

    public static class Blocks {

        public static final TagKey<Block> HOOKSHOT = TagKey.of(RegistryKeys.BLOCK, new Identifier(DungeonUtils.MOD_ID, "hookshot"));

        public static final TagKey<Block> ACCEPTABLE_CRATE_BLOCK = TagKey.of(RegistryKeys.BLOCK, new Identifier(DungeonUtils.MOD_ID, "acceptable_crate_block"));
    }

    public static class Items {

        public static final TagKey<Item> BOW = TagKey.of(RegistryKeys.ITEM, new Identifier(DungeonUtils.MOD_ID, "bow"));

        public static final TagKey<Item> CHAMPION_ABILITY = TagKey.of(RegistryKeys.ITEM, new Identifier(DungeonUtils.MOD_ID, "champion_ability"));
    }

    public static void registerDungeonUtilsTags() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its tags.");
    }
}