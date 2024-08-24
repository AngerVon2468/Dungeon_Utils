package org.hyrulecraft.dungeon_utils.util;

public interface IStaminaHolder {

    default void setStamina(Float amount) {}

    default void setMaxStamina(Float amount) {}

    default void increaseStamina(Float amount) {}

    default void decreaseStamina(Float amount) {}

    default void increaseMaxStamina(Float amount) {}

    default void decreaseMaxStamina(Float amount) {}

    default void resetStamina() {}

    default void resetMaxStamina() {}

    default Float getStamina() {
        return 7.3f;
    }

    default Float getMaxStamina() {
        return 2.9f;
    }
}