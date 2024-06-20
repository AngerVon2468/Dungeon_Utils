package org.hyrulecraft.dungeon_utils.item.itemtype;

import dev.emi.trinkets.api.*;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.DungeonUtils;
import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;
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
            BlockState blockState = world.getBlockState(user.getBlockPos().offset(Direction.DOWN, 1));
            if (blockState.isOf(Blocks.AIR) && handStack.isOf(DungeonUtilsItems.PARAGLIDER)) {

                Vec3d playerPos = user.getBlockPos().toCenterPos();
                Vec3d playerFacingPos = user.getBlockPos().offset(user.getHorizontalFacing(), 1).toCenterPos();
                if (user.getHorizontalFacing() == Direction.NORTH && DirectionCheckUtil.facingNorth(playerPos.x, playerFacingPos.x, playerPos.z, playerFacingPos.z)) {

                    user.addVelocity(0, 0.007,-0.01);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 10));

                }
                if (user.getHorizontalFacing() == Direction.SOUTH && DirectionCheckUtil.facingSouth(playerPos.x, playerFacingPos.x, playerPos.z, playerFacingPos.z)) {

                    user.addVelocity(0, 0.007,0.01);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 10));

                }
                if (user.getHorizontalFacing() == Direction.EAST && DirectionCheckUtil.facingEast(playerPos.x, playerFacingPos.x, playerPos.z, playerFacingPos.z)) {

                    user.addVelocity(0.01, 0.007,0);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 10));

                }
                if (user.getHorizontalFacing() == Direction.WEST && DirectionCheckUtil.facingWest(playerPos.x, playerFacingPos.x, playerPos.z, playerFacingPos.z)) {

                    user.addVelocity(-0.01, 0.007,0);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 10));

                }

            } else {

                //

            }

        }

    }
}