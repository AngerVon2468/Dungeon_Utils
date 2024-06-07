package org.hyrulecraft.dungeon_utils.item.itemtype

import net.minecraft.block.*
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.sound.SoundCategory
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems
import org.hyrulecraft.dungeon_utils.sound.DungeonUtilsSounds

class MegatonHammerItem(toolMaterial: ToolMaterial?, attackDamage: Int, attackSpeed: Float, settings: Settings?) : SwordItem
    (toolMaterial, attackDamage, attackSpeed, settings) {

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {

        val stack = user.getStackInHand(hand)
        user.playSound(DungeonUtilsSounds.HAMMER_SWING, SoundCategory.PLAYERS, 1.0f, 1.0f)
        user.itemCooldownManager[DungeonUtilsItems.MEGATON_HAMMER] = 20
        return TypedActionResult.success(stack)

    }

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val world = context.world
        val blockpos = context.blockPos
        val blockState = world.getBlockState(blockpos)
        val player = context.player!!

        if (blockState.isOf(Blocks.STONE) && !world.isClient) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f)
            world.setBlockState(blockpos, Blocks.COBBLESTONE.defaultState)
            player.itemCooldownManager[DungeonUtilsItems.MEGATON_HAMMER] = 20
            return ActionResult.SUCCESS

        } else if (blockState.isOf(Blocks.STONE_BRICKS) && !world.isClient) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f)
            world.setBlockState(blockpos, Blocks.CRACKED_STONE_BRICKS.defaultState)
            player.itemCooldownManager[DungeonUtilsItems.MEGATON_HAMMER] = 20
            return ActionResult.SUCCESS

        } else if (blockState.isOf(Blocks.DEEPSLATE_BRICKS) && !world.isClient) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f)
            world.setBlockState(blockpos, Blocks.CRACKED_DEEPSLATE_BRICKS.defaultState)
            player.itemCooldownManager[DungeonUtilsItems.MEGATON_HAMMER] = 20
            return ActionResult.SUCCESS

        } else if (blockState.isOf(Blocks.DEEPSLATE_TILES) && !world.isClient) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f)
            world.setBlockState(blockpos, Blocks.CRACKED_DEEPSLATE_TILES.defaultState)
            player.itemCooldownManager[DungeonUtilsItems.MEGATON_HAMMER] = 20
            return ActionResult.SUCCESS

        } else if (blockState.isOf(Blocks.NETHER_BRICKS) && !world.isClient) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f)
            world.setBlockState(blockpos, Blocks.CRACKED_NETHER_BRICKS.defaultState)
            player.itemCooldownManager[DungeonUtilsItems.MEGATON_HAMMER] = 20
            return ActionResult.SUCCESS

        } else if (blockState.isOf(Blocks.POLISHED_BLACKSTONE_BRICKS) && !world.isClient) {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f)
            world.setBlockState(blockpos, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.defaultState)
            player.itemCooldownManager[DungeonUtilsItems.MEGATON_HAMMER] = 20
            return ActionResult.SUCCESS

        } else {

            player.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f)
            player.itemCooldownManager[DungeonUtilsItems.MEGATON_HAMMER] = 20
            return ActionResult.SUCCESS

        }
    }

    override fun useOnEntity(stack: ItemStack, user: PlayerEntity, entity: LivingEntity, hand: Hand): ActionResult {
        if (!user.itemCooldownManager.isCoolingDown(DungeonUtilsItems.MEGATON_HAMMER)) {

            entity.damage(user.damageSources.playerAttack(user), 8.0f)
            user.playSound(DungeonUtilsSounds.HAMMER_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f)
            user.itemCooldownManager[DungeonUtilsItems.MEGATON_HAMMER] = 20
            return ActionResult.SUCCESS

        } else {

            return ActionResult.FAIL

        }
    }

    override fun canMine(state: BlockState, world: World, pos: BlockPos, miner: PlayerEntity): Boolean {
        return false
    }
}