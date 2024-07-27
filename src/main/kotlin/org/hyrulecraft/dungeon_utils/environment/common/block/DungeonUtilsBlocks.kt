package org.hyrulecraft.dungeon_utils.environment.common.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings

import net.minecraft.block.Block
import net.minecraft.item.*
import net.minecraft.registry.*
import net.minecraft.util.Identifier

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.*
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.bonepile.*
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.godtribe.GodtribeStone
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.switchblock.*

object DungeonUtilsBlocks {

    private fun registerBlock(name: String?, block: Block?): Block? { registerBlockItem(name, block)
        return Registry.register(RegistryTypes.BLOCK, Identifier(DungeonUtils.MOD_ID, name), block)
    }

    private fun registerBlockItem(name: String?, block: Block?): Item {
        return Registry.register(RegistryTypes.ITEM, Identifier(DungeonUtils.MOD_ID, name),
            BlockItem(block, FabricItemSettings().maxCount(64)))
    }

    @JvmField
    val YELLOW_SWITCH_BLOCK = registerBlock("yellow_switch_block",
        YellowSwitchBlock(
            FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.1f)
        )
    )

    @JvmField
    val RUSTY_SWITCH_BLOCK = registerBlock("rusty_switch_block",
        RustySwitchBlock(
            FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.1f)
        )
    )

    @JvmField
    val BLUE_SWITCH_BLOCK = registerBlock("blue_switch_block",
        BlueSwitchBlock(
            FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.1f)
        )
    )

    @JvmField
    val PEDESTAL_BLOCK = registerBlock("pedestal_block",
        PedestalBlock(
            FabricBlockSettings.create().nonOpaque().requiresTool().strength(1.5f)
        )
    )

    @JvmField
    val MALICE = registerBlock("malice",
        MaliceBlock(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val BONE_PILE = registerBlock("pile_of_bones",
        BonePileBlock(
            FabricBlockSettings.create().strength(2f).requiresTool()
        )
    )

    @JvmField
    val STONE_BONE_PILE = registerBlock("stone_pile_of_bones",
        StoneBonePileBlock(
            FabricBlockSettings.create().strength(2f).requiresTool()
        )
    )

    @JvmField
    val DIRT_BONE_PILE = registerBlock("dirt_pile_of_bones",
        DirtBonePileBlock(
            FabricBlockSettings.create().strength(2f).requiresTool()
        )
    )

    @JvmField
    val FLESHY_BONE_PILE = registerBlock("fleshy_pile_of_bones",
        FleshyBonePileBlock(
            FabricBlockSettings.create().strength(2f).requiresTool()
        )
    )

    @JvmField
    val CLOVER_PATCH = registerBlock("clover_patch",
        CloverPatchBlock(
            FabricBlockSettings.create().breakInstantly().nonOpaque()
        )
    )

    @JvmField
    val BOMB_FLOWER = registerBlock("bomb_flower",
        BombFlowerBlock(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val GODTRIBE_STONE = registerBlock("godtribe_stone",
        GodtribeStone(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val GODTRIBE_PILLAR = registerBlock("godtribe_pillar",
        GodtribeStone(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val TILEABLE_GODTRIBE_PATTERN = registerBlock("tileable_godtribe_pattern",
        GodtribeStone(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val CHISELED_GODTRIBE_PILLAR = registerBlock("chiseled_godtribe_pillar",
        GodtribeStone(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val CHISELED_GODTRIBE_STONE = registerBlock("chiseled_godtribe_stone",
        GodtribeStone(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val CRACKED_GODTRIBE_PILLAR = registerBlock("cracked_godtribe_pillar",
        GodtribeStone(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val GODTRIBE_BRICKS = registerBlock("godtribe_bricks",
        GodtribeStone(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val GODTRIBE_BRICKS_PILLAR = registerBlock("godtribe_bricks_pillar",
        GodtribeStone(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val POLISHED_GODTRIBE_STONE = registerBlock("polished_godtribe_stone",
        GodtribeStone(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val SWIRL_PATTERN_GODTRIBE_PILLAR = registerBlock("swirl_pattern_godtribe_pillar",
        GodtribeStone(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val TRIFORCE_PATTERN_GODTRIBE_PILLAR = registerBlock("triforce_pattern_godtribe_pillar",
        GodtribeStone(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val TRIFORCE_PATTERN_GODTRIBE_STONE = registerBlock("triforce_pattern_godtribe_stone",
        GodtribeStone(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmStatic
    fun registerDungeonUtilsBlocks() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its blocks.")
    }
}