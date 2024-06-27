package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.rupee;

public class ChildRupeeWalletItem extends AbstractRupeeWalletItem {

    public ChildRupeeWalletItem(Settings settings) {
        super(settings);
    }

    @Override
    public int rupeeWalletLimit() {
        return 99;
    }
}