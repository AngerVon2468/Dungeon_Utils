package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.entity.DungeonUtilsEntities;
import org.hyrulecraft.dungeon_utils.environment.common.entity.entitytype.MasterSwordBeamEntity;
import org.hyrulecraft.dungeon_utils.environment.common.event.MasterSwordBeamCallbacks;

import org.jetbrains.annotations.NotNull;

public class MasterSwordItem extends SwordItem {

    public MasterSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, @NotNull Hand hand) {

        ItemStack stack = user.getMainHandStack();
        if (user.isSneaking()) {

            MasterSwordBeamEntity masterSwordBeamEntity = DungeonUtilsEntities.MASTER_SWORD_BEAM.create(world);
            if (MasterSwordBeamCallbacks.BEFORE_APPLY_PROPERTIES.invoker().beforeApplyProperties(user, masterSwordBeamEntity)) {
                masterSwordBeamEntity.setOwner(user);
                masterSwordBeamEntity.setPosition(user.getX(), user.getY() + user.getEyeHeight(user.getPose()), user.getZ());
                Vec3d vec3d = user.getRotationVec(1.0f);
                masterSwordBeamEntity.setVelocity(vec3d.x, vec3d.y, vec3d.z, 0.5f, 0.0f);
                masterSwordBeamEntity.setYaw(user.getHeadYaw());
            }
            MasterSwordBeamCallbacks.BEFORE_SUMMON.invoker().beforeSummon(user, masterSwordBeamEntity);
            world.spawnEntity(masterSwordBeamEntity);

        }

        return TypedActionResult.fail(stack);
    }
}