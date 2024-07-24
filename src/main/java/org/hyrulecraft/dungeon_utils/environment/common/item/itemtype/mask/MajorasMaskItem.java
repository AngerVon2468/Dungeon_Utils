package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.mask;

import net.minecraft.item.Item;

public class MajorasMaskItem extends AbstractMaskItem {

    public MajorasMaskItem(Settings settings) {
        super(settings);
    }

    @Override
    Item getItem() {
        return this;
    }
}