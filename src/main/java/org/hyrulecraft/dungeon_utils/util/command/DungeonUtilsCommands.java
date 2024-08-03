package org.hyrulecraft.dungeon_utils.util.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.zigythebird.playeranimatorapi.API.PlayerAnimAPI;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.*;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;

public class DungeonUtilsCommands {

    public static final Identifier SHOWOFF = new Identifier(DungeonUtils.MOD_ID, "showoff");

    public static void showoff() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("showoff")
                .executes(context -> {

                            if (context.getSource().isExecutedByPlayer()) {

                                PlayerAnimAPI.playPlayerAnim(context.getSource().getWorld(), context.getSource().getPlayer(), SHOWOFF);
                                return 1;

                            } else {

                                context.getSource().sendFeedback(() -> Text.literal("Command was run by an non-player source."), true);
                                DungeonUtils.LOGGER.error("Command was run by an non-player source.");
                                return -1;

                            }

                })
        ));
    }

    public static void addMana() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("addMana")
                .executes(context -> {

                    return -1;

                }).then(CommandManager.argument("amount", IntegerArgumentType.integer())
                        .executes((context) -> {
                            if (context.getSource().isExecutedByPlayer()) {

                                int amount = IntegerArgumentType.getInteger(context, "amount");
                                context.getSource().getPlayer().addMana(amount);
                                return 1;

                            } else {

                                context.getSource().sendFeedback(() -> Text.literal("Command was run by an non-player source."), true);
                                DungeonUtils.LOGGER.error("Command was run by an non-player source.");
                                return -1;

                            }
                        }
                ))
        ));
    }

    public static void setMana() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("setMana")
                .executes(context -> {

                    return -1;

                }).then(CommandManager.argument("amount", IntegerArgumentType.integer())
                        .executes((context) -> {
                                    if (context.getSource().isExecutedByPlayer()) {

                                        int amount = IntegerArgumentType.getInteger(context, "amount");
                                        context.getSource().getPlayer().setMana(amount);
                                        return 1;

                                    } else {

                                        context.getSource().sendFeedback(() -> Text.literal("Command was run by an non-player source."), true);
                                        DungeonUtils.LOGGER.error("Command was run by an non-player source.");
                                        return -1;

                                    }
                                }
                        ))
        ));
    }

    public static void kys() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("kys")
                .executes(context -> {

                    if (context.getSource().isExecutedByPlayer()) {

                        context.getSource().getPlayer().kill();
                        return 1;

                    } else {

                        context.getSource().sendFeedback(() -> Text.literal("Command was run by an non-player source."), true);
                        DungeonUtils.LOGGER.error("Command was run by an non-player source.");
                        return -1;

                    }

                })
        ));
    }

    public static void suicide() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("suicide")
                .executes(context -> {

                    if (context.getSource().isExecutedByPlayer()) {

                        context.getSource().getPlayer().kill();
                        return 1;

                    } else {

                        context.getSource().sendFeedback(() -> Text.literal("Command was run by an non-player source."), true);
                        DungeonUtils.LOGGER.error("Command was run by an non-player source.");
                        return -1;

                    }

                })
        ));
    }

    public static void trashHand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("trash_hand")
                .executes(context -> {

                    if (context.getSource().isExecutedByPlayer()) {

                        ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                        assert serverPlayer != null;
                        ItemStack stack = serverPlayer.getStackInHand(serverPlayer.getActiveHand());

                        if (!stack.isEmpty()) {

                            stack.decrement(stack.getCount());
                            return 1;

                        } else {

                            context.getSource().sendFeedback(() -> Text.literal("You have no items in your hand!"), false);
                            return -1;

                        }

                    } else {

                        context.getSource().sendFeedback(() -> Text.literal("Command was run by an non-player source."), true);
                        DungeonUtils.LOGGER.error("Command was run by an non-player source.");
                        return -1;

                    }

                })
        ));
    }

    public static void wiiu() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("wiiu")
                .executes(context -> {

                    return -1;

                })
                .then(CommandManager.literal("good")
                        .executes(context -> {

                            if (context.getSource().isExecutedByPlayer()) {

                                context.getSource().getPlayer().setHealth(20.0f);
                                return 1;

                            } else {

                                return -1;

                            }

                        })
                )
                .then(CommandManager.literal("bad")
                        .executes(context -> {

                            if (context.getSource().isExecutedByPlayer()) {

                                MinecraftServer server = context.getSource().getServer();
                                context.getSource().getPlayer().kill();
                                DungeonUtils.LOGGER.error(":middle_finger:");
                                server.close();
                                MinecraftClient.getInstance().close();
                                return -1;

                            } else {

                                MinecraftServer server = context.getSource().getServer();
                                DungeonUtils.LOGGER.error(":middle_finger:");
                                server.close();
                                MinecraftClient.getInstance().close();
                                return -1;

                            }

                        })
                )
        ));
    }

    public static void hcDiscord() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("hc_discord")
                .executes(context -> {

                    context.getSource().sendFeedback(() -> Text.literal("HyruleCraft Discord link!").styled(style ->
                            style.withHoverEvent(
                                    new HoverEvent(
                                            HoverEvent.Action.SHOW_TEXT,
                                            Text.literal("https://discord.gg/qscBaKdwaT")
                                    )
                            ).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/qscBaKdwaT"))
                    ), false);

                    return 1;

                })
        ));
    }
}