package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bomb;

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

public abstract class AbstractBombBagItem extends Item {

    public AbstractBombBagItem(Settings settings) {
        super(settings);
    }

    public abstract int bombBagLimit();

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, @NotNull Hand hand) {

        ItemStack stack = user.getMainHandStack();
        if (InventoryUtil.checkPlayerInventoryForItem(user, DungeonUtilsItems.BOMB) && !user.isSneaking()) {

            ItemStack rupeeStack = user.getInventory().getStack(InventoryUtil.getItemStackSlot(user, DungeonUtilsItems.BOMB));
            int rupeesInStack = rupeeStack.getCount();
            if (stack.getNbt() != null && stack.getNbt().contains("dungeon_utils.bomb.amount")) {

                int rupeesInWallet = stack.getNbt().getInt("dungeon_utils.bomb.amount");
                if (rupeesInWallet >= this.bombBagLimit()) {

                    return TypedActionResult.fail(stack);

                } else if (rupeesInStack + rupeesInWallet > this.bombBagLimit()) {

                    if (rupeesInStack > rupeesInWallet) {

                        int firstAmount = (rupeesInWallet + rupeesInStack) - this.bombBagLimit();
                        int secondAmount = (rupeesInWallet + rupeesInStack) - rupeesInStack;
                        int removeAmount = secondAmount - firstAmount;
                        rupeeStack.decrement(removeAmount);
                        int addAmount = this.bombBagLimit();
                        this.setAmount(user, addAmount);
                        return TypedActionResult.consume(stack);

                    } else if (rupeesInWallet > rupeesInStack) {

                        int removeAmount = this.bombBagLimit() - rupeesInWallet;
                        rupeeStack.decrement(removeAmount);
                        int addAmount = this.bombBagLimit();
                        this.setAmount(user, addAmount);
                        return TypedActionResult.consume(stack);

                    } else {

                        int removeAmount = this.bombBagLimit() - rupeesInWallet;
                        rupeeStack.decrement(removeAmount);
                        int addAmount = this.bombBagLimit();
                        this.setAmount(user, addAmount);
                        return TypedActionResult.consume(stack);

                    }

                } else {

                    rupeeStack.decrement(rupeesInStack);
                    int addAmount = rupeesInStack + rupeesInWallet;
                    this.setAmount(user, addAmount);
                    return TypedActionResult.consume(stack);

                }

            } else {

                rupeeStack.decrement(rupeesInStack);
                this.setAmount(user, rupeesInStack);
                return TypedActionResult.consume(stack);

            }

        } else if (stack.getNbt() != null && stack.getNbt().contains("dungeon_utils.bomb.amount") && stack.getNbt().getInt("dungeon_utils.bomb.amount") > 0 && user.isSneaking()) {

            this.setAmount(user, stack.getNbt().getInt("dungeon_utils.bomb.amount") - 1);
            user.getInventory().insertStack(new ItemStack(DungeonUtilsItems.BOMB));
            return TypedActionResult.consume(stack);

        } else {

            return TypedActionResult.fail(stack);

        }

    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext options) {
        if (MavityLibConfig.showTooltips) {

            tooltip.add(Text.translatable("tooltip.dungeon_utils.bomb_amount"));
            if (!stack.hasNbt()) {

                tooltip.add(Text.literal("0"));

            } else if (stack.hasNbt() && stack.getNbt() != null && stack.getNbt().contains("dungeon_utils.bomb.amount")) {

                int amount = stack.getNbt().getInt("dungeon_utils.bomb.amount");
                String amountAsString = Integer.toString(amount);
                String limitAsString = Integer.toString(bombBagLimit());

                tooltip.add(Text.literal(amountAsString + " out of " + limitAsString));

            }

            super.appendTooltip(stack, world, tooltip, options);
        }
    }

    public void setAmount(@NotNull PlayerEntity user, int newAmount) {
        ItemStack stack = user.getMainHandStack();

        NbtCompound nbtData = new NbtCompound();
        nbtData.putInt("dungeon_utils.bomb.amount", newAmount);

        stack.setNbt(nbtData);
    }
}