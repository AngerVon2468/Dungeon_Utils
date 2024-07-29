package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.champion.weapon.revali;

import org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bow.AbstractMultiShotBowItem;

public class GreatEagleBowItem extends AbstractMultiShotBowItem {

    public GreatEagleBowItem(Settings settings) {
        super(settings);
    }

    @Override
    public int arrowCount() {
        return 3;
    }
}