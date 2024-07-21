package org.hyrulecraft.dungeon_utils.environment.common.entity.entity_type;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.mob.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.*;

import org.hyrulecraft.dungeon_utils.environment.common.entity.goal.FlyRandomlyGoal;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class FairyEntity extends FlyingEntity implements Tameable {

    @Nullable private final LivingEntity owner;

    public FairyEntity(EntityType<? extends FlyingEntity> type, World world) {
        super(type, world);
        this.owner = null;
    }

    public static DefaultAttributeContainer.Builder createFairyAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0f);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new FlyRandomlyGoal(this));
    }

    @Nullable
    @Override
    public UUID getOwnerUuid() {
        return owner.getUuid();
    }

    @Override
    public EntityView method_48926() {
        return this.getWorld();
    }

    @Override
    public @Nullable LivingEntity getOwner() {
        return this.owner;
    }
}