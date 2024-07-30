package org.hyrulecraft.dungeon_utils.environment.common.entity.entitytype;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.BombFlowerBlock;
import org.hyrulecraft.dungeon_utils.environment.common.entity.DungeonUtilsEntities;
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;

import org.jetbrains.annotations.*;

public class BombEntity extends ProjectileEntity {

    World world = this.getWorld();

    public boolean isFromBombFlower;

    // Use the create method instead of the constructor generated in DungeonUtilsEntities to get a bomb entity instance.
    public BombEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Contract("_, _ -> new")
    public static @NotNull BombEntity create(World world, boolean isFromBombFlower) {
        BombEntity bombEntity = new BombEntity(DungeonUtilsEntities.BOMB, world);
        bombEntity.isFromBombFlower = isFromBombFlower;
        return bombEntity;
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("isFromBombFlower", this.isFromBombFlower);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.isFromBombFlower = nbt.getBoolean("isFromBombFlower");
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    public void tick() {
        super.tick();

        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);

        boolean isInPortal = false;
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = ((BlockHitResult) hitResult).getBlockPos();
            BlockState blockState = this.world.getBlockState(blockPos);
            if (blockState.isOf(Blocks.NETHER_PORTAL)) {
                this.setInNetherPortal(blockPos);
                isInPortal = true;
            } else if (blockState.isOf(Blocks.END_GATEWAY)) {
                BlockEntity blockEntity = this.world.getBlockEntity(blockPos);
                if (blockEntity instanceof EndGatewayBlockEntity endGatewayBlockEntity && EndGatewayBlockEntity.canTeleport(this)) {
                    EndGatewayBlockEntity.tryTeleportingEntity(this.world, blockPos, blockState, this, endGatewayBlockEntity);
                }

                isInPortal = true;
            }
        }
        if (hitResult.getType() != HitResult.Type.MISS && !isInPortal) {
            this.onCollision(hitResult);
        }
        this.checkBlockCollision();

        Vec3d bombVec = this.getVelocity();
        double x = this.getX() + bombVec.x;
        double y = this.getY() + bombVec.y;
        double z = this.getZ() + bombVec.z;
        this.updatePosition(x, y, z);
        if (!this.hasNoGravity() && !this.isFromBombFlower) {
            this.setVelocity(this.getVelocity().add(0.0f, -0.08f, 0.0f));
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient() && !this.isFromBombFlower) {
            this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 1, World.ExplosionSourceType.MOB);
            this.discard();
        } else {
            this.setVelocity(Vec3d.ZERO);
        }
    }

    @Override
    public ActionResult interact(@NotNull PlayerEntity playerEntity, Hand hand) {
        if (playerEntity.isSneaking() && !this.world.isClient() && this.isFromBombFlower) {
            BlockState state = this.world.getBlockState(this.getBlockPos());
            if (state.getBlock() instanceof BombFlowerBlock block && block.blockEntity != null) {
                block.blockEntity.beforeGrown = true;
                block.blockEntity.growth = 0;
            }
            this.discard();
            playerEntity.giveItemStack(DungeonUtilsItems.BOMB.getDefaultStack());
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean canHit() {
        return true;
    }
}