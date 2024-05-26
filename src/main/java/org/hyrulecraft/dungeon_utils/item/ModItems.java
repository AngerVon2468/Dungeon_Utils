package org.hyrulecraft.dungeon_utils.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.item.itemtype.*;
import org.hyrulecraft.dungeon_utils.DungeonUtils;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(DungeonUtils.MOD_ID, name), item);
    }

    public static final Item MEGATON_HAMMER = registerItem("megaton_hammer",
            new MegatonHammerItem(ToolMaterials.IRON, 8, -2, new FabricItemSettings()));

    public static final Item LYNEL_BOW = registerItem("lynel_bow",
            new LynelBowItem(new FabricItemSettings()));

    public static void registerModItems() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its items.");
    }
}