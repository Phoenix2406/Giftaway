package org.phoenix.giftawayremake.eventhandler;

import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.filesmanager.PlayerDataFiles;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerDataEvent implements Listener {

    public GiftawayCore plugin;

    public PlayerDataEvent(GiftawayCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (!p.hasPlayedBefore()) {
            if (!PlayerDataFiles.getPlayerConfig().isSet("WinnerData." + p.getUniqueId())) {
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "Name", p.getName());
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "VanillaWinner", "NO");
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "MMOWinner", "NO");
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "CustomWinner", "NO");
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "VanillaAmount", 0);
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "MMOAmount", 0);
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "CustomAmount", 0);
                PlayerDataFiles.playerSave();
            }
        } else if (p.hasPlayedBefore()) {
            if (!PlayerDataFiles.getPlayerConfig().isSet("WinnerData." + p.getUniqueId())) {
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "Name", p.getName());
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "VanillaWinner", "NO");
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "MMOWinner", "NO");
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "CustomWinner", "NO");
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "VanillaAmount", 0);
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "MMOAmount", 0);
                PlayerDataFiles.getPlayerConfig().set("WinnerData." + p.getUniqueId() + "." + "CustomAmount", 0);
                PlayerDataFiles.playerSave();
            }
        }
    }
}
