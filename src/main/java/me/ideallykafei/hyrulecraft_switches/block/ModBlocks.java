package me.ideallykafei.hyrulecraft_switches.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import me.ideallykafei.hyrulecraft_switches.HyrulecraftSwitches;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

public class ModBlocks {

    private static void registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        Registry.register(Registries.BLOCK, new Identifier(HyrulecraftSwitches.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(HyrulecraftSwitches.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }



    public static void registerModBlocks() {
        HyrulecraftSwitches.LOGGER.info(HyrulecraftSwitches.NAME + " has registered its blocks.");

    }
}