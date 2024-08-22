package org.hyrulecraft.dungeon_utils.util;

public interface IStaminaHolder {

    default void setStamina(Float amount) {}

    default void setMaxStamina(Float amount) {}

    default void addStamina(Float amount) {}

    default void removeStamina(Float amount) {}

    default void resetStamina() {}

    default void resetMaxStamina() {}

    default Float getStamina() {
        return 0.0f;
    }

    default Float getMaxStamina() {
        return 0.0f;
    }
}