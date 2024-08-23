package org.hyrulecraft.all.mixin;

import net.minecraft.item.*;

import org.jetbrains.annotations.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Items.class)
public abstract class ItemsMixin {

    @Contract("_ -> new")
    @Redirect(
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=glass_bottle"
                            },
                            ordinal = 0
                    )
            ),
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/item/GlassBottleItem;",
                    ordinal = 0
            ),
            method = "<clinit>")
    private static @NotNull GlassBottleItem newBottle(Item.@NotNull Settings settings) {
        return new GlassBottleItem(settings.maxCount(1));
    }
}