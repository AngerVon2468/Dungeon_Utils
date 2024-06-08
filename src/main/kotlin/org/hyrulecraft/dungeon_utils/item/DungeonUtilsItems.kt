package org.hyrulecraft.dungeon_utils.item

import com.github.crimsondawn45.fabricshieldlib.lib.`object`.FabricShieldItem

import net.fabricmc.fabric.api.item.v1.FabricItemSettings

import net.minecraft.item.*
import net.minecraft.registry.*
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity

import org.hyrulecraft.dungeon_utils.DungeonUtils
import org.hyrulecraft.dungeon_utils.item.itemtype.*
import org.hyrulecraft.dungeon_utils.item.itemtype.bow.lynel.*
import org.hyrulecraft.dungeon_utils.item.itemtype.champion.ability.daruk.DaruksProtectionItem
import org.hyrulecraft.dungeon_utils.item.itemtype.champion.ability.mipha.*
import org.hyrulecraft.dungeon_utils.item.itemtype.champion.ability.revali.*
import org.hyrulecraft.dungeon_utils.item.itemtype.clothing.glove.*
import org.hyrulecraft.dungeon_utils.item.itemtype.clothing.link.CapTrinketItem

object DungeonUtilsItems {

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier(DungeonUtils.MOD_ID, name), item)
    }

    @JvmField
    val PARAGLIDER = registerItem("paraglider", ParagliderTrinketItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val MOGMA_MITTS = registerItem("mogma_mitts", MogmaMittsItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val CLIMBING_GLOVES = registerItem("climbing_gloves", ClimbingGlovesItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val MEGATON_HAMMER = registerItem("megaton_hammer", MegatonHammerItem(ToolMaterials.IRON, 8, -2f, FabricItemSettings()))

    @JvmField val LYNEL_BOW_FIVE_X = registerItem("lynel_bow_five_x", LynelBowFiveXItem(FabricItemSettings().maxDamage(1024)))

    @JvmField
    val LYNEL_BOW_THREE_X = registerItem("lynel_bow_three_x", LynelBowThreeXItem(FabricItemSettings().maxDamage(1024)))

    private val CHAMPION_ABILITY = FabricItemSettings().maxDamage(0).maxCount(1)

    @JvmField
    val REVALIS_GALE = registerItem("revalis_gale", RevalisGaleItem(CHAMPION_ABILITY))

    @JvmField
    val REVALIS_GALE_PLUS = registerItem("revalis_gale_plus", RevalisGalePlusItem(CHAMPION_ABILITY))

    @JvmField
    val MIPHAS_GRACE = registerItem("miphas_grace", MiphasGraceItem(CHAMPION_ABILITY))

    @JvmField
    val MIPHAS_GRACE_PLUS = registerItem("miphas_grace_plus", MiphasGracePlusItem(CHAMPION_ABILITY))

    @JvmField
    val DARUKS_PROTECTION = registerItem("daruks_protection", DaruksProtectionItem(CHAMPION_ABILITY))

    @JvmField
    val LINK_CAP = registerItem("link_cap", CapTrinketItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val KOKIRI_SWORD = registerItem("kokiri_sword", SwordItem(ToolMaterials.IRON, 3, -3.0f, FabricItemSettings().maxDamage(200)))

    @JvmField
    val RAZOR_SWORD = registerItem("razor_sword", SwordItem(ToolMaterials.IRON, 6, -2.5f, FabricItemSettings().maxDamage(400)))

    @JvmField
    val GILDED_SWORD = registerItem("gilded_sword", SwordItem(ToolMaterials.IRON, 10, -2.0f, FabricItemSettings().maxDamage(700)))

    @JvmField
    val THE_MASTER_SWORD = registerItem("the_master_sword", SwordItem(ToolMaterials.DIAMOND, 12, -2.6f, FabricItemSettings().maxDamage(2048)))

    @JvmField
    val THE_MASTER_SWORD_AWAKENED = registerItem("the_master_sword_awakened", SwordItem(ToolMaterials.DIAMOND, 14, -2.8f, FabricItemSettings().maxDamage(4096)))

    @JvmField val THE_HYLIAN_SHIELD = registerItem("the_hylian_shield",
        FabricShieldItem(FabricItemSettings().maxDamage(8192), 10, 100, Items.NETHERITE_BLOCK))

    @JvmField val KOKIRI_SHIELD = registerItem("kokiri_shield",
        FabricShieldItem(FabricItemSettings().maxDamage(500), 25, 5, Items.OAK_PLANKS))

    @JvmField
    val HEART_CONTAINER = registerItem("heart_container", HeartContainerItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmStatic
    fun registerModItems() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its items.")
    }
}