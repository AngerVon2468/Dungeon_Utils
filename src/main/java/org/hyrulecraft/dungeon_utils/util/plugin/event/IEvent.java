package org.hyrulecraft.dungeon_utils.util.plugin.event;

public interface IEvent {

    default Event.Type getType() {
        return null;
    }
}