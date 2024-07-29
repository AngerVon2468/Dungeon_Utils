package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bow.lynel;

import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bow.AbstractMultiShotBowItem;

public class LynelBowFiveXItem extends AbstractMultiShotBowItem {

    public LynelBowFiveXItem(Settings settings) {
        super(settings);
    }

    @Override
    public int arrowCount() {
        return 5;
    }
}