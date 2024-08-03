package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.mask;

import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

public class MajorasMaskItem extends AbstractMaskItem {

    @Override
    public void equipTick(World world, @NotNull PlayerEntity player) {
        super.equipTick(world, player);
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20, 0, true, false, false, null, StatusEffects.BLINDNESS.getFactorCalculationDataSupplier()));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20, 0, true, false, false, null, StatusEffects.NIGHT_VISION.getFactorCalculationDataSupplier()));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 20, 19, true, false, false, null, StatusEffects.HUNGER.getFactorCalculationDataSupplier()));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.BAD_OMEN, 20, 0, true, false, false, null, StatusEffects.BAD_OMEN.getFactorCalculationDataSupplier()));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 5, true, false, false, null, StatusEffects.SPEED.getFactorCalculationDataSupplier()));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20, 2, true, false, false, null, StatusEffects.STRENGTH.getFactorCalculationDataSupplier()));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 20, 3, true, false, false, null, StatusEffects.HUNGER.getFactorCalculationDataSupplier()));
    }

    @Override
    public void onUnequip(World world, @NotNull PlayerEntity player) {
        player.clearStatusEffects();
        super.onUnequip(world, player);
    }
}