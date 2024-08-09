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
        // Changed to only remove the effects that were added, to avoid breaking other mods.
        player.removeStatusEffect(StatusEffects.BLINDNESS);
        player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        player.removeStatusEffect(StatusEffects.HUNGER);
        player.removeStatusEffect(StatusEffects.BAD_OMEN);
        player.removeStatusEffect(StatusEffects.SPEED);
        player.removeStatusEffect(StatusEffects.STRENGTH);
        player.removeStatusEffect(StatusEffects.ABSORPTION);
        super.onUnequip(world, player);
    }
}