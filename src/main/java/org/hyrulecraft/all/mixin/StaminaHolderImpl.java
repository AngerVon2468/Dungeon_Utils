package org.hyrulecraft.all.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

import org.hyrulecraft.dungeon_utils.util.IStaminaHolder;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class StaminaHolderImpl implements IStaminaHolder {

    @Unique
    public Float stamina = 20.0f;

    @Unique
    public Float maxStamina = 20.0f;

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readNbt(@NotNull NbtCompound nbtCompound, CallbackInfo ci) {
        this.stamina = nbtCompound.getFloat("stamina");
        this.maxStamina = nbtCompound.getFloat("maxStamina");
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeNbt(@NotNull NbtCompound nbtCompound, CallbackInfo ci) {
        nbtCompound.putFloat("stamina", this.stamina);
        nbtCompound.putFloat("maxStamina", this.maxStamina);
    }

    @Override
    public void setStamina(Float amount) {
        this.stamina = amount;
    }

    @Override
    public void setMaxStamina(Float amount) {
        this.maxStamina = amount;
    }

    @Override
    public void addStamina(Float amount) {
        this.stamina += amount;
    }

    @Override
    public void removeStamina(Float amount) {
        this.stamina -= amount;
    }

    @Override
    public void resetStamina() {
        this.stamina = 20.0f;
    }

    @Override
    public void resetMaxStamina() {
        this.maxStamina = 20.0f;
    }

    @Override
    public Float getStamina() {
        return this.stamina;
    }

    @Override
    public Float getMaxStamina() {
        return this.maxStamina;
    }
}