package org.phoenix.giftawayremake.filesmanager;

import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerDataFiles {

    public static GiftawayCore plugin;

    public PlayerDataFiles(GiftawayCore plugin) {
        this.plugin = plugin;
    }

    private static File playerFile;
    private static FileConfiguration playerConfig;

    public static void setupPlayersFile() {
        playerFile = new File(Bukkit.getServer().getPluginManager().getPlugin("Giftaway").getDataFolder(), "playerdata.yml");

        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        playerConfig = YamlConfiguration.loadConfiguration(playerFile);
    }

    public static FileConfiguration getPlayerConfig() {
        return playerConfig;
    }

    public static void playerSave() {
        try {
            playerConfig.save(playerFile);
        } catch (IOException ex) {
            plugin.getLogger().info(ColorCodeTranslate.chat("&cCouldn't save playerdata.yml files!"));
        }
    }

    public static void playerReload() {
        playerConfig = YamlConfiguration.loadConfiguration(playerFile);
    }
}
