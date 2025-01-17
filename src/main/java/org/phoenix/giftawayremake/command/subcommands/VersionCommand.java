package org.phoenix.giftawayremake.command.subcommands;

import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.command.CommandInterface;
import org.phoenix.giftawayremake.filesmanager.LanguageDataFiles;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VersionCommand implements CommandInterface {

    private GiftawayCore plugin;

    public VersionCommand(GiftawayCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (plugin.getConfig().getString("language").equals("en_US")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.version")) {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aThis plugin running on &b&nversion " + GiftawayCore.getInstance().getVersion()));
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("ind_ID")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.version")) {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aVersi dari plugin ini berjalan di &b&nversi " + GiftawayCore.getInstance().getVersion()));
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("rus_RU")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.version")) {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aЭтот плагин запущен на &b&nверсии " + GiftawayCore.getInstance().getVersion()));
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("ara_SA")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.version")) {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aهذا البرنامج المساعد يعمل على &b&nversion " + GiftawayCore.getInstance().getVersion()));
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
