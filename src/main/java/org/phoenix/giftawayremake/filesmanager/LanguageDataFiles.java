package org.phoenix.giftawayremake.filesmanager;

import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LanguageDataFiles {

    public static GiftawayCore plugin;

    public LanguageDataFiles(GiftawayCore plugin) {
        this.plugin = plugin;
    }

    private static File languageFileFolder;

    // English Language Definition
    private static File languageUSFile;
    private static FileConfiguration languageUSConfig;
    // Indonesian Language Definition
    private static File languageIDFile;
    private static FileConfiguration languageIDConfig;
    // Rusian Language Definition
    private static File languageRUFile;
    private static FileConfiguration languageRUConfig;
    // Arabian Language Definition
    private static File languageSAFile;
    private static FileConfiguration languageSAConfig;

    public static void setupLanguageFolder(GiftawayCore plugin) {
        languageFileFolder = new File(plugin.getDataFolder(), "Language");
        if (!languageFileFolder.exists()) {
            languageFileFolder.mkdir();
        }
    }

    // English Language Section
    public static void setupLanguageUSFiles() {
        languageUSFile = new File(Bukkit.getServer().getPluginManager().getPlugin("Giftaway").getDataFolder(), "Language/en_US.yml");

        if (!languageUSFile.exists()) {
            try {
                languageUSFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        languageUSConfig = YamlConfiguration.loadConfiguration(languageUSFile);
    }

    public static FileConfiguration getLanguageUSConfig() {
        return languageUSConfig;
    }

    public static void languageUSSave() {
        try {
            languageUSConfig.save(languageUSFile);
        } catch (IOException ex) {
            plugin.getLogger().info(ColorCodeTranslate.chat("&cCouldn't save en_US.yml data files!"));
        }
    }

    public static void languageUSReload() {
        languageUSConfig = YamlConfiguration.loadConfiguration(languageUSFile);
    }

    // Indonesian Language Section
    public static void setupLanguageIDFiles() {
        languageIDFile = new File(Bukkit.getServer().getPluginManager().getPlugin("Giftaway").getDataFolder(), "Language/ind_ID.yml");

        if (!languageIDFile.exists()) {
            try {
                languageIDFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        languageIDConfig = YamlConfiguration.loadConfiguration(languageIDFile);
    }

    public static FileConfiguration getLanguageIDConfig() {
        return languageIDConfig;
    }

    public static void languageIDSave() {
        try {
            languageIDConfig.save(languageIDFile);
        } catch (IOException ex) {
            plugin.getLogger().info(ColorCodeTranslate.chat("&cCouldn't save ind_ID.yml data files!"));
        }
    }

    public static void languageIDReload() {
        languageIDConfig = YamlConfiguration.loadConfiguration(languageIDFile);
    }

    // Rusian Language Section
    public static void setupLanguageRUFiles() {
        languageRUFile = new File(Bukkit.getServer().getPluginManager().getPlugin("Giftaway").getDataFolder(), "Language/rus_RU.yml");

        if (!languageRUFile.exists()) {
            try {
                languageRUFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        languageRUConfig = YamlConfiguration.loadConfiguration(languageRUFile);
    }

    public static FileConfiguration getLanguageRUConfig() {
        return languageRUConfig;
    }

    public static void languageRUSave() {
        try {
            languageRUConfig.save(languageRUFile);
        } catch (IOException ex) {
            plugin.getLogger().info(ColorCodeTranslate.chat("&cCouldn't save rus_RU.yml data files!"));
        }
    }

    public static void languageRUReload() {
        languageRUConfig = YamlConfiguration.loadConfiguration(languageRUFile);
    }

    // Arabian Language Section
    public static void setupLanguageSAFiles() {
        languageSAFile = new File(Bukkit.getServer().getPluginManager().getPlugin("Giftaway").getDataFolder(), "Language/ara_SA.yml");

        if (!languageSAFile.exists()) {
            try {
                languageSAFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        languageSAConfig = YamlConfiguration.loadConfiguration(languageSAFile);
    }

    public static FileConfiguration getLanguageSAConfig() {
        return languageSAConfig;
    }

    public static void languageSASave() {
        try {
            languageSAConfig.save(languageSAFile);
        } catch (IOException ex) {
            plugin.getLogger().info(ColorCodeTranslate.chat("&cCouldn't save ara_SA.yml data files!"));
        }
    }

    public static void languageSAReload() {
        languageSAConfig = YamlConfiguration.loadConfiguration(languageSAFile);
    }
}
