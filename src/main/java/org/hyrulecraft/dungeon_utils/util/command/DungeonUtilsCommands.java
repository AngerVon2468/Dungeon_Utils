package org.hyrulecraft.dungeon_utils.util.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.*;

import org.hyrulecraft.dungeon_utils.DungeonUtils;
import org.hyrulecraft.dungeon_utils.block.DungeonUtilsBlocks;
import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;

import java.util.List;

public class DungeonUtilsCommands {

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

                            if (context.getSource().isExecutedByPlayer() && FabricLoader.getInstance().isDevelopmentEnvironment()) {

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

    public static void dungeonUtils() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("dungeon_utils")
                .executes(context -> {
                    return 1;
                })
                .then(CommandManager.literal("item")
                        .executes(context -> {
                            context.getSource().sendFeedback(() -> Text.literal("Please specify an item to give"), false);
                            return 1;
                        })
                        .then(CommandManager.literal("paraglider")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.PARAGLIDER.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.PARAGLIDER.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("mogma_mitts")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.MOGMA_MITTS.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.MOGMA_MITTS.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("climbing_gloves")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.CLIMBING_GLOVES.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.CLIMBING_GLOVES.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("megaton_hammer")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.MEGATON_HAMMER.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.MEGATON_HAMMER.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("lynel_bow_five_x")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.LYNEL_BOW_FIVE_X.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.LYNEL_BOW_FIVE_X.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("lynel_bow_three_x")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.LYNEL_BOW_THREE_X.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.LYNEL_BOW_THREE_X.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("revalis_gale")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.REVALIS_GALE.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.REVALIS_GALE.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("revalis_gale_plus")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.REVALIS_GALE_PLUS.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.REVALIS_GALE_PLUS.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("miphas_grace")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.MIPHAS_GRACE.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.MIPHAS_GRACE.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("miphas_grace_plus")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.MIPHAS_GRACE_PLUS.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.MIPHAS_GRACE_PLUS.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("daruks_protection")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.DARUKS_PROTECTION.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.DARUKS_PROTECTION.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("link_cap")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.LINK_CAP.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.LINK_CAP.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("kokiri_sword")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.KOKIRI_SWORD.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.KOKIRI_SWORD.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("razor_sword")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.RAZOR_SWORD.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.RAZOR_SWORD.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("gilded_sword")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.GILDED_SWORD.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.GILDED_SWORD.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("the_master_sword")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.THE_MASTER_SWORD.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.THE_MASTER_SWORD.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("the_master_sword_awakened")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsItems.THE_MASTER_SWORD_AWAKENED.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsItems.THE_MASTER_SWORD_AWAKENED.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                )
                .then(CommandManager.literal("block")
                        .executes(context -> {
                            context.getSource().sendFeedback(() -> Text.literal("Please specify a block to give"), false);
                            return 1;
                        })
                        .then(CommandManager.literal("yellow_switch_block")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsBlocks.YELLOW_SWITCH_BLOCK.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsBlocks.YELLOW_SWITCH_BLOCK.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("rusty_switch_block")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsBlocks.RUSTY_SWITCH_BLOCK.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsBlocks.RUSTY_SWITCH_BLOCK.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("blue_switch_block")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsBlocks.BLUE_SWITCH_BLOCK.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsBlocks.BLUE_SWITCH_BLOCK.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                        .then(CommandManager.literal("pedestal_block")
                                .executes(context -> {

                                    ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                    assert serverPlayer != null;
                                    int emptySlots = 0;
                                    List<ItemStack> inventory = serverPlayer.getInventory().main;
                                    for (ItemStack stack : inventory) {
                                        if (stack.isEmpty()) {
                                            emptySlots++;
                                        }
                                    }

                                    if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {

                                        serverPlayer.getInventory().insertStack(DungeonUtilsBlocks.PEDESTAL_BLOCK.asItem().getDefaultStack());

                                        emptySlots--;

                                        return 1;

                                    } else {

                                        return -1;

                                    }

                                })
                                .then(CommandManager.argument("amount", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            int amount = IntegerArgumentType.getInteger(context, "amount");
                                            ServerPlayerEntity serverPlayer = context.getSource().getPlayer();
                                            assert serverPlayer != null;
                                            int emptySlots = 0;
                                            List<ItemStack> inventory = serverPlayer.getInventory().main;
                                            for (ItemStack stack : inventory) {
                                                if (stack.isEmpty()) {
                                                    emptySlots++;
                                                }
                                            }

                                            if (context.getSource().isExecutedByPlayer() && emptySlots >= 1) {


                                                for (int i = 100; amount >= 1; amount--) {

                                                    serverPlayer.getInventory().insertStack(DungeonUtilsBlocks.PEDESTAL_BLOCK.asItem().getDefaultStack());

                                                }

                                                emptySlots--;

                                                return 1;

                                            } else {

                                                return -1;

                                            }
                                        }))
                        )
                )
        ));
    }
}