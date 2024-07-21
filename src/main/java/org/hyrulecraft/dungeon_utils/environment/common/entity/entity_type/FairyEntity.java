package org.hyrulecraft.dungeon_utils.environment.common.entity.entity_type;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.entity.goal.FlyRandomlyGoal;

public class FairyEntity extends FlyingEntity {

    public FairyEntity(EntityType<? extends FlyingEntity> type, World world) {
        super(type, world);
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
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new FlyRandomlyGoal(this));
    }
}