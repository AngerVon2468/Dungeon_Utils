package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bottle;

public class FairyBottleItem extends AbstractFilledBottleItem {

    public FairyBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    String getNbtKey() {
        return "dungeon_utils.fairy_bottle.type";
    }
}