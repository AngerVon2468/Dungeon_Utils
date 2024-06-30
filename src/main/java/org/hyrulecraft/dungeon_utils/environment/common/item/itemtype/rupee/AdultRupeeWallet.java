package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.rupee;

public class AdultRupeeWallet extends AbstractRupeeWalletItem {

    public AdultRupeeWallet(Settings settings) {
        super(settings);
    }

    @Override
    public int rupeeWalletLimit() {
        return 500;
    }
}