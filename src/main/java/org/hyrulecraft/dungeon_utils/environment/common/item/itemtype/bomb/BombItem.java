package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bomb;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.entity.entitytype.BombEntity;

import org.jetbrains.annotations.NotNull;

public class BombItem extends Item {

    public BombItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, Hand hand) {

        ItemStack stack = user.getMainHandStack();
        if (!world.isClient()) {

            BombEntity bombEntity = BombEntity.create(world, false);
            bombEntity.setOwner(user);
            bombEntity.setPosition(user.getX(), user.getY() + user.getEyeHeight(user.getPose()), user.getZ());
            Vec3d playerFacing = user.getRotationVector();
            bombEntity.setVelocity(playerFacing.x, playerFacing.y, playerFacing.z);
            bombEntity.setYaw(user.getHeadYaw());
            world.spawnEntity(bombEntity);
            if (!user.isCreative()) {

                stack.decrement(1);

            }

        }

        return TypedActionResult.success(stack, true);
    }
}