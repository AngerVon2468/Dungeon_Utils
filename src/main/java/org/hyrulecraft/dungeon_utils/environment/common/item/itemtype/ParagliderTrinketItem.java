package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype;

import dev.emi.trinkets.api.*;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;
import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.util.DirectionCheckUtil;

public class ParagliderTrinketItem extends TrinketItem {

    public ParagliderTrinketItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (!(entity instanceof PlayerEntity user)) {

            DungeonUtils.LOGGER.info("Just how... how did you even do that...");

        } else {

            ItemStack handStack = user.getEquippedStack(EquipmentSlot.MAINHAND);
            Vec3d playerVec3d = user.getVelocity();
            if (handStack.isOf(DungeonUtilsItems.PARAGLIDER) && user.isFallFlying()) {

                Vec3d playerPos = user.getBlockPos().toCenterPos();
                Vec3d playerFacingPos = user.getBlockPos().offset(user.getHorizontalFacing(), 1).toCenterPos();
                if (user.getHorizontalFacing() == Direction.NORTH && DirectionCheckUtil.facingNorth(playerPos.x, playerFacingPos.x, playerPos.z, playerFacingPos.z)) {

                    user.setVelocity(playerVec3d.x, 0.05, playerVec3d.z);

                }
                if (user.getHorizontalFacing() == Direction.SOUTH && DirectionCheckUtil.facingSouth(playerPos.x, playerFacingPos.x, playerPos.z, playerFacingPos.z)) {

                    user.setVelocity(playerVec3d.x, 0.05, playerVec3d.z);

                }
                if (user.getHorizontalFacing() == Direction.EAST && DirectionCheckUtil.facingEast(playerPos.x, playerFacingPos.x, playerPos.z, playerFacingPos.z)) {

                    user.setVelocity(playerVec3d.x, 0.05, playerVec3d.z);

                }
                if (user.getHorizontalFacing() == Direction.WEST && DirectionCheckUtil.facingWest(playerPos.x, playerFacingPos.x, playerPos.z, playerFacingPos.z)) {

                    user.setVelocity(playerVec3d.x, 0.05, playerVec3d.z);

                }

            }

        }

    }
}