package org.hyrulecraft.dungeon_utils.itemgroup

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup

import net.minecraft.item.ItemStack
import net.minecraft.registry.*
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.hyrulecraft.dungeon_utils.DungeonUtils.*

import org.hyrulecraft.dungeon_utils.block.ModBlocks.*
import org.hyrulecraft.dungeon_utils.item.ModItems.MEGATON_HAMMER

class DungeonUtilsItemGroups {

    companion object {

        val ALL = Registry.register(
            Registries.ITEM_GROUP, Identifier(MOD_ID, "dungeon_utils"),
            FabricItemGroup.builder().displayName(Text.translatable("tab.dungeon_utils.dungeon_utils"))
                .icon { ItemStack(YELLOW_SWITCH_BLOCK) }.entries { displayContext, entries ->
                    entries.add(YELLOW_SWITCH_BLOCK)
                    entries.add(RUSTY_SWITCH_BLOCK)
                    entries.add(BLUE_SWITCH_BLOCK)
                    entries.add(MEGATON_HAMMER)
                }.build()
        )

        @JvmStatic
        fun registerDungeonUtilsItemGroups() {
            LOGGER.info("$NAME has registered its itemgroups.")
        }
    }
}