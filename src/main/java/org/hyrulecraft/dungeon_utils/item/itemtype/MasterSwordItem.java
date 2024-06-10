package org.hyrulecraft.dungeon_utils.item.itemtype;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.entity.*;

import org.jetbrains.annotations.NotNull;

public class MasterSwordItem extends SwordItem {

    public MasterSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, @NotNull Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (user.isSneaking()) {

            BeamEntity beamEntity = DungeonUtilsEntities.BEAM.create(world);
            beamEntity.setOwner(user);
            beamEntity.setPosition(user.getX(), user.getY() + user.getEyeHeight(user.getPose()), user.getZ());
            Vec3d vec3d = user.getRotationVec(1.0f);
            beamEntity.setVelocity(vec3d.x, vec3d.y, vec3d.z, 0.5f, 0.0f);
            beamEntity.setYaw(user.getHeadYaw());
            world.spawnEntity(beamEntity);

        }

        return TypedActionResult.fail(stack);
    }
}