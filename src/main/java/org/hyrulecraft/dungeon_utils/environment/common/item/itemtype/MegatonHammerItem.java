package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategories;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.environment.common.sound.DungeonUtilsSounds;

import org.jetbrains.annotations.NotNull;

public class MegatonHammerItem extends SwordItem {

    public MegatonHammerItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, @NotNull PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        user.playSound(DungeonUtilsSounds.HAMMER_SWING, SoundCategories.PLAYERS, 1.0f, 1.0f);
        user.getItemCooldownManager().set(DungeonUtilsItems.MEGATON_HAMMER, 20);
        return TypedActionResult.success(stack);
    }

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {

        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        PlayerEntity player = context.getPlayer();
        assert player != null;
        if (blockState.isOf(Blocks.STONE) && !world.isClient()) {
            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategories.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(blockPos, Blocks.COBBLESTONE.getDefaultState());
            player.getItemCooldownManager().set(DungeonUtilsItems.MEGATON_HAMMER, 20);
            return ActionResult.SUCCESS;
        } else if (blockState.isOf(Blocks.STONE_BRICKS) && !world.isClient()) {
            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategories.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(blockPos, Blocks.CRACKED_STONE_BRICKS.getDefaultState());
            player.getItemCooldownManager().set(DungeonUtilsItems.MEGATON_HAMMER, 20);
            return ActionResult.SUCCESS;
        } else if (blockState.isOf(Blocks.DEEPSLATE_BRICKS) && !world.isClient()) {
            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategories.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(blockPos, Blocks.CRACKED_DEEPSLATE_BRICKS.getDefaultState());
            player.getItemCooldownManager().set(DungeonUtilsItems.MEGATON_HAMMER, 20);
            return ActionResult.SUCCESS;
        } else if (blockState.isOf(Blocks.DEEPSLATE_TILES) && !world.isClient()) {
            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategories.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(blockPos, Blocks.CRACKED_DEEPSLATE_TILES.getDefaultState());
            player.getItemCooldownManager().set(DungeonUtilsItems.MEGATON_HAMMER, 20);
            return ActionResult.SUCCESS;
        } else if (blockState.isOf(Blocks.NETHER_BRICKS) && !world.isClient()) {
            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategories.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(blockPos, Blocks.CRACKED_NETHER_BRICKS.getDefaultState());
            player.getItemCooldownManager().set(DungeonUtilsItems.MEGATON_HAMMER, 20);
            return ActionResult.SUCCESS;
        } else if (blockState.isOf(Blocks.POLISHED_BLACKSTONE_BRICKS) && !world.isClient()) {
            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategories.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(blockPos, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            player.getItemCooldownManager().set(DungeonUtilsItems.MEGATON_HAMMER, 20);
            return ActionResult.SUCCESS;
        } else {
            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategories.PLAYERS, 1.0f, 1.0f);
            player.getItemCooldownManager().set(DungeonUtilsItems.MEGATON_HAMMER, 20);
            return ActionResult.SUCCESS;
        }
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, @NotNull PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getItemCooldownManager().isCoolingDown(DungeonUtilsItems.MEGATON_HAMMER)) {
            entity.damage(user.getDamageSources().playerAttack(user), 8.0f);
            user.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategories.PLAYERS, 1.0f, 1.0f);
            user.getItemCooldownManager().set(DungeonUtilsItems.MEGATON_HAMMER, 20);
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FAIL;
        }
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return false;
    }
}