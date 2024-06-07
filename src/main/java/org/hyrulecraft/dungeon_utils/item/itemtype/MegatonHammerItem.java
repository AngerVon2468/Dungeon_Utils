package org.hyrulecraft.dungeon_utils.item.itemtype;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.sound.DungeonUtilsSounds;

import org.jetbrains.annotations.NotNull;

public class MegatonHammerItem extends SwordItem {

    public MegatonHammerItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, @NotNull PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        user.playSound(DungeonUtilsSounds.HAMMER_SWING, SoundCategory.PLAYERS, 1.0f, 1.0f);
        user.getItemCooldownManager().set((DungeonUtilsItems.MEGATON_HAMMER), 20);

        return TypedActionResult.success(stack);
    }

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockpos);
        PlayerEntity player = context.getPlayer();
        assert player != null;

        if (blockState.isOf(Blocks.STONE) && !world.isClient) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState());
            player.getItemCooldownManager().set((DungeonUtilsItems.MEGATON_HAMMER), 20);

            return ActionResult.SUCCESS;

        } else if (blockState.isOf(Blocks.STONE_BRICKS) && !world.isClient) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(blockpos, Blocks.CRACKED_STONE_BRICKS.getDefaultState());
            player.getItemCooldownManager().set((DungeonUtilsItems.MEGATON_HAMMER), 20);

            return ActionResult.SUCCESS;

        } else if (blockState.isOf(Blocks.DEEPSLATE_BRICKS) && !world.isClient) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(blockpos, Blocks.CRACKED_DEEPSLATE_BRICKS.getDefaultState());
            player.getItemCooldownManager().set((DungeonUtilsItems.MEGATON_HAMMER), 20);

            return ActionResult.SUCCESS;

        } else if (blockState.isOf(Blocks.DEEPSLATE_TILES) && !world.isClient) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(blockpos, Blocks.CRACKED_DEEPSLATE_TILES.getDefaultState());
            player.getItemCooldownManager().set((DungeonUtilsItems.MEGATON_HAMMER), 20);

            return ActionResult.SUCCESS;

        } else if (blockState.isOf(Blocks.NETHER_BRICKS) && !world.isClient) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(blockpos, Blocks.CRACKED_NETHER_BRICKS.getDefaultState());
            player.getItemCooldownManager().set((DungeonUtilsItems.MEGATON_HAMMER), 20);

            return ActionResult.SUCCESS;

        } else if (blockState.isOf(Blocks.POLISHED_BLACKSTONE_BRICKS) && !world.isClient) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.setBlockState(blockpos, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            player.getItemCooldownManager().set((DungeonUtilsItems.MEGATON_HAMMER), 20);

            return ActionResult.SUCCESS;

        } else {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            player.getItemCooldownManager().set((DungeonUtilsItems.MEGATON_HAMMER), 20);

            return ActionResult.SUCCESS;

        }
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, @NotNull PlayerEntity user, @NotNull LivingEntity entity, Hand hand) {
        entity.damage(user.getDamageSources().playerAttack(user), 8.0F);
        user.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
        user.getItemCooldownManager().set((DungeonUtilsItems.MEGATON_HAMMER), 20);
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return false;
    }
}