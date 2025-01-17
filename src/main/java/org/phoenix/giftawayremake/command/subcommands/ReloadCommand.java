package org.phoenix.giftawayremake.command.subcommands;

import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.command.CommandInterface;
import org.phoenix.giftawayremake.filesmanager.LanguageDataFiles;
import org.phoenix.giftawayremake.filesmanager.PlayerDataFiles;
import org.phoenix.giftawayremake.filesmanager.SoundDataFiles;
import org.phoenix.giftawayremake.placeholderapi.PAPIExpansion;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ReloadCommand implements CommandInterface, Listener {

    private GiftawayCore plugin;

    public ReloadCommand(GiftawayCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (plugin.getConfig().getString("language").equals("en_US")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.reload")) {
                if (!plugin.getConfig().getString("version").equals(GiftawayCore.getInstance().getVersion())) {
                    plugin.getPluginLoader().disablePlugin(plugin);
                    sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawaySystem.unsuccessfulReload")));
                    throw new RuntimeException("Plugin version is corrupted on config.yml");
                } else {
                    if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                        new PAPIExpansion().register();
                        Bukkit.getPluginManager().registerEvents(this, plugin);
                    }
                    plugin.getLogger().info("Sounds Data File successfully reloaded!");
                    plugin.getLogger().info("Player Data Files successfully reloaded!");
                    plugin.getLogger().info("Language folder successfully reloaded!");
                    sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawaySystem.successfulReload")));
                    plugin.reloadConfig();
                    SoundDataFiles.soundReload();
                    PlayerDataFiles.playerReload();
                    LanguageDataFiles.languageUSReload();
                    LanguageDataFiles.languageIDReload();
                    LanguageDataFiles.languageRUReload();
                    LanguageDataFiles.languageSAReload();
                }
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("ind_ID")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.reload")) {
                if (!plugin.getConfig().getString("version").equals(GiftawayCore.getInstance().getVersion())) {
                    plugin.getPluginLoader().disablePlugin(plugin);
                    sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawaySystem.unsuccessfulReload")));
                    throw new RuntimeException("Versi plugin telah rusak di config.yml");
                } else {
                    if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                        new PAPIExpansion().register();
                        Bukkit.getPluginManager().registerEvents(this, plugin);
                    }
                    plugin.getLogger().info("Sounds Data File successfully reloaded!");
                    plugin.getLogger().info("Player Data Files successfully reloaded!");
                    plugin.getLogger().info("Language folder successfully reloaded!");
                    sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawaySystem.successfulReload")));
                    plugin.reloadConfig();
                    SoundDataFiles.soundReload();
                    PlayerDataFiles.playerReload();
                    LanguageDataFiles.languageUSReload();
                    LanguageDataFiles.languageIDReload();
                    LanguageDataFiles.languageRUReload();
                    LanguageDataFiles.languageSAReload();
                }
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("rus_RU")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.reload")) {
                if (!plugin.getConfig().getString("version").equals(GiftawayCore.getInstance().getVersion())) {
                    plugin.getPluginLoader().disablePlugin(plugin);
                    sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawaySystem.unsuccessfulReload")));
                    throw new RuntimeException("Версия плагина повреждена в config.yml");
                } else {
                    if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                        new PAPIExpansion().register();
                        Bukkit.getPluginManager().registerEvents(this, plugin);
                    }
                    plugin.getLogger().info("Sounds Data File successfully reloaded!");
                    plugin.getLogger().info("Player Data Files successfully reloaded!");
                    plugin.getLogger().info("Language folder successfully reloaded!");
                    sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawaySystem.successfulReload")));
                    plugin.reloadConfig();
                    SoundDataFiles.soundReload();
                    PlayerDataFiles.playerReload();
                    LanguageDataFiles.languageUSReload();
                    LanguageDataFiles.languageIDReload();
                    LanguageDataFiles.languageRUReload();
                    LanguageDataFiles.languageSAReload();
                }
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("ara_SA")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.reload")) {
                if (!plugin.getConfig().getString("version").equals(GiftawayCore.getInstance().getVersion())) {
                    plugin.getPluginLoader().disablePlugin(plugin);
                    sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawaySystem.unsuccessfulReload")));
                    throw new RuntimeException("إصدار البرنامج المساعد تالف في config.yml");
                } else {
                    if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                        new PAPIExpansion().register();
                        Bukkit.getPluginManager().registerEvents(this, plugin);
                    }
                    plugin.getLogger().info("Sounds Data File successfully reloaded!");
                    plugin.getLogger().info("Player Data Files successfully reloaded!");
                    plugin.getLogger().info("Language folder successfully reloaded!");
                    sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawaySystem.successfulReload")));
                    plugin.reloadConfig();
                    SoundDataFiles.soundReload();
                    PlayerDataFiles.playerReload();
                    LanguageDataFiles.languageUSReload();
                    LanguageDataFiles.languageIDReload();
                    LanguageDataFiles.languageRUReload();
                    LanguageDataFiles.languageSAReload();
                }
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else {
            p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "Sorry! This language is not available in giftaway plugin. Check console/config to check typo in Language configuration."));
            throw new RuntimeException("language " + plugin.getConfig().getString("language") + " is not available!");
        }
        return false;
    }
}
