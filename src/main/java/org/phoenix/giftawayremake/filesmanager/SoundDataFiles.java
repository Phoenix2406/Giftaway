package org.phoenix.giftawayremake.filesmanager;

import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SoundDataFiles {

    public static GiftawayCore plugin;

    public SoundDataFiles(GiftawayCore plugin) {
        this.plugin = plugin;
    }

    private static File soundFile;
    private static FileConfiguration soundConfig;

    public static void setupSoundFile() {
        soundFile = new File(Bukkit.getServer().getPluginManager().getPlugin("Giftaway").getDataFolder(), "sounds.yml");

        if (!soundFile.exists()) {
            try {
                soundFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        soundConfig = YamlConfiguration.loadConfiguration(soundFile);
    }

    public static FileConfiguration getSoundConfig() {
        return soundConfig;
    }

    public static void soundSave() {
        try {
            soundConfig.save(soundFile);
        } catch (IOException ex) {
            plugin.getLogger().info(ColorCodeTranslate.chat("&cCouldn't save playerdata.yml files!"));
        }
    }

    public static void soundReload() {
        soundConfig = YamlConfiguration.loadConfiguration(soundFile);
    }
}
