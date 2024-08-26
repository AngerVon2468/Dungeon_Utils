package org.hyrulecraft.dungeon_utils.util;

import java.util.Objects;

public final class ObjectsButOnSteroids {

    public static <T> T requireFirstNonNullOrElse(T attempted, T other) {
        return attempted != null ? attempted : other;
    }

    public static <T> T requireBothNonNullOrElse(T attempted, T other) {
        return attempted != null ? attempted : Objects.requireNonNull(other, "Second object was null (" + other.getClass().getSimpleName() + ")");
    }
}