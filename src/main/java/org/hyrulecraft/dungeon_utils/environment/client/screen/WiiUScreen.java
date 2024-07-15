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
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("WiiU is based"), this.width / 2, this.height - 100, 0xffffff);
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }

    public ButtonWidget buttonYes = ButtonWidget.builder(Text.translatable("title.dungeon_utils.wiiu.button_yes"), button -> newScreen(new MultiplayerScreen(this.parent)))
            .dimensions(this.width / 2 - 205, 20, 200, 20)
            .build();
    public ButtonWidget buttonNo = ButtonWidget.builder(Text.translatable("title.dungeon_utils.wiiu.button_no"), button -> {
                throw new RuntimeException("fuck you");
            })
            .dimensions(this.width / 2 + 5, 20, 200, 20)
            .build();

    @Override
    protected void init() {
        this.buttonYes.setPosition(this.width / 2 - 205, 220);
        this.buttonNo.setPosition(this.width / 2 + 5, 220);

        this.addDrawableChild(buttonYes);
        this.addDrawableChild(buttonNo);
    }

    public void newScreen(Screen screen) {
        this.nextScreen = screen;
        this.close();
    }

    @Override
    @SuppressWarnings("all")
    public void close() {
        if (this.nextScreen != null) {

            this.client.setScreen(nextScreen);

        } else {

            this.client.setScreen(parent);

        }
    }
}