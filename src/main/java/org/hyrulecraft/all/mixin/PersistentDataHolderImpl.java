package org.hyrulecraft.all.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

import org.hyrulecraft.dungeon_utils.util.nbt.IPersistentDataHolder;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

/**
 * Borrowed code from the Fabric 1.20.X course by KaupenJoe, you should go check it out!!!<br>
 * (<a href="https://github.com/Kaupenjoe/Fabric-Course-1.20.X/blob/master/src/main/java/net/kaupenjoe/mccourse/mixin/ModEntityDataSaverMixin.java">Here's a link!</a>)
**/
@Mixin(Entity.class)
public abstract class PersistentDataHolderImpl implements IPersistentDataHolder {

    @Unique
    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if (this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return this.persistentData;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void writeNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> info) {
        if (this.persistentData != null) {
            nbt.put("dungeon_utils.persistent_data", this.persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void readNbt(@NotNull NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("dungeon_utils.persistent_data", 10)) {
            this.persistentData = nbt.getCompound("dungeon_utils.persistent_data");
        }
    }
}