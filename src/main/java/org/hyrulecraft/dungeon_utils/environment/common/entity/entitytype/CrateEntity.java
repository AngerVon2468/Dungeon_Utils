package org.hyrulecraft.dungeon_utils.environment.common.entity.entitytype;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.tags.DungeonUtilsTags;
import org.hyrulecraft.dungeon_utils.util.DirectionCheckUtil;

public class CrateEntity extends Entity {

    public CrateEntity(EntityType<? extends Entity> type, World world) {
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
        BlockPos blockPos = this.getBlockPos();

        PlayerEntity user = world.getClosestPlayer(this.getX(), this.getY(), this.getZ(), 1, false);
        if (user != null && user.isSneaking()) {

            Vec3d playerPos = user.getBlockPos().toCenterPos();
            Vec3d cratePos = this.getBlockPos().toCenterPos();
            BlockState stateNorth = world.getBlockState(blockPos.offset(Direction.NORTH, 1));
            BlockState stateSouth = world.getBlockState(blockPos.offset(Direction.SOUTH, 1));
            BlockState stateEast = world.getBlockState(blockPos.offset(Direction.EAST, 1));
            BlockState stateWest = world.getBlockState(blockPos.offset(Direction.WEST, 1));
            if (user.getMovementDirection() == Direction.NORTH && stateNorth.isIn(DungeonUtilsTags.Blocks.ACCEPTABLE_CRATE_BLOCK) && DirectionCheckUtil.facingNorth(playerPos.x, cratePos.x, playerPos.z, cratePos.z)) {

                this.setPosition(this.getX(), this.getY(), this.getZ() - 1);
                this.setYaw(-180f);

            }
            if (user.getMovementDirection() == Direction.SOUTH && stateSouth.isIn(DungeonUtilsTags.Blocks.ACCEPTABLE_CRATE_BLOCK) && DirectionCheckUtil.facingSouth(playerPos.x, cratePos.x, playerPos.z, cratePos.z)) {

                this.setPosition(this.getX(), this.getY(), this.getZ() + 1);
                this.setYaw(0f);

            }
            if (user.getMovementDirection() == Direction.EAST && stateEast.isIn(DungeonUtilsTags.Blocks.ACCEPTABLE_CRATE_BLOCK) && DirectionCheckUtil.facingEast(playerPos.x, cratePos.x, playerPos.z, cratePos.z)) {

                this.setPosition(this.getX() + 1, this.getY(), this.getZ());
                this.setYaw(-90f);

            }
            if (user.getMovementDirection() == Direction.WEST && stateWest.isIn(DungeonUtilsTags.Blocks.ACCEPTABLE_CRATE_BLOCK) && DirectionCheckUtil.facingWest(playerPos.x, cratePos.x, playerPos.z, cratePos.z)) {

                this.setPosition(this.getX() - 1, this.getY(), this.getZ());
                this.setYaw(90f);

            }

        }

        BlockState stateDown = world.getBlockState(blockPos.offset(Direction.DOWN, 1));
        if (stateDown.isIn(DungeonUtilsTags.Blocks.ACCEPTABLE_CRATE_BLOCK)) {

            this.setPosition(this.getX(), this.getY() - 1, this.getZ());

        }

        BlockState state = this.getBlockStateAtPos();
        if (state.isIn(DungeonUtilsTags.Blocks.ACCEPTABLE_CRATE_BLOCK)) {

            world.setBlockState(blockPos, Blocks.AIR.getDefaultState());

        }

    }

    @Override
    public boolean canHit() {
        return !this.isRemoved();
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