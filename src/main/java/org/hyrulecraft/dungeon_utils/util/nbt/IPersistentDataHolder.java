package org.hyrulecraft.dungeon_utils.util.nbt;

import net.minecraft.nbt.NbtCompound;

public interface IPersistentDataHolder {

    default NbtCompound getPersistentData() {
        return new NbtCompound();
    }
}