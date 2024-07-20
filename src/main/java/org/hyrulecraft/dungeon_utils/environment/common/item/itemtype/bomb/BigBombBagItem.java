package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bomb;

public class BigBombBagItem extends AbstractBombBagItem {

    public BigBombBagItem(Settings settings) {
        super(settings);
    }

    @Override
    public int bombBagLimit() {
        return 30;
    }
}