package org.hyrulecraft.dungeon_utils.environment.client.screen;

import net.fabricmc.api.*;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class WiiUScreen extends Screen {

    private Screen parent;
    private Screen nextScreen;

    public WiiUScreen(Screen parent) {
        super(Text.translatable("title.dungeon_utils.wiiu"));
        this.parent = parent;
    }

    @Override
    public void render(@NotNull DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("WiiU is based"), width / 2, height - 100, 0xffffff);
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }

    public ButtonWidget buttonYes = ButtonWidget.builder(Text.translatable("title.dungeon_utils.wiiu.button_yes"), button -> newScreen(new MultiplayerScreen(parent)))
            .dimensions(width / 2 - 205, 20, 200, 20)
            .build();
    public ButtonWidget buttonNo = ButtonWidget.builder(Text.translatable("title.dungeon_utils.wiiu.button_no"), button -> {
                throw new RuntimeException("fuck you");
            })
            .dimensions(width / 2 + 5, 20, 200, 20)
            .build();

    @Override
    protected void init() {
        buttonYes.setPosition(width / 2 - 205, 220);
        buttonNo.setPosition(width / 2 + 5, 220);

        addDrawableChild(buttonYes);
        addDrawableChild(buttonNo);
    }

    public void newScreen(Screen screen) {
        nextScreen = screen;
        close();
    }

    @Override
    @SuppressWarnings("all")
    public void close() {
        if (nextScreen != null) {

            client.setScreen(nextScreen);

        } else {

            client.setScreen(parent);

        }
    }
}