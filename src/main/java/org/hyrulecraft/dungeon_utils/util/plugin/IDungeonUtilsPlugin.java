package org.hyrulecraft.dungeon_utils.util.plugin;

import org.hyrulecraft.dungeon_utils.util.plugin.event.Event;

public interface IDungeonUtilsPlugin {

    void init();

    void onEvent(Event event);
}