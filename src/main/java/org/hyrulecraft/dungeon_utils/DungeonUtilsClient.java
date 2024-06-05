package org.hyrulecraft.dungeon_utils;

import net.fabricmc.api.ClientModInitializer;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.util.UtilCollector;

public class DungeonUtilsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its main client class.");

        // Initializing classes.
        UtilCollector.registerAllUtilClassesInClient();

        // To do with bows.
        // TODO: FIX THIS IT IS BROKEN
        ModelPredicateProviderRegistry.register(DungeonUtilsItems.LYNEL_BOW_FIVE_X, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (entity.getActiveItem() != stack) {
                return 0.0f;
            }
            return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
        });
        ModelPredicateProviderRegistry.register(DungeonUtilsItems.LYNEL_BOW_THREE_X, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0f;
            }
            if (entity.getActiveItem() != stack) {
                return 0.0f;
            }
            return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
        });
    }
}