package org.hyrulecraft.dungeon_utils.environment.common.event;

import net.fabricmc.fabric.api.event.*;

import net.minecraft.entity.player.PlayerEntity;

import org.hyrulecraft.dungeon_utils.environment.common.entity.entity_type.MasterSwordBeamEntity;

public class MasterSwordBeamCallbacks {

    /**
     * Callback for editing the master sword beam from The Master Sword. <br>
     * Runs after the master sword beam has had its properties set, but just before it is summoned into the world.
     */
    public static Event<BeforeSummon> BEFORE_SUMMON = EventFactory.createArrayBacked(BeforeSummon.class,
            callbacks -> (user, masterSwordBeamEntity) -> {
                for (BeforeSummon callback : callbacks) {
                    callback.beforeSummon(user, masterSwordBeamEntity);
                }
            }
    );

    /**
     * Callback for editing the master sword beam from The Master Sword. <br>
     * Runs before the master sword beam has had its properties set. <br>
     * I think it's broken :'(
     */
    public static Event<BeforeApplyProperties> BEFORE_APPLY_PROPERTIES = EventFactory.createArrayBacked(BeforeApplyProperties.class,
            callbacks -> (user, masterSwordBeamEntity) -> {
                for (BeforeApplyProperties callback : callbacks) {
                    if (!callback.beforeApplyProperties(user, masterSwordBeamEntity)) {
                        return false;
                    }
                }

                return true;
            }
    );

    @FunctionalInterface
    public interface BeforeSummon {

        void beforeSummon(PlayerEntity user, MasterSwordBeamEntity masterSwordBeamEntity);
    }

    @FunctionalInterface
    public interface BeforeApplyProperties {

        boolean beforeApplyProperties(PlayerEntity user, MasterSwordBeamEntity masterSwordBeamEntity);
    }
}