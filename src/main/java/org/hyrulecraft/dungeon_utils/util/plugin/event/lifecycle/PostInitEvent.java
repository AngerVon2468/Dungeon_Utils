package org.hyrulecraft.dungeon_utils.util.plugin.event.lifecycle;

import org.hyrulecraft.dungeon_utils.util.plugin.event.Event;

public class PostInitEvent extends Event {

    @Override
    public Type getType() {
        return Type.POST_INIT;
    }
}