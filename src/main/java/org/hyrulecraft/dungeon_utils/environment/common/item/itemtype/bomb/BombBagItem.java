package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bomb;

public class BombBagItem extends AbstractBombBagItem {

    public BombBagItem(Settings settings) {
        super(settings);
    }

    @Override
    public int bombBagLimit() {
        return 20;
    }
}