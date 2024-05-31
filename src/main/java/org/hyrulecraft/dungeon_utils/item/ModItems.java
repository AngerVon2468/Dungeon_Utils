package org.hyrulecraft.dungeon_utils.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.item.itemtype.*;
import org.hyrulecraft.dungeon_utils.DungeonUtils;
import org.hyrulecraft.dungeon_utils.item.itemtype.champion.ability.mipha.*;
import org.hyrulecraft.dungeon_utils.item.itemtype.champion.ability.revali.*;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(DungeonUtils.MOD_ID, name), item);
    }

    public static final Item MEGATON_HAMMER = registerItem("megaton_hammer",
            new MegatonHammerItem(ToolMaterials.IRON, 8, -2, new FabricItemSettings()));

    public static final Item LYNEL_BOW = registerItem("lynel_bow",
            new LynelBowItem(new FabricItemSettings().maxDamage(1024)));

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

    public static void registerModItems() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its items.");
    }
}