package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.mask;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import virtuoel.pehkui.api.*;

public class GiantsMaskItem extends AbstractMaskItem {

    @Override
    Item getItem() {
        return this;
    }

    @Override
    public void onEquip(World world, PlayerEntity player) {
        ScaleData playerScale = ScaleTypes.BASE.getScaleData(player);
        ScaleData playerDefense = ScaleTypes.DEFENSE.getScaleData(player);
        ScaleData playerHealth = ScaleTypes.HEALTH.getScaleData(player);
        ScaleData playerDrops = ScaleTypes.DROPS.getScaleData(player);
        ScaleData playerSpeed = ScaleTypes.MOTION.getScaleData(player);
        ScaleData playerDamage = ScaleTypes.ATTACK.getScaleData(player);
        ScaleData playerReach = ScaleTypes.REACH.getScaleData(player);
        playerScale.setScale(playerScale.getBaseScale() * 4.0f);
        playerDefense.resetScale();
        playerHealth.resetScale();
        playerDrops.resetScale();
        playerSpeed.resetScale();
        playerSpeed.setScale(playerSpeed.getBaseScale() - 0.25f);
        playerDamage.resetScale();
        playerDamage.setScale(playerSpeed.getBaseScale() + 2.0f);
        playerReach.resetScale();
        playerReach.setScale(playerSpeed.getBaseScale() + 2.0f);
        super.onEquip(world, player);
    }

    @Override
    public void onUnequip(World world, PlayerEntity player) {
        ScaleData playerScale = ScaleTypes.BASE.getScaleData(player);
        ScaleData playerDefense = ScaleTypes.DEFENSE.getScaleData(player);
        ScaleData playerHealth = ScaleTypes.HEALTH.getScaleData(player);
        ScaleData playerDrops = ScaleTypes.DROPS.getScaleData(player);
        ScaleData playerSpeed = ScaleTypes.MOTION.getScaleData(player);
        ScaleData playerDamage = ScaleTypes.ATTACK.getScaleData(player);
        ScaleData playerReach = ScaleTypes.REACH.getScaleData(player);
        ScaleData playerJumpHeight = ScaleTypes.JUMP_HEIGHT.getScaleData(player);
        playerScale.resetScale();
        playerDefense.resetScale();
        playerHealth.resetScale();
        playerDrops.resetScale();
        playerSpeed.resetScale();
        playerSpeed.resetScale();
        playerDamage.resetScale();
        playerDamage.resetScale();
        playerReach.resetScale();
        playerReach.resetScale();
        playerJumpHeight.resetScale();
        super.onUnequip(world, player);
    }
}