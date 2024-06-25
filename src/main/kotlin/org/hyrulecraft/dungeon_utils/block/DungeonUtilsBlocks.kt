package org.hyrulecraft.dungeon_utils.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings

import net.minecraft.block.Block
import net.minecraft.item.*
import net.minecraft.registry.*
import net.minecraft.util.Identifier

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.BlueSwitchBlock
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.PedestalBlock
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.RustySwitchBlock
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.YellowSwitchBlock

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
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val RUSTY_SWITCH_BLOCK = registerBlock("rusty_switch_block",
        RustySwitchBlock(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val BLUE_SWITCH_BLOCK = registerBlock("blue_switch_block",
        BlueSwitchBlock(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val PEDESTAL_BLOCK = registerBlock("pedestal_block",
        PedestalBlock(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmStatic
    fun registerModBlocks() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its blocks.")
    }
}