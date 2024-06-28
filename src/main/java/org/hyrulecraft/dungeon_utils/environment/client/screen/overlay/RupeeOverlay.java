package org.hyrulecraft.dungeon_utils.environment.client.screen.overlay;

import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;

@Environment(EnvType.CLIENT)
public class RupeeOverlay {

    public static void render(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        int scaledWidth = client.getWindow().getScaledWidth();
        int scaledHeight = client.getWindow().getScaledHeight();
        ClientPlayerEntity clientPlayer = client.clientPlayer;
        ItemStack stack = clientPlayer.getStackInHand(clientPlayer.getActiveHand());
        if (stack.isOf(DungeonUtilsItems.CHILD_RUPEE_WALLET)) {

            drawContext.drawText(client.textRenderer, Text.translatable("gui.dungeon_utils.rupee_render_1"), scaledWidth / 2 + -208, scaledHeight / 2 + 104, -1, true);
            if (stack.getNbt() != null) {

                int amount = stack.getNbt().getInt("dungeon_utils.rupee.amount");
                String amountAsString = Integer.toString(amount);
                drawContext.drawText(client.textRenderer, Text.literal(amountAsString), scaledWidth / 2 + -168, scaledHeight / 2 + 104, -1, true);

            }

        }
    }

    public static void registerOverlay() {
        HudRenderCallback.EVENT.register(RupeeOverlay::render);
    }
}