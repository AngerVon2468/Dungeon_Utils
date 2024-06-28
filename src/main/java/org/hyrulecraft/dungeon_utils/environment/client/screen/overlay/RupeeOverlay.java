package org.hyrulecraft.dungeon_utils.environment.client.screen.overlay;

import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;

@Environment(EnvType.CLIENT)
public class RupeeOverlay {

    public static void render(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        int scaledWidth = client.getWindow().getScaledWidth();
        int scaledHeight = client.getWindow().getScaledHeight();
        ClientPlayerEntity clientPlayer = client.clientPlayer;
        if (clientPlayer.getStackInHand(clientPlayer.getActiveHand()).isOf(DungeonUtilsItems.CHILD_RUPEE_WALLET)) {
            drawContext.drawText(client.textRenderer, Text.translatable("gui.dungeon_utils.rupee_render_1"), scaledWidth / 2 + -208, scaledHeight / 2 + 104, -1, true);
            drawContext.drawText(client.textRenderer, Text.translatable("gui.dungeon_utils.rupee_render_2"), scaledWidth / 2 + -170, scaledHeight / 2 + 104, -1, true);
        }
    }

    public static void registerOverlay() {
        HudRenderCallback.EVENT.register(RupeeOverlay::render);
    }
}