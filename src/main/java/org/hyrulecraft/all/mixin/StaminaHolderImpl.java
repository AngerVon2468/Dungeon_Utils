package org.hyrulecraft.all.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

import org.hyrulecraft.dungeon_utils.util.nbt.IStaminaHolder;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class StaminaHolderImpl implements IStaminaHolder {

    @Unique
    public Float stamina = 20.0f;

    @Unique
    public Float maxStamina = 20.0f;

    @Inject(method = "readNbt", at = @At("TAIL"))
    public void readNbt(@NotNull NbtCompound nbtCompound, CallbackInfo ci) {
        this.stamina = nbtCompound.getFloat("stamina");
        this.maxStamina = nbtCompound.getFloat("maxStamina") > 0 ? nbtCompound.getFloat("maxStamina") : this.maxStamina;
    }

    @Inject(method = "writeNbt", at = @At("TAIL"))
    public void writeNbt(@NotNull NbtCompound nbtCompound, CallbackInfoReturnable<NbtCompound> cir) {
        nbtCompound.putFloat("stamina", this.stamina);
        nbtCompound.putFloat("maxStamina", this.maxStamina);
    }

    @Override
    public void setStamina(Float amount) {
        this.stamina = amount <= this.maxStamina && amount >= 0 ? amount : this.stamina;
    }

    @Override
    public void setMaxStamina(Float amount) {
        this.maxStamina = amount > 0 ? amount : this.maxStamina;
    }

    @Override
    public void increaseStamina(Float amount) {
        this.stamina = this.stamina + amount <= this.maxStamina && this.stamina + amount >= 0 ? this.stamina + amount : this.stamina;
    }

    @Override
    public void decreaseStamina(Float amount) {
        this.increaseStamina(-amount);
    }

    @Override
    public void increaseMaxStamina(Float amount) {
        this.maxStamina += amount;
    }

    @Override
    public void decreaseMaxStamina(Float amount) {
        this.increaseMaxStamina(-amount);
    }

    @Override
    public void resetStamina() {
        this.stamina = this.getMaxStamina();
    }

    @Override
    public void resetMaxStamina() {
        this.maxStamina = 20.0f;
        this.stamina = this.stamina > this.maxStamina ? this.maxStamina : this.stamina;
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