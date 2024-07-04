package org.hyrulecraft.dungeon_utils.environment.common.entity;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;
import org.jetbrains.annotations.Nullable;

public class BombEntity extends ProjectileEntity {

    World world = this.getWorld();

    @Nullable Entity owner = this.getOwner();

    public BombEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
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
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.isOf(Blocks.NETHER_PORTAL)) {
                this.setInNetherPortal(blockPos);
                isInPortal = true;
            } else if (blockState.isOf(Blocks.END_GATEWAY)) {
                BlockEntity blockEntity = world.getBlockEntity(blockPos);
                if (blockEntity instanceof EndGatewayBlockEntity && EndGatewayBlockEntity.canTeleport(this)) {
                    EndGatewayBlockEntity.tryTeleportingEntity(this.getWorld(), blockPos, blockState, this, (EndGatewayBlockEntity) blockEntity);
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
        double y = this.getY();
        double z = this.getZ() + bombVec.z;
        if (owner != null) {

            Vec3d playerRotationVec = owner.getRotationVector();
            if (playerRotationVec.y > 90) {

                y = this.getY() - bombVec.y;
                this.updatePosition(x, y, z);

            } else if (playerRotationVec.y < 90) {

                y = this.getY() + bombVec.y;
                this.updatePosition(x, y, z);

            } else {

                this.updatePosition(x, y, z);

            }

        } else {

            DungeonUtils.LOGGER.info("Bomb Entity owner should not be null!");

        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        if (!world.isClient) {
            world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 1, World.ExplosionSourceType.MOB);
            this.discard();
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        if (!world.isClient) {
            world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 1, World.ExplosionSourceType.MOB);
            this.discard();
        }
    }
}