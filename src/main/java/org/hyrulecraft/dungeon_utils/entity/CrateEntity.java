package org.hyrulecraft.dungeon_utils.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class CrateEntity extends Entity {

    public CrateEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public void tick() {
        super.tick();

        PlayerEntity user = this.getWorld().getClosestPlayer(this.getX(), this.getY(), this.getZ(), 0.5, false);

        if (user != null && user.isSneaking()) {

            if (user.getMovementDirection() == Direction.NORTH) {

                this.setPos(this.getX(), this.getY(), this.getZ() - 0.5);

            }
            if (user.getMovementDirection() == Direction.SOUTH) {

                this.setPos(this.getX(), this.getY(), this.getZ() + 0.5);

            }
            if (user.getMovementDirection() == Direction.EAST) {

                this.setPos(this.getX() + 0.5, this.getY(), this.getZ());

            }
            if (user.getMovementDirection() == Direction.WEST) {

                this.setPos(this.getX() - 0.5, this.getY(), this.getZ());

            }

        }

    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return true;
    }
}