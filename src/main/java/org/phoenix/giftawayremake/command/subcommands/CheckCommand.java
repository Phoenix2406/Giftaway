package org.phoenix.giftawayremake.command.subcommands;

import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.command.CommandInterface;
import org.phoenix.giftawayremake.filesmanager.LanguageDataFiles;
import org.phoenix.giftawayremake.filesmanager.PlayerDataFiles;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckCommand implements CommandInterface {

    private GiftawayCore plugin;

    public CheckCommand(GiftawayCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (plugin.getConfig().getString("language").equals("en_US")) {
            if (p.isOp() || p.hasPermission("giftaway.check")) {
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&aName &f▶ " + p.getName()));
                sender.sendMessage(ColorCodeTranslate.chat("&aVanilla Winner &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "VanillaWinner")));
                sender.sendMessage(ColorCodeTranslate.chat("&aMMO Winner &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "MMOWinner")));
                sender.sendMessage(ColorCodeTranslate.chat("&aCustom Winner &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "CustomWinner")));
                sender.sendMessage(ColorCodeTranslate.chat("&aVanilla Amount &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "VanillaAmount")));
                sender.sendMessage(ColorCodeTranslate.chat("&aMMO Amount &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "MMOAmount")));
                sender.sendMessage(ColorCodeTranslate.chat("&aCustom Amount &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "CustomAmount")));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("ind_ID")) {
            if (p.isOp() || p.hasPermission("giftaway.check")) {
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&aNama &f▶ " + p.getName()));
                sender.sendMessage(ColorCodeTranslate.chat("&aPemenang VanillWa inner &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "VanillaWinner")));
                sender.sendMessage(ColorCodeTranslate.chat("&aPemenang MMO &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "MMOWinner")));
                sender.sendMessage(ColorCodeTranslate.chat("&aPemenang Custom &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "CustomWinner")));
                sender.sendMessage(ColorCodeTranslate.chat("&aJumlah Vanilla &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "VanillaAmount")));
                sender.sendMessage(ColorCodeTranslate.chat("&aJumlah MMO &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "MMOAmount")));
                sender.sendMessage(ColorCodeTranslate.chat("&aJumlah Custom &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "CustomAmount")));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("rus_RU")) {
            if (p.isOp() || p.hasPermission("giftaway.check")) {
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&aимя &f▶ " + p.getName()));
                sender.sendMessage(ColorCodeTranslate.chat("&aVanilla победитель &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "VanillaWinner")));
                sender.sendMessage(ColorCodeTranslate.chat("&aMMO победитель &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "MMOWinner")));
                sender.sendMessage(ColorCodeTranslate.chat("&aCustom победитель &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "CustomWinner")));
                sender.sendMessage(ColorCodeTranslate.chat("&aVanilla количество &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "VanillaAmount")));
                sender.sendMessage(ColorCodeTranslate.chat("&aMMO количество &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "MMOAmount")));
                sender.sendMessage(ColorCodeTranslate.chat("&aCustom количество &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "CustomAmount")));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("ara_SA")) {
            if (p.isOp() || p.hasPermission("giftaway.check")) {
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&aاسم &f▶ " + p.getName()));
                sender.sendMessage(ColorCodeTranslate.chat("&aVanilla الفائز &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "VanillaWinner")));
                sender.sendMessage(ColorCodeTranslate.chat("&aMMO الفائز &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "MMOWinner")));
                sender.sendMessage(ColorCodeTranslate.chat("&aCustom الفائز &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "CustomWinner")));
                sender.sendMessage(ColorCodeTranslate.chat("&aVanilla مقدار &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "VanillaAmount")));
                sender.sendMessage(ColorCodeTranslate.chat("&aMMO مقدار &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "MMOAmount")));
                sender.sendMessage(ColorCodeTranslate.chat("&aCustom مقدار &f▶ &7x" + PlayerDataFiles.getPlayerConfig().getString("WinnerData." + p.getUniqueId() + "." + "CustomAmount")));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
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
