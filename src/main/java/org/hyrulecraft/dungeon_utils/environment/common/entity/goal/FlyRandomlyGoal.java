package org.hyrulecraft.dungeon_utils.environment.common.entity.goal;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.util.math.random.Random;

import java.util.EnumSet;

public class FlyRandomlyGoal extends Goal {

    private final FlyingEntity entity;

    public FlyRandomlyGoal(FlyingEntity entity) {
        this.entity = entity;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    public boolean canStart() {
        MoveControl moveControl = this.entity.getMoveControl();
        if (!moveControl.isMoving()) {
            return true;
        } else {
            double d = moveControl.getTargetX() - this.entity.getX();
            double e = moveControl.getTargetY() - this.entity.getY();
            double f = moveControl.getTargetZ() - this.entity.getZ();
            double g = d * d + e * e + f * f;
            return g < 1.0 || g > 3600.0;
        }
    }

    public void start() {
        Random random = this.entity.getRandom();
        double d = this.entity.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
        double e = this.entity.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
        double f = this.entity.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
        this.entity.getMoveControl().moveTo(d, e, f, 1.0);
    }
}