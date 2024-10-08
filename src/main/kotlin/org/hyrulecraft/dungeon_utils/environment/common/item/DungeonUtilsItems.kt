package org.hyrulecraft.dungeon_utils.environment.common.item

import com.github.crimsondawn45.fabricshieldlib.lib.`object`.FabricShieldItem

import net.fabricmc.fabric.api.item.v1.FabricItemSettings

import net.lightglow.cosmlib.common.item.ClothingItem

import net.minecraft.item.*
import net.minecraft.registry.*
import net.minecraft.util.Identifier

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils
import org.hyrulecraft.dungeon_utils.environment.common.item.armour.LinkArmourMaterials
import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.*
import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bomb.*
import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bottle.*
import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bow.lynel.*
import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.champion.weapon.revali.GreatEagleBowItem
import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.champion.ability.daruk.DaruksProtectionItem
import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.champion.ability.mipha.*
import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.champion.ability.revali.*
import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.clothing.glove.*
import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.clothing.link.*
import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.mask.*
import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.rupee.*

object DungeonUtilsItems {

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(RegistryTypes.ITEM, Identifier(DungeonUtils.MOD_ID, name), item)
    }

    @JvmField
    val PARAGLIDER = registerItem("paraglider", ParagliderTrinketItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val MOGMA_MITTS = registerItem("mogma_mitts", MogmaMittsItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val CLIMBING_GLOVES = registerItem("climbing_gloves", ClimbingGlovesItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val MEGATON_HAMMER = registerItem("megaton_hammer", MegatonHammerItem(ToolMaterials.IRON, 8, -2f, FabricItemSettings()))

    @JvmField
    val LYNEL_BOW_FIVE_X = registerItem("lynel_bow_five_x", LynelBowFiveXItem(FabricItemSettings().maxDamage(1024)))

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
    val LINK_TUNIC = registerItem("link_tunic", TunicTrinketItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val LINK_SLEEVES = registerItem("link_sleeves", ArmorItem(LinkArmourMaterials.LINK_ARMOUR_MATERIAL, ArmorItem.Type.CHESTPLATE, FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val KOKIRI_SWORD = registerItem("kokiri_sword", SwordItem(ToolMaterials.IRON, 3, -3.0f, FabricItemSettings().maxDamage(200)))

    @JvmField
    val RAZOR_SWORD = registerItem("razor_sword", SwordItem(ToolMaterials.IRON, 6, -2.5f, FabricItemSettings().maxDamage(400)))

    @JvmField
    val GILDED_SWORD = registerItem("gilded_sword", SwordItem(ToolMaterials.IRON, 10, -2.0f, FabricItemSettings().maxDamage(700)))

    @JvmField
    val THE_MASTER_SWORD = registerItem("the_master_sword", MasterSwordItem(ToolMaterials.DIAMOND, 12, -1.8f, FabricItemSettings().maxDamage(2048)))

    @JvmField
    val THE_MASTER_SWORD_AWAKENED = registerItem("the_master_sword_awakened", MasterSwordItem(ToolMaterials.DIAMOND, 14, -1.8f, FabricItemSettings().maxDamage(4096)))

    @JvmField
    val THE_HYLIAN_SHIELD = registerItem("the_hylian_shield", FabricShieldItem(FabricItemSettings().maxDamage(8192), 10, 0, Items.DIAMOND))

    @JvmField
    val KOKIRI_SHIELD = registerItem("kokiri_shield", ShieldSurfItem(FabricItemSettings().maxDamage(500), 25, 0, Items.OAK_PLANKS))

    @JvmField
    val HEART_CONTAINER = registerItem("heart_container", HeartContainerItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val HEART_PIECE = registerItem("heart_piece", Item(FabricItemSettings().maxCount(64).maxDamage(0)))

    @JvmField
    val GREAT_EAGLE_BOW = registerItem("great_eagle_bow", GreatEagleBowItem(FabricItemSettings().maxDamage(1500)))

    @JvmField
    val SHADOW_HYLIAN_SHIELD = registerItem("shadow_hylian_shield", FabricShieldItem(FabricItemSettings().maxDamage(8192), 10, 0, Items.DIAMOND))

    @JvmField
    val SHADOW_MASTER_SWORD = registerItem("shadow_master_sword", SwordItem(ToolMaterials.DIAMOND, 12, -1.8f, FabricItemSettings().maxDamage(2048)))

    @JvmField
    val CHILD_RUPEE_WALLET = registerItem("child_rupee_wallet", ChildRupeeWalletItem())

    @JvmField
    val ADULT_RUPEE_WALLET = registerItem("adult_rupee_wallet", AdultRupeeWalletItem())

    @JvmField
    val GIANT_RUPEE_WALLET = registerItem("giant_rupee_wallet", GiantRupeeWalletItem())

    @JvmField
    val BOMB_BAG = registerItem("bomb_bag", BombBagItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val BIG_BOMB_BAG = registerItem("big_bomb_bag", BigBombBagItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val BIGGEST_BOMB_BAG = registerItem("biggest_bomb_bag", BiggestBombBagItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val GREEN_RUPEE = registerItem("green_rupee", Item(FabricItemSettings().maxCount(64)))

    @JvmField
    val BLUE_RUPEE = registerItem("blue_rupee", Item(FabricItemSettings().maxCount(64)))

    @JvmField
    val HOOKSHOT = registerItem("hookshot", HookshotItem(FabricItemSettings().maxCount(1)))

    @JvmField
    val TOTK_LINK = registerItem("totk_link", ClothingItem(DungeonUtils.MOD_ID, "totk_link", true, FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val BOTW_LINK = registerItem("botw_link", ClothingItem(DungeonUtils.MOD_ID, "botw_link", true, FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val SHADOW_LINK = registerItem("shadow_link", ClothingItem(DungeonUtils.MOD_ID, "shadow_link", true, FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val ADULT_LINK = registerItem("adult_link", ClothingItem(DungeonUtils.MOD_ID, "adult_link", true, FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val YOUNG_LINK = registerItem("young_link", ClothingItem(DungeonUtils.MOD_ID, "young_link", true, FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val SHEIKAH_SLATE = registerItem("sheikah_slate", SheikahSlateItem(FabricItemSettings().maxDamage(0).maxCount(1)))

    @JvmField
    val BOMB = registerItem("bomb", BombItem(FabricItemSettings().maxDamage(0).maxCount(1)))

    @JvmField
    val BUG_BOTTLE = registerItem("bug_bottle", BugBottleItem())

    @JvmField
    val FAIRY_BOTTLE = registerItem("fairy_bottle", FairyBottleItem())

    @JvmField
    val FISH_BOTTLE = registerItem("fish_bottle", FishBottleItem())

    @JvmField
    val MILK_BOTTLE = registerItem("milk_bottle", Item(FabricItemSettings().maxDamage(0).maxCount(1)))

    @JvmField
    val BOKOBLIN_HEAD = registerItem("bokoblin_head", BokoblinHeadItem())

    @JvmField
    val MAJORAS_MASK = registerItem("majoras_mask", MajorasMaskItem())

    @JvmField
    val GIANTS_MASK = registerItem("giants_mask", GiantsMaskItem())

    @JvmField
    val GREAT_FAIRY_MASK = registerItem("great_fairy_mask", GreatFairyMaskItem())

    @JvmField
    val KEATON_MASK = registerItem("keaton_mask", KeatonMaskItem())

    @JvmField
    val STAMINA_CONTAINER = registerItem("stamina_container", StaminaContainerItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmStatic
    fun registerDungeonUtilsItems() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its items.")
    }
}