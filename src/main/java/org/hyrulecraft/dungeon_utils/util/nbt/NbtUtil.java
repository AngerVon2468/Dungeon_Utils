package org.hyrulecraft.dungeon_utils.util.nbt;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import org.jetbrains.annotations.NotNull;

public class NbtUtil {

    public static void setNbt(@NotNull ItemStack stack, String key, String value) {

        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putString(key, value);
        stack.setNbt(nbt);

    }

    public static void writeNbt(@NotNull ItemStack stack, String key, Integer value) {

        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt(key, value);
        stack.writeNbt(nbt);

    }

    public static @NotNull NbtCompound createNbt(String key, Integer value) {

        NbtCompound nbt = new NbtCompound();
        nbt.putInt(key, value);

        return nbt;
    }
}