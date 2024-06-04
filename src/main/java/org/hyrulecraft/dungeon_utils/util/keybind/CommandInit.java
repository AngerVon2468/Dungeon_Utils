package org.hyrulecraft.dungeon_utils.util.keybind;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import org.hyrulecraft.dungeon_utils.DungeonUtils;

public class CommandInit {

    public static void kys() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("kys")
                .executes(context -> {

                    if (context.getSource().isExecutedByPlayer()) {

                        context.getSource().getPlayer().kill();

                    } else {

                        context.getSource().sendFeedback(() -> Text.literal("Command was run by an non-player source."), true);
                        DungeonUtils.LOGGER.error("Command was run by an non-player source.");

                    }

                    return 1;

                })));
    }

    public static void suicide() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("suicide")
                .executes(context -> {

                    if (context.getSource().isExecutedByPlayer()) {

                        context.getSource().getPlayer().kill();

                    } else {

                        context.getSource().sendFeedback(() -> Text.literal("Command was run by an non-player source."), true);
                        DungeonUtils.LOGGER.error("Command was run by an non-player source.");

                    }

                    return 1;

                })));
    }

    public static void trashHand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("trash_hand")
                .executes(context -> {

                    if (context.getSource().isExecutedByPlayer()) {

                        ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                        ItemStack stack = serverPlayer.getStackInHand(serverPlayer.getActiveHand());
                        stack.decrement(stack.getCount());

                    } else {

                        context.getSource().sendFeedback(() -> Text.literal("Command was run by an non-player source."), true);
                        DungeonUtils.LOGGER.error("Command was run by an non-player source.");

                    }

                    return 1;

                })));
    }

    public static void wiiuCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("wiiu")
                .executes(context -> {

                    context.getSource().sendFeedback(() -> Text.literal("WiiU"), false);
                    return 1;

                })));
    }
}