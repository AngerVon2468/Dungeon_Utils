package org.hyrulecraft.dungeon_utils.environment.common.entity.entity_type;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.mob.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import org.hyrulecraft.dungeon_utils.environment.common.entity.goal.FlyRandomlyGoal;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class FairyEntity extends FlyingEntity implements Tameable {

    @Nullable private final LivingEntity owner;

    public FairyEntity(EntityType<? extends FlyingEntity> type, World world) {
        super(type, world);
        this.owner = null;
        this.moveControl = new FairyMoveControl(this);
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
        this.goalSelector.add(4, new FlyRandomlyGoal(this));
    }

    @Nullable
    @Override
    public UUID getOwnerUuid() {
        return owner != null ? owner.getUuid() : null;
    }

    @Override
    public EntityView method_48926() {
        return this.getWorld();
    }

    @Override
    public @Nullable LivingEntity getOwner() {
        return this.owner;
    }

    static class FairyMoveControl extends MoveControl {
        private final FairyEntity fairy;
        private int collisionCheckCooldown;

        public FairyMoveControl(FairyEntity fairy) {
            super(fairy);
            this.fairy = fairy;
        }

        public void tick() {
            if (this.state == State.MOVE_TO) {
                if (this.collisionCheckCooldown-- <= 0) {
                    this.collisionCheckCooldown += this.fairy.getRandom().nextInt(5) + 2;
                    Vec3d vec3d = new Vec3d(this.targetX - this.fairy.getX(), this.targetY - this.fairy.getY(), this.targetZ - this.fairy.getZ());
                    double d = vec3d.length();
                    vec3d = vec3d.normalize();
                    float h = (float)(MathHelper.atan2(vec3d.z, vec3d.x) * 57.2957763671875) - 90.0F;
                    this.fairy.setYaw(this.wrapDegrees(this.fairy.getYaw(), h, 90.0F));
                    if (this.willCollide(vec3d, MathHelper.ceil(d))) {
                        this.fairy.setVelocity(this.fairy.getVelocity().add(vec3d.multiply(0.1)));
                    } else {
                        this.state = State.WAIT;
                    }
                }

            }
        }

        private boolean willCollide(Vec3d vec3d, int i) {
            Box box = this.fairy.getBoundingBox();

            for(int j = 1; j < i; ++j) {
                box = box.offset(vec3d);
                if (!this.fairy.getWorld().isSpaceEmpty(this.fairy, box)) {
                    return false;
                }
            }

            return true;
        }
    }
}