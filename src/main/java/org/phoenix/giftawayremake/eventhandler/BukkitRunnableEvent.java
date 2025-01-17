package org.phoenix.giftawayremake.eventhandler;

import org.phoenix.giftawayremake.GiftawayCore;

public class BukkitRunnableEvent {

    private GiftawayCore plugin;

    public BukkitRunnableEvent(GiftawayCore plugin) {
        this.plugin = plugin;
    }

    public void startRunnable() {
        plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            plugin.alreadyWin.clear();
        },18000, 36000);
    }
}
