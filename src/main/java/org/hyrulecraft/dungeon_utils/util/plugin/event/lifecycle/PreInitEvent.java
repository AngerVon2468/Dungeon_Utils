package org.hyrulecraft.dungeon_utils.util.plugin.event.lifecycle;

import org.hyrulecraft.dungeon_utils.util.plugin.event.Event;

public class PreInitEvent extends Event {

    @Override
    public Type getType() {
        return Type.PRE_INIT;
    }
}