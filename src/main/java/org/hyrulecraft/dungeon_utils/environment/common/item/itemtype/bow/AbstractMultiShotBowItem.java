package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bow;

import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.sound.*;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public abstract class AbstractMultiShotBowItem extends AbstractBulletTimeBowItem {

    public AbstractMultiShotBowItem(Settings settings) {
        super(settings);
    }

    public abstract int arrowCount();

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            boolean stackCanBeDecremented = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack arrowStack = user.getProjectileType(stack);

            int charge = getMaxUseTime(stack) - remainingUseTicks;
            boolean unknownBooleanValue = stackCanBeDecremented && arrowStack.isOf(Items.ARROW);
            float arrowVelocity = getPullProgress(charge);
            if (arrowVelocity >= 0.1) {
                if (!world.isClient) {
                    int arrowCount = arrowCount(); // Number of arrows to shoot
                    for (int i = 0; i < arrowCount; i++) {
                        ArrowItem arrowItem = (ArrowItem)(arrowStack.getItem() instanceof ArrowItem ? arrowStack.getItem() : Items.ARROW);
                        PersistentProjectileEntity arrowEntity = arrowItem.createArrow(world, arrowStack, user);
                        arrowEntity.setVelocity(user, user.getPitch(), user.getYaw() + i * 10 - (arrowCount - 1) * 5, 0.0F, arrowVelocity * 3.0F, 1.0F);

                        arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;

                        if (arrowVelocity == 1.0F) {
                            arrowEntity.setCritical(true);
                        }

                        world.spawnEntity(arrowEntity);
                    }
                }
                world.playSound(null, user.getX(), user.getY(), user.getZ(), Sounds.ENTITY_ARROW_SHOOT, SoundCategories.PLAYERS, 1.0F, 1.0F / (user.getRandom().nextFloat() * 0.4F + 1.2F) + arrowVelocity * 0.5F);

                if (!unknownBooleanValue && !playerEntity.getAbilities().creativeMode) {
                    arrowStack.decrement(1);
                    if (arrowStack.isEmpty()) {
                        playerEntity.getInventory().removeOne(arrowStack);
                    }
                }

                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));

                stack.damage(1, user, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            }
        }
    }
}