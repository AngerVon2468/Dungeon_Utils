package org.hyrulecraft.dungeon_utils.util.plugin.event;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;

public abstract class Event implements IEvent {

    public Event() {
        if (this.getType() == null) {
            throw new IllegalCallerException("Event base class cannot be instantiated (You've either initialized Event, or provided a broken implementation of getType())");
        }
        String className = this.getClass().getSimpleName();
        if (!className.endsWith("Event")) {
            DungeonUtils.LOGGER.error("Event classes should end with Item and {} doesn't.", className);
        }
    }

    public enum Type {
        PRE_INIT,
        POST_INIT,
    }
}