package me.ideallykafei.hyrulecraft_switches.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import me.ideallykafei.hyrulecraft_switches.HyrulecraftSwitches;
import me.ideallykafei.hyrulecraft_switches.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {



    public static void registerItemGroups() {
        HyrulecraftSwitches.LOGGER.info("Registering Item Groups for " + HyrulecraftSwitches.MOD_ID);
    }
}
