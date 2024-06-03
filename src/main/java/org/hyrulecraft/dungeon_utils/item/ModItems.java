package org.hyrulecraft.dungeon_utils.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.DungeonUtils;
import org.hyrulecraft.dungeon_utils.item.itemtype.*;
import org.hyrulecraft.dungeon_utils.item.itemtype.bow.lynel.*;
import org.hyrulecraft.dungeon_utils.item.itemtype.champion.ability.daruk.*;
import org.hyrulecraft.dungeon_utils.item.itemtype.champion.ability.mipha.*;
import org.hyrulecraft.dungeon_utils.item.itemtype.champion.ability.revali.*;
import org.hyrulecraft.dungeon_utils.item.itemtype.clothing.glove.*;
import org.hyrulecraft.dungeon_utils.item.itemtype.clothing.link.*;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(DungeonUtils.MOD_ID, name), item);
    }

    public static final Item PARAGLIDER = registerItem("paraglider",
            new ParagliderTrinketItem(new FabricItemSettings().maxCount(1).maxDamage(0)));

    public static final Item MOGMA_MITTS = registerItem("mogma_mitts",
            new MogmaMittsItem(new FabricItemSettings().maxCount(1).maxDamage(0)));

    public static final Item CLIMBING_GLOVES = registerItem("climbing_gloves",
            new ClimbingGlovesItem(new FabricItemSettings().maxCount(1).maxDamage(0)));

    public static final Item MEGATON_HAMMER = registerItem("megaton_hammer",
            new MegatonHammerItem(ToolMaterials.IRON, 8, -2, new FabricItemSettings()));

    public static final Item LYNEL_BOW_FIVE_X = registerItem("lynel_bow_five_x",
            new LynelBowFiveXItem(new FabricItemSettings().maxDamage(1024)));

    public static final Item LYNEL_BOW_THREE_X = registerItem("lynel_bow_three_x",
            new LynelBowThreeXItem(new FabricItemSettings().maxDamage(1024)));

    // Champion Abilities

    private static final FabricItemSettings CHAMPION_ABILITY = new FabricItemSettings().maxDamage(0).maxCount(1);

    public static final Item REVALIS_GALE = registerItem("revalis_gale",
            new RevalisGaleItem(CHAMPION_ABILITY));

    public static final Item REVALIS_GALE_PLUS = registerItem("revalis_gale_plus",
            new RevalisGalePlusItem(CHAMPION_ABILITY));

    public static final Item MIPHAS_GRACE = registerItem("miphas_grace",
            new MiphasGraceItem(CHAMPION_ABILITY));

    public static final Item MIPHAS_GRACE_PLUS = registerItem("miphas_grace_plus",
            new MiphasGracePlusItem(CHAMPION_ABILITY));

    public static final Item DARUKS_PROTECTION = registerItem("daruks_protection",
            new DaruksProtectionItem(CHAMPION_ABILITY));

    //

    public static final Item LINK_CAP = registerItem("link_cap",
            new CapTrinketItem(new FabricItemSettings()));

    public static final Item KOKIRI_SWORD = registerItem("kokiri_sword",
            new SwordItem(ToolMaterials.IRON, 3, -3.0f, new FabricItemSettings().maxDamage(200)));

    public static final Item RAZOR_SWORD = registerItem("razor_sword",
            new SwordItem(ToolMaterials.IRON, 6, -2.5f, new FabricItemSettings().maxDamage(400)));

    public static final Item GILDED_SWORD = registerItem("gilded_sword",
            new SwordItem(ToolMaterials.IRON, 10, -2.0f, new FabricItemSettings().maxDamage(700)));

    public static void registerModItems() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its items.");
    }
}