package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bottle;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.entity.DungeonUtilsEntities;
import org.hyrulecraft.dungeon_utils.environment.common.entity.entity_type.FairyEntity;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractFilledBottleItem extends Item {

    public AbstractFilledBottleItem(Settings settings) {
        super(settings);
    }

    abstract String getNbtKey();

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {

        World world = context.getWorld();
        if (!world.isClient()) {

            ItemStack stack = context.getStack();
            NbtCompound nbt = stack.getNbt();
            BlockPos pos = context.getBlockPos().offset(Direction.UP, 1);
            if (this instanceof FishBottleItem) {

                if (nbt.contains(this.getNbtKey())) {

                    String s = nbt.getString(this.getNbtKey());
                    if (s.contains(CodEntity.class.getSimpleName())) {

                        CodEntity cod = EntityType.COD.create(world);
                        cod.refreshPositionAfterTeleport(Vec3d.ofCenter(pos));
                        context.getWorld().spawnEntity(cod);

                    } else if (s.contains(PufferfishEntity.class.getSimpleName())) {

                        PufferfishEntity pufferfish = EntityType.PUFFERFISH.create(world);
                        pufferfish.refreshPositionAfterTeleport(Vec3d.ofCenter(pos));
                        context.getWorld().spawnEntity(pufferfish);

                    } else if (s.contains(SalmonEntity.class.getSimpleName())) {

                        SalmonEntity salmon = EntityType.SALMON.create(world);
                        salmon.refreshPositionAfterTeleport(Vec3d.ofCenter(pos));
                        context.getWorld().spawnEntity(salmon);

                    } else if (s.contains(TadpoleEntity.class.getSimpleName())) {

                        TadpoleEntity tadpole = EntityType.TADPOLE.create(world);
                        tadpole.refreshPositionAfterTeleport(Vec3d.ofCenter(pos));
                        context.getWorld().spawnEntity(tadpole);

                    } else if (s.contains(TropicalFishEntity.class.getSimpleName())) {

                        TropicalFishEntity tropicalFish = EntityType.TROPICAL_FISH.create(world);
                        tropicalFish.refreshPositionAfterTeleport(Vec3d.ofCenter(pos));
                        context.getWorld().spawnEntity(tropicalFish);

                    }

                }

            } else if (this instanceof FairyBottleItem) {

                if (nbt.contains(this.getNbtKey())) {

                    String s = nbt.getString(this.getNbtKey());
                    if (s.contains(FairyEntity.class.getSimpleName())) {

                        FairyEntity fairy = DungeonUtilsEntities.FAIRY.create(world);
                        fairy.refreshPositionAfterTeleport(Vec3d.ofCenter(pos));
                        context.getWorld().spawnEntity(fairy);

                    } else if (s.contains(AllayEntity.class.getSimpleName())) {

                        AllayEntity allay = EntityType.ALLAY.create(world);
                        allay.refreshPositionAfterTeleport(Vec3d.ofCenter(pos));
                        context.getWorld().spawnEntity(allay);

                    }

                }

            } else if (this instanceof BugBottleItem) {

                if (nbt.contains(this.getNbtKey())) {

                    String s = nbt.getString(this.getNbtKey());
                    if (s.contains(EndermiteEntity.class.getSimpleName())) {

                        EndermiteEntity endermite = EntityType.ENDERMITE.create(world);
                        endermite.refreshPositionAfterTeleport(Vec3d.ofCenter(pos));
                        context.getWorld().spawnEntity(endermite);

                    } else if (s.contains(SilverfishEntity.class.getSimpleName())) {

                        SilverfishEntity silverfish = EntityType.SILVERFISH.create(world);
                        silverfish.refreshPositionAfterTeleport(Vec3d.ofCenter(pos));
                        context.getWorld().spawnEntity(silverfish);

                    }

                }

            }

            stack.decrement(1);

        }

        return ActionResult.SUCCESS;
    }
}