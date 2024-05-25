package org.hyrulecraft.dungeon_utils.item.itemtype;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.item.ModItems;
import org.hyrulecraft.dungeon_utils.sound.SoundInit;

import org.jetbrains.annotations.NotNull;

public class MegatonHammerItem extends SwordItem {

    public MegatonHammerItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, @NotNull PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        user.playSound(SoundInit.getHAMMER_SWING(), SoundCategory.PLAYERS, 1.0f, 1.0f);
        user.getItemCooldownManager().set((ModItems.MEGATON_HAMMER), 20);

        return TypedActionResult.success(stack);
    }

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {
        assert context.getPlayer() != null;

        context.getPlayer().playSound(SoundInit.getHAMMER_HIT(), SoundCategory.PLAYERS, 1.0f, 1.0f);
        context.getPlayer().getItemCooldownManager().set((ModItems.MEGATON_HAMMER), 20);

        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, @NotNull PlayerEntity user, @NotNull LivingEntity entity, Hand hand) {
        entity.damage(user.getDamageSources().playerAttack(user), 8.0F);
        user.playSound(SoundInit.getHAMMER_HIT(), SoundCategory.PLAYERS, 1.0f, 1.0f);
        user.getItemCooldownManager().set((ModItems.MEGATON_HAMMER), 20);
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return false;
    }
}