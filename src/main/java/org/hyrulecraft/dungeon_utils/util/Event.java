package org.hyrulecraft.dungeon_utils.util;

import org.jetbrains.annotations.NotNull;

public class Event {

    private final Type type;

    public Event(Type type) {
        this.type = type;
    }

    public Event copyFrom(@NotNull Event other) {
        return new Event(other.getType());
    }

    public Type getType() {
        return this.type;
    }

    public enum Type {
        PRE_INIT,
        POST_INIT
    }
}