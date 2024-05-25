package me.ideallykafei.hyrulecraft_switches.item;

import me.ideallykafei.hyrulecraft_switches.HyrulecraftSwitches;

import net.minecraft.item.Item;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(HyrulecraftSwitches.MOD_ID, name), item);
    }

    public static void registerModItems() {
        HyrulecraftSwitches.LOGGER.info(HyrulecraftSwitches.NAME + " has registered its items.");
    }
}