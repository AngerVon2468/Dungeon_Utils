package org.hyrulecraft.dungeon_utils.util.plugin.event;

public abstract class Event implements IEvent {

    public Event() {
        if (this.getType() == null) {
            throw new IllegalCallerException("Event base class cannot be instantiated (You've either initialized Event, or provided a broken implementation of getType())");
        }
    }

    public enum Type {
        PRE_INIT,
        POST_INIT,
    }
}