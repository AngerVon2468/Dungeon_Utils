package org.hyrulecraft.dungeon_utils.item.itemtype.bow;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.tags.DungeonUtilsTags;

public abstract class AbstractBulletTimeBowItem extends BowItem {

    public AbstractBulletTimeBowItem(Settings settings) {
        super(settings);
    }

    // This is broken, fix at some point.
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {

            BlockState airPos = world.getBlockState(player.getBlockPos().offset(Direction.DOWN, 1));
            Vec3d playerVec = player.getVelocity();
            if (airPos.isOf(Blocks.AIR) && player.getEquippedStack(EquipmentSlot.MAINHAND).isIn(DungeonUtilsTags.Items.BOW)) {

                player.setNoGravity(true);
                player.addVelocity(playerVec.x / 5, playerVec.y / 200, playerVec.z / 5);

            } else {

                player.setNoGravity(false);

            }

        }
    }
}