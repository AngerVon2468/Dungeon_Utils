package me.ideallykafei.hyrulecraft_switches.block;

import me.ideallykafei.hyrulecraft_switches.block.blocktype.YellowSwitchBlock;
import me.ideallykafei.hyrulecraft_switches.HyrulecraftSwitches;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(HyrulecraftSwitches.MOD_ID, name), block);
    }

    public static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(HyrulecraftSwitches.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().maxCount(64)));
    }

    public static final Block YELLOW_SWITCH_BLOCK = registerBlock("yellow_switch_block", new YellowSwitchBlock(FabricBlockSettings.create()));

    public static void registerModBlocks() {
        HyrulecraftSwitches.LOGGER.info(HyrulecraftSwitches.NAME + " has registered its blocks.");
    }
}