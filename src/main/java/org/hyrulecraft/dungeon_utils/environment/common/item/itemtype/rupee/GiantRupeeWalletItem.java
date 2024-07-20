package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.rupee;

public class GiantRupeeWalletItem extends AbstractRupeeWalletItem {

    public GiantRupeeWalletItem(Settings settings) {
        super(settings);
    }

    @Override
    public int rupeeWalletLimit() {
        return 200;
    }
}