package org.hyrulecraft.dungeon_utils.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.tags.DungeonUtilsTags;

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

        World world = this.getWorld();

        PlayerEntity user = world.getClosestPlayer(this.getX(), this.getY(), this.getZ(), 1, false);
        if (user != null && user.isSneaking()) {

            BlockState stateNorth = world.getBlockState(this.getBlockPos().offset(Direction.NORTH, 1));
            BlockState stateSouth = world.getBlockState(this.getBlockPos().offset(Direction.SOUTH, 1));
            BlockState stateEast = world.getBlockState(this.getBlockPos().offset(Direction.EAST, 1));
            BlockState stateWest = world.getBlockState(this.getBlockPos().offset(Direction.WEST, 1));

            if (user.getMovementDirection() == Direction.NORTH && stateNorth.isIn(DungeonUtilsTags.Blocks.ACCEPTABLE_CRATE_BLOCK)) {

                this.setPos(this.getX(), this.getY(), this.getZ() - 1);

            }
            if (user.getMovementDirection() == Direction.SOUTH && stateSouth.isIn(DungeonUtilsTags.Blocks.ACCEPTABLE_CRATE_BLOCK)) {

                this.setPos(this.getX(), this.getY(), this.getZ() + 1);

            }
            if (user.getMovementDirection() == Direction.EAST && stateEast.isIn(DungeonUtilsTags.Blocks.ACCEPTABLE_CRATE_BLOCK)) {

                this.setPos(this.getX() + 1, this.getY(), this.getZ());

            }
            if (user.getMovementDirection() == Direction.WEST && stateWest.isIn(DungeonUtilsTags.Blocks.ACCEPTABLE_CRATE_BLOCK)) {

                this.setPos(this.getX() - 1, this.getY(), this.getZ());

            }

        }

        BlockState stateDown = world.getBlockState(this.getBlockPos().offset(Direction.DOWN, 1));
        if (stateDown.isIn(DungeonUtilsTags.Blocks.ACCEPTABLE_CRATE_BLOCK)) {

            this.setPos(this.getX(), this.getY() - 1, this.getZ());

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