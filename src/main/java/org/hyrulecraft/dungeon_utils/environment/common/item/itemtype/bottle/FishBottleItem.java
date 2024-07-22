package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bottle;

public class FishBottleItem extends AbstractFilledBottleItem {

    public FishBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    String getNbtKey() {
        return "dungeon_utils.fish_bottle.type";
    }
}