package org.hyrulecraft.dungeon_utils.environment.common.item.itemtype.rupee;

public class GiantRupeeWallet extends AbstractRupeeWalletItem {

    public GiantRupeeWallet(Settings settings) {
        super(settings);
    }

    @Override
    public int rupeeWalletLimit() {
        return 200;
    }
}