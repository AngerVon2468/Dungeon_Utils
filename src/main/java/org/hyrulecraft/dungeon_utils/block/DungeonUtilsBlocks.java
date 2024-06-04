package org.hyrulecraft.dungeon_utils.block;

import org.hyrulecraft.dungeon_utils.DungeonUtils;
import org.hyrulecraft.dungeon_utils.block.blocktype.*;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

public class DungeonUtilsBlocks {

    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(DungeonUtils.MOD_ID, name), block);
    }

    public static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(DungeonUtils.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().maxCount(64)));
    }

    public static final Block YELLOW_SWITCH_BLOCK = registerBlock("yellow_switch_block",
            new YellowSwitchBlock(FabricBlockSettings.create()));

    public static final Block RUSTY_SWITCH_BLOCK = registerBlock("rusty_switch_block",
            new RustySwitchBlock(FabricBlockSettings.create()));

    public static final Block BLUE_SWITCH_BLOCK = registerBlock("blue_switch_block",
            new BlueSwitchBlock(FabricBlockSettings.create()));

    public static void registerModBlocks() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its blocks.");
    }
}