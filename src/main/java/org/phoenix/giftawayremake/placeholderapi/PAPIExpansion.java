package org.phoenix.giftawayremake.placeholderapi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.filesmanager.PlayerDataFiles;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PAPIExpansion extends PlaceholderExpansion {

    // Get identifier for giftaway placeholder
    public String getIdentifier() {
        return "giftaway";
    }

    // Get author of giftaway placeholder
    public String getAuthor() {
        return "Atha06";
    }

    // Get version of giftaway placeholder
    public String getVersion() {
        return "2.0";
    }

    public String onPlaceholderRequest(Player player, String identifier) {

        // Getting giftaway name placeholder. This will return like %giftaway_name%
        if (identifier.equalsIgnoreCase("name")) {
            return player.getName();
        }

        // Getting giftaway winner name placeholder. This will return like %giftaway_winner%
        if (identifier.equalsIgnoreCase("winner")) {

            // Detect if random player is not online
            if (GiftawayCore.getInstance().rplayer != null) {

                // Detect if player already winner or not
                if (!GiftawayCore.getInstance().alreadyWin.contains(GiftawayCore.getInstance().rplayer.getUniqueId())) {
                    GiftawayCore.getInstance().alreadyWin.add(GiftawayCore.getInstance().rplayer.getUniqueId());

                    if (GiftawayCore.getInstance().getConfig().getString("TypeMode").equals("Vanilla")) {
                        PlayerDataFiles.getPlayerConfig().set("WinnerData." + GiftawayCore.getInstance().rplayer.getUniqueId() + "." + "VanillaType", "YES");
                        PlayerDataFiles.getPlayerConfig().set("WinnerData." + GiftawayCore.getInstance().rplayer.getUniqueId() + "." + "VanillaAmount", + 1);
                    } else if (GiftawayCore.getInstance().getConfig().getString("TypeMode").equals("MMO")) {
                        PlayerDataFiles.getPlayerConfig().set("WinnerData." + GiftawayCore.getInstance().rplayer.getUniqueId() + "." + "MMOType", "YES");
                        PlayerDataFiles.getPlayerConfig().set("WinnerData." + GiftawayCore.getInstance().rplayer.getUniqueId() + "." + "MMOAmount", + 1);
                    } else if (GiftawayCore.getInstance().getConfig().getString("TypeMode").equals("Custom")) {
                        PlayerDataFiles.getPlayerConfig().set("WinnerData." + GiftawayCore.getInstance().rplayer.getUniqueId() + "." + "CustomType", "YES");
                        PlayerDataFiles.getPlayerConfig().set("WinnerData." + GiftawayCore.getInstance().rplayer.getUniqueId() + "." + "CustomAmount", + 1);
                    }
                    PlayerDataFiles.playerSave();
                    return GiftawayCore.getInstance().rplayer.getName();
                } else {
                    return null;
                }
            } else {
                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                Bukkit.broadcastMessage(ColorCodeTranslate.chat("Failed to getting winner, the winner is disconnected/leave server!"));
                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                return null;
            }
        }
        return null;
    }
}
