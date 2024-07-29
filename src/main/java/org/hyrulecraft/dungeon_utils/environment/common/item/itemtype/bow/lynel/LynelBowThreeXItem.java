package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bow.lynel;

import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bow.AbstractMultiShotBowItem;

public class LynelBowThreeXItem extends AbstractMultiShotBowItem {

    public LynelBowThreeXItem(Settings settings) {
        super(settings);
    }

    @Override
    public int arrowCount() {
        return 3;
    }
}