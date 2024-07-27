package org.hyrulecraft.dungeon_utils.environment.client.screen.overlay;

import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.environment.common.tags.DungeonUtilsTags;

@Environment(EnvType.CLIENT)
public class DungeonUtilsOverlays {

    public static void render(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        int scaledWidth = client.getWindow().getScaledWidth();
        int scaledHeight = client.getWindow().getScaledHeight();
        ClientPlayerEntity clientPlayer = client.clientPlayer;
        ItemStack stack = clientPlayer.getStackInHand(clientPlayer.getActiveHand());
        if (stack.isIn(DungeonUtilsTags.Items.RUPEE_WALLETS)) {

            drawContext.drawItem(DungeonUtilsItems.GREEN_RUPEE.getDefaultStack(), scaledWidth / 2 + -225, scaledHeight / 2 + 100, -1);
            drawContext.drawText(client.textRenderer, Text.translatable("gui.dungeon_utils.rupee_render_1"), scaledWidth / 2 + -208, scaledHeight / 2 + 104, -1, true);
            if (stack.getNbt() != null) {

                int amount = stack.getNbt().getInt("dungeon_utils.rupee.amount");
                String amountAsString = Integer.toString(amount);
                drawContext.drawText(client.textRenderer, Text.literal(amountAsString), scaledWidth / 2 + -168, scaledHeight / 2 + 104, -1, true);

            }

        } else if (stack.isIn(DungeonUtilsTags.Items.BOMB_BAGS)) {

            drawContext.drawItem(DungeonUtilsItems.BOMB.getDefaultStack(), scaledWidth / 2 + -225, scaledHeight / 2 + 100, -1);
            drawContext.drawText(client.textRenderer, Text.translatable("gui.dungeon_utils.bomb_render_1"), scaledWidth / 2 + -208, scaledHeight / 2 + 104, -1, true);
            if (stack.getNbt() != null) {

                int amount = stack.getNbt().getInt("dungeon_utils.bomb.amount");
                String amountAsString = Integer.toString(amount);
                drawContext.drawText(client.textRenderer, Text.literal(amountAsString), scaledWidth / 2 + -168, scaledHeight / 2 + 104, -1, true);

            }

        }
    }

    public static void registerOverlays() {
        HudRenderCallback.EVENT.register(DungeonUtilsOverlays::render);
    }
}