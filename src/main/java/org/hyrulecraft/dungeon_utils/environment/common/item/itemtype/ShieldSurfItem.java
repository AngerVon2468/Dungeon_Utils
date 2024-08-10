package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

public class ShieldSurfItem extends FabricShieldItem {

    public ShieldSurfItem(Settings settings, int coolDownTicks, int enchantability, Item... repairItems) {
        super(settings, coolDownTicks, enchantability, repairItems);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, @NotNull PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            this.startShieldSurfing(user, world);
        }
        super.use(world, user, hand);
        return super.use(world, user, hand);
    }

    public void startShieldSurfing(@NotNull PlayerEntity user, @NotNull World world) {
        user.setSprinting(true);

        BlockPos userPos = user.getBlockPos();
        BlockPos blockBelowPos = userPos.down();
        BlockState blockBelowState = world.getBlockState(blockBelowPos);
        user.setVelocity(user.getRotationVector().multiply(1.0, 0.0, 1.0));
        if (blockBelowState.isAir()) {
            user.setVelocity(user.getVelocity().add(0, -0.1, 0));
        }

    }
}