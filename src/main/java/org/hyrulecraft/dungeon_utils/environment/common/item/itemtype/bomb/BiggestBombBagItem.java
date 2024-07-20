package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bomb;

public class BiggestBombBagItem extends AbstractBombBagItem {

    public BiggestBombBagItem(Settings settings) {
        super(settings);
    }

    @Override
    public int bombBagLimit() {
        return 40;
    }
}