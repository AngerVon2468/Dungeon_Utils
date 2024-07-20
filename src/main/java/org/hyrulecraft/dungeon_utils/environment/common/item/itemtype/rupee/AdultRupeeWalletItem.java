package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.rupee;

public class AdultRupeeWalletItem extends AbstractRupeeWalletItem {

    public AdultRupeeWalletItem(Settings settings) {
        super(settings);
    }

    @Override
    public int rupeeWalletLimit() {
        return 500;
    }
}