package org.hyrulecraft.dungeon_utils.item.itemtype;

import dev.emi.trinkets.api.*;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.tags.DungeonUtilsTags;
import org.hyrulecraft.dungeon_utils.util.DirectionCheckUtil;

public class ParagliderTrinketItem extends TrinketItem {

    public ParagliderTrinketItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (!(entity instanceof PlayerEntity user)) {

            // Why are we still here... just to suffer?

        } else {

            ItemStack handStack = user.getEquippedStack(EquipmentSlot.MAINHAND);
            BlockState blockState = world.getBlockState(user.getBlockPos().offset(Direction.DOWN, 1));
            if (blockState.isOf(Blocks.AIR) && handStack.isOf(DungeonUtilsItems.PARAGLIDER)) {

                Vec3d playerPos = user.getBlockPos().toCenterPos();
                Vec3d playerFacingPos = user.getBlockPos().offset(user.getHorizontalFacing(), 1).toCenterPos();
                if (user.getHorizontalFacing() == Direction.NORTH && blockState.isIn(DungeonUtilsTags.Blocks.HOOKSHOT) && DirectionCheckUtil.facingNorth(playerPos.x, playerFacingPos.x, playerPos.z, playerFacingPos.z)) {

                    user.addVelocity(0, 0.14,-0.2);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 255));

                }
                if (user.getHorizontalFacing() == Direction.SOUTH && blockState.isIn(DungeonUtilsTags.Blocks.HOOKSHOT) && DirectionCheckUtil.facingSouth(playerPos.x, playerFacingPos.x, playerPos.z, playerFacingPos.z)) {

                    user.addVelocity(0, 0.14,0.2);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 255));

                }
                if (user.getHorizontalFacing() == Direction.EAST && blockState.isIn(DungeonUtilsTags.Blocks.HOOKSHOT) && DirectionCheckUtil.facingEast(playerPos.x, playerFacingPos.x, playerPos.z, playerFacingPos.z)) {

                    user.addVelocity(0.2, 0.14,0);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 255));

                }
                if (user.getHorizontalFacing() == Direction.WEST && blockState.isIn(DungeonUtilsTags.Blocks.HOOKSHOT) && DirectionCheckUtil.facingWest(playerPos.x, playerFacingPos.x, playerPos.z, playerFacingPos.z)) {

                    user.addVelocity(-0.2, 0.14,0);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 255));

                }

            } else {

                //

            }

        }

    }
}