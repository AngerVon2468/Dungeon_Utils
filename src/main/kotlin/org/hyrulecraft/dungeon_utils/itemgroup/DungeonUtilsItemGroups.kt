package org.hyrulecraft.dungeon_utils.itemgroup

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup

import net.minecraft.item.ItemStack
import net.minecraft.registry.*
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.hyrulecraft.dungeon_utils.DungeonUtils.*

import org.hyrulecraft.dungeon_utils.block.ModBlocks.*
import org.hyrulecraft.dungeon_utils.item.ModItems.*

class DungeonUtilsItemGroups {

    companion object {

        val ALL = Registry.register(
            Registries.ITEM_GROUP, Identifier(MOD_ID, "dungeon_utils"),
            FabricItemGroup.builder().displayName(Text.translatable("tab.dungeon_utils.dungeon_utils"))
                .icon { ItemStack(YELLOW_SWITCH_BLOCK) }.entries { displayContext, entries ->
                    entries.add(MEGATON_HAMMER)
                    entries.add(LYNEL_BOW_FIVE_X)
                    entries.add(LYNEL_BOW_THREE_X)
                    entries.add(YELLOW_SWITCH_BLOCK)
                    entries.add(RUSTY_SWITCH_BLOCK)
                    entries.add(BLUE_SWITCH_BLOCK)
                    entries.add(REVALIS_GALE)
                    entries.add(REVALIS_GALE_PLUS)
                    entries.add(MIPHAS_GRACE)
                    entries.add(MIPHAS_GRACE_PLUS)
                    entries.add(LINK_CAP)
                    entries.add(MOGMA_MITTS)
                    entries.add(CLIMBING_GLOVES)
                    entries.add(PARAGLIDER)
                    entries.add(KOKIRI_SWORD)
                    entries.add(RAZOR_SWORD)
                    entries.add(GILDED_SWORD)
                }.build()
        )

        val CHAMPION_ABILITIES = Registry.register(
            Registries.ITEM_GROUP, Identifier(MOD_ID, "champion_abilities"),
            FabricItemGroup.builder().displayName(Text.translatable("tab.dungeon_utils.champion_abilities"))
                .icon { ItemStack(REVALIS_GALE) }.entries { displayContext, entries ->
                    entries.add(REVALIS_GALE)
                    entries.add(REVALIS_GALE_PLUS)
                    entries.add(MIPHAS_GRACE)
                    entries.add(MIPHAS_GRACE_PLUS)
                }.build()
        )

        val CLOTHNG = Registry.register(
            Registries.ITEM_GROUP, Identifier(MOD_ID, "clothing"),
            FabricItemGroup.builder().displayName(Text.translatable("tab.dungeon_utils.clothing"))
                .icon { ItemStack(LINK_CAP) }.entries { displayContext, entries ->
                    entries.add(LINK_CAP)
                    entries.add(MOGMA_MITTS)
                    entries.add(CLIMBING_GLOVES)
                }.build()
        )

        val WEAPONRY = Registry.register(
            Registries.ITEM_GROUP, Identifier(MOD_ID, "weaponry"),
            FabricItemGroup.builder().displayName(Text.translatable("tab.dungeon_utils.weaponry"))
                .icon { ItemStack(KOKIRI_SWORD) }.entries { displayContext, entries ->
                    entries.add(KOKIRI_SWORD)
                    entries.add(RAZOR_SWORD)
                    entries.add(GILDED_SWORD)
                    entries.add(MEGATON_HAMMER)
                    entries.add(LYNEL_BOW_FIVE_X)
                    entries.add(LYNEL_BOW_THREE_X)
                    entries.add(REVALIS_GALE)
                    entries.add(REVALIS_GALE_PLUS)
                    entries.add(MIPHAS_GRACE)
                    entries.add(MIPHAS_GRACE_PLUS)
                }.build()
        )

        @JvmStatic
        fun registerDungeonUtilsItemGroups() {
            LOGGER.info("$NAME has registered its itemgroups.")
        }
    }
}