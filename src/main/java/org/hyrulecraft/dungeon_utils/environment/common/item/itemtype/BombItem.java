package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.entity.DungeonUtilsEntities;
import org.hyrulecraft.dungeon_utils.environment.common.entity.entity_type.BombEntity;

import org.jetbrains.annotations.NotNull;

public class BombItem extends Item {

    public BombItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, @NotNull PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        BombEntity bombEntity = DungeonUtilsEntities.BOMB.create(world);
        bombEntity.setOwner(user); // What do you mean this doesn't even work???
        bombEntity.setPosition(user.getX(), user.getY() + user.getEyeHeight(user.getPose()), user.getZ());
        Vec3d playerFacing = user.getRotationVector();
        bombEntity.setVelocity(playerFacing.x, playerFacing.y, playerFacing.z);
        bombEntity.setYaw(user.getHeadYaw());
        world.spawnEntity(bombEntity);

        return TypedActionResult.success(stack, true);
    }
}