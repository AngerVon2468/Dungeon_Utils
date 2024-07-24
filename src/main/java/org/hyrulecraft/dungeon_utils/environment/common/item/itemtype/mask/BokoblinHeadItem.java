package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.mask;

import net.minecraft.item.Item;

public class BokoblinHeadItem extends AbstractMaskItem {

    public BokoblinHeadItem(Settings settings) {
        super(settings);
    }

    @Override
    Item getItem() {
        return this;
    }
}