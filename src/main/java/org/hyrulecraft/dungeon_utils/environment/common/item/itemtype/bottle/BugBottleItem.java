package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.bottle;

public class BugBottleItem extends AbstractFilledBottleItem {

    public BugBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    String getNbtKey() {
        return "dungeon_utils.bug_bottle.type";
    }
}