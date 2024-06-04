package org.hyrulecraft.dungeon_utils.item.itemtype.clothing.glove;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;

import org.jetbrains.annotations.NotNull;

// TODO: Rework literally all of this.
public class MogmaMittsItem extends Item {

    public MogmaMittsItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        assert player != null;
        ItemStack stack = player.getStackInHand(player.getActiveHand());

        if (stack.isOf(DungeonUtilsItems.MOGMA_MITTS)) {

            player.setNoGravity(true);
            player.noClip = true;
            player.setOnGround(false);
            player.groundCollision = false;
            player.setPose(EntityPose.SWIMMING);

        } else {

            player.setNoGravity(false);

        }

        return ActionResult.CONSUME;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {
            ItemStack stack2 = player.getStackInHand(player.getActiveHand());
            BlockState blockState = world.getBlockState(player.getBlockPos());

            if (stack2.isOf(DungeonUtilsItems.MOGMA_MITTS)) {

                player.noClip = true;
                player.setOnGround(false);
                player.groundCollision = false;
                player.setPose(EntityPose.SWIMMING);
                if (blockState.isOf(Blocks.AIR)) {
                    player.addVelocity(0, -0.1, 0);
                }
                isBeingUsed(player);

            } else {

                player.setNoGravity(false);

            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity player, @NotNull Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        return TypedActionResult.consume(stack);
    }

    public void isBeingUsed(@NotNull PlayerEntity player) {
        ItemStack stack = player.getStackInHand(player.getActiveHand());

        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("dungeon_utils.mogma_mitts.is_being_used", "is_being_used");

        stack.setNbt(nbtData);
    }
}