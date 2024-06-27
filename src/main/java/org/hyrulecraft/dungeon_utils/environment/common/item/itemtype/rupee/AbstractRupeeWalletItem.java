package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.rupee;

import net.fabricmc.api.*;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.util.InventoryUtil;

import org.jetbrains.annotations.*;

import wiiu.mavity.mavity_lib.config.MavityLibConfig;

import java.util.List;

public abstract class AbstractRupeeWalletItem extends Item {

    public AbstractRupeeWalletItem(Settings settings) {
        super(settings);
    }

    public int totalRupees() {
        return (Integer) null;
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, @NotNull Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // TODO: Fix this.

        if (InventoryUtil.hasPlayerStackInInventory(user, DungeonUtilsItems.GREEN_RUPEE)) {

            ItemStack stack2 = user.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(user, DungeonUtilsItems.GREEN_RUPEE));
            int stack2Count = stack2.getCount();

            return TypedActionResult.consume(stack);

        } else {

            return TypedActionResult.fail(stack);

        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext options) {
        if (MavityLibConfig.showTooltips) {

            tooltip.add(Text.translatable("tooltip.dungeon_utils.rupee_amount"));
            if (!stack.hasNbt()) {

                tooltip.add(Text.literal("0"));

            } else if (stack.hasNbt() && stack.getNbt() != null && stack.getNbt().contains("dungeon_utils.rupee.amount")) {

                int amount = stack.getNbt().getInt("dungeon_utils.rupee.amount");
                String amountAsString = Integer.toString(amount);
                String limitAsString = Integer.toString(totalRupees());

                tooltip.add(Text.literal(amountAsString + " out of " + limitAsString));

            }

            super.appendTooltip(stack, world, tooltip, options);
        }
    }

    public void addAmount(@NotNull PlayerEntity player, int i) {
        ItemStack stack = player.getStackInHand(player.getActiveHand());

        NbtCompound nbtData = new NbtCompound();
        nbtData.putInt("dungeon_utils.rupee.amount", i);

        stack.setNbt(nbtData);
    }
}