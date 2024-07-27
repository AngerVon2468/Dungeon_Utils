package org.hyrulecraft.dungeon_utils.environment.common.itemgroup

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup

import net.minecraft.item.*
import net.minecraft.registry.*
import net.minecraft.text.Text
import net.minecraft.util.Identifier

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils.*
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.BLUE_SWITCH_BLOCK
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.BONE_PILE
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.CHISELED_GODTRIBE_PILLAR
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.CHISELED_GODTRIBE_STONE
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.CRACKED_GODTRIBE_PILLAR
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.DIRT_BONE_PILE
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.FLESHY_BONE_PILE
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.GODTRIBE_BRICKS
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.GODTRIBE_BRICKS_PILLAR
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.GODTRIBE_PILLAR
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.GODTRIBE_STONE
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.MALICE
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.POLISHED_GODTRIBE_STONE
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.RUSTY_SWITCH_BLOCK
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.STONE_BONE_PILE
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.SWIRL_PATTERN_GODTRIBE_PILLAR
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.TILEABLE_GODTRIBE_PATTERN
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.TRIFORCE_PATTERN_GODTRIBE_STONE
import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlocks.YELLOW_SWITCH_BLOCK
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.ADULT_LINK
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.BLUE_RUPEE
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.BOKOBLIN_HEAD
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.BOTW_LINK
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.CHILD_RUPEE_WALLET
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.CLIMBING_GLOVES
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.DARUKS_PROTECTION
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.GIANTS_MASK
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.GILDED_SWORD
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.GREAT_EAGLE_BOW
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.GREAT_FAIRY_MASK
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.GREEN_RUPEE
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.HEART_CONTAINER
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.KEATON_MASK
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.KOKIRI_SHIELD
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.KOKIRI_SWORD
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.LINK_CAP
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.LINK_SLEEVES
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.LINK_TUNIC
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.LYNEL_BOW_FIVE_X
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.LYNEL_BOW_THREE_X
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.MAJORAS_MASK
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.MEGATON_HAMMER
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.MIPHAS_GRACE
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.MIPHAS_GRACE_PLUS
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.MOGMA_MITTS
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.PARAGLIDER
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.RAZOR_SWORD
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.REVALIS_GALE
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.REVALIS_GALE_PLUS
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.SHADOW_HYLIAN_SHIELD
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.SHADOW_LINK
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.SHADOW_MASTER_SWORD
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.THE_HYLIAN_SHIELD
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.THE_MASTER_SWORD
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.THE_MASTER_SWORD_AWAKENED
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.TOTK_LINK
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems.YOUNG_LINK

class DungeonUtilsItemGroups {

    companion object {

        val ALL: ItemGroup = Registry.register(
            RegistryTypes.ITEM_GROUP, Identifier(MOD_ID, "dungeon_utils"),
            FabricItemGroup.builder().displayName(Text.translatable("tab.dungeon_utils.dungeon_utils"))
                .icon { ItemStack(YELLOW_SWITCH_BLOCK) }.entries { displayContext, entries ->
                    entries.add(MEGATON_HAMMER)
                    entries.add(LYNEL_BOW_FIVE_X)
                    entries.add(LYNEL_BOW_THREE_X)
                    entries.add(GREAT_EAGLE_BOW)
                    entries.add(YELLOW_SWITCH_BLOCK)
                    entries.add(RUSTY_SWITCH_BLOCK)
                    entries.add(BLUE_SWITCH_BLOCK)
                    entries.add(REVALIS_GALE)
                    entries.add(REVALIS_GALE_PLUS)
                    entries.add(MIPHAS_GRACE)
                    entries.add(MIPHAS_GRACE_PLUS)
                    entries.add(DARUKS_PROTECTION)
                    entries.add(LINK_CAP)
                    entries.add(LINK_TUNIC)
                    entries.add(LINK_SLEEVES)
                    entries.add(MOGMA_MITTS)
                    entries.add(CLIMBING_GLOVES)
                    entries.add(PARAGLIDER)
                    entries.add(KOKIRI_SWORD)
                    entries.add(RAZOR_SWORD)
                    entries.add(GILDED_SWORD)
                    entries.add(THE_MASTER_SWORD)
                    entries.add(THE_MASTER_SWORD_AWAKENED)
                    entries.add(THE_HYLIAN_SHIELD)
                    entries.add(KOKIRI_SHIELD)
                    entries.add(HEART_CONTAINER)
                    entries.add(SHADOW_MASTER_SWORD)
                    entries.add(SHADOW_HYLIAN_SHIELD)
                    entries.add(CHILD_RUPEE_WALLET)
                    entries.add(GREEN_RUPEE)
                    entries.add(BLUE_RUPEE)
                }.build()
        )

        val CHAMPION_ABILITIES: ItemGroup = Registry.register(
            RegistryTypes.ITEM_GROUP, Identifier(MOD_ID, "champion_abilities"),
            FabricItemGroup.builder().displayName(Text.translatable("tab.dungeon_utils.champion_abilities"))
                .icon { ItemStack(REVALIS_GALE) }.entries { displayContext, entries ->
                    entries.add(REVALIS_GALE)
                    entries.add(REVALIS_GALE_PLUS)
                    entries.add(MIPHAS_GRACE)
                    entries.add(MIPHAS_GRACE_PLUS)
                    entries.add(DARUKS_PROTECTION)
                }.build()
        )

        val CLOTHING: ItemGroup = Registry.register(
            RegistryTypes.ITEM_GROUP, Identifier(MOD_ID, "clothing"),
            FabricItemGroup.builder().displayName(Text.translatable("tab.dungeon_utils.clothing"))
                .icon { ItemStack(LINK_CAP) }.entries { displayContext, entries ->
                    entries.add(LINK_CAP)
                    entries.add(LINK_TUNIC)
                    entries.add(LINK_SLEEVES)
                    entries.add(MOGMA_MITTS)
                    entries.add(CLIMBING_GLOVES)
                    entries.add(TOTK_LINK)
                    entries.add(BOTW_LINK)
                    entries.add(SHADOW_LINK)
                    entries.add(ADULT_LINK)
                    entries.add(YOUNG_LINK)
                }.build()
        )

        val WEAPONRY: ItemGroup = Registry.register(
            RegistryTypes.ITEM_GROUP, Identifier(MOD_ID, "weaponry"),
            FabricItemGroup.builder().displayName(Text.translatable("tab.dungeon_utils.weaponry"))
                .icon { ItemStack(KOKIRI_SWORD) }.entries { displayContext, entries ->
                    entries.add(KOKIRI_SWORD)
                    entries.add(KOKIRI_SHIELD)
                    entries.add(RAZOR_SWORD)
                    entries.add(GILDED_SWORD)
                    entries.add(THE_MASTER_SWORD)
                    entries.add(THE_MASTER_SWORD_AWAKENED)
                    entries.add(THE_HYLIAN_SHIELD)
                    entries.add(SHADOW_MASTER_SWORD)
                    entries.add(SHADOW_HYLIAN_SHIELD)
                    entries.add(MEGATON_HAMMER)
                    entries.add(LYNEL_BOW_FIVE_X)
                    entries.add(LYNEL_BOW_THREE_X)
                    entries.add(GREAT_EAGLE_BOW)
                    entries.add(REVALIS_GALE)
                    entries.add(REVALIS_GALE_PLUS)
                    entries.add(MIPHAS_GRACE)
                    entries.add(MIPHAS_GRACE_PLUS)
                    entries.add(DARUKS_PROTECTION)
                }.build()
        )

        val BLOCKS: ItemGroup = Registry.register(
            RegistryTypes.ITEM_GROUP, Identifier(MOD_ID, "blocks"),
            FabricItemGroup.builder().displayName(Text.translatable("tab.dungeon_utils.blocks"))
                .icon { ItemStack(BONE_PILE) }.entries { displayContext, entries ->
                    entries.add(BONE_PILE)
                    entries.add(STONE_BONE_PILE)
                    entries.add(DIRT_BONE_PILE)
                    entries.add(FLESHY_BONE_PILE)
                    entries.add(MALICE)
                    entries.add(YELLOW_SWITCH_BLOCK)
                    entries.add(RUSTY_SWITCH_BLOCK)
                    entries.add(BLUE_SWITCH_BLOCK)
                    entries.add(GODTRIBE_STONE)
                    entries.add(CHISELED_GODTRIBE_STONE)
                    entries.add(POLISHED_GODTRIBE_STONE)
                    entries.add(TRIFORCE_PATTERN_GODTRIBE_STONE)
                    entries.add(GODTRIBE_PILLAR)
                    entries.add(CHISELED_GODTRIBE_PILLAR)
                    entries.add(CRACKED_GODTRIBE_PILLAR)
                    entries.add(GODTRIBE_BRICKS)
                    entries.add(GODTRIBE_BRICKS_PILLAR)
                    entries.add(SWIRL_PATTERN_GODTRIBE_PILLAR)
                    entries.add(TILEABLE_GODTRIBE_PATTERN)
                }.build()
        )

        val MASKS: ItemGroup = Registry.register(
            RegistryTypes.ITEM_GROUP, Identifier(MOD_ID, "masks"),
            FabricItemGroup.builder().displayName(Text.translatable("tab.dungeon_utils.masks"))
                .icon { ItemStack(MAJORAS_MASK) }.entries { displayContext, entries ->
                    entries.add(MAJORAS_MASK)
                    entries.add(GIANTS_MASK)
                    entries.add(GREAT_FAIRY_MASK)
                    entries.add(KEATON_MASK)
                    entries.add(BOKOBLIN_HEAD)
                }.build()
        )

        @JvmStatic
        fun registerDungeonUtilsItemGroups() {
            LOGGER.info("$NAME has registered its itemgroups.")
        }
    }
}