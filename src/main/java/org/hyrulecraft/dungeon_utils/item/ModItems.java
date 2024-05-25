package org.hyrulecraft.dungeon_utils.item;

import org.hyrulecraft.dungeon_utils.DungeonUtils;

import net.minecraft.item.Item;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(DungeonUtils.MOD_ID, name), item);
    }

    public static void registerModItems() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its items.");
    }
}