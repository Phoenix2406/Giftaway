package org.phoenix.giftawayremake.command.subcommands;

import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.command.CommandInterface;
import org.phoenix.giftawayremake.filesmanager.LanguageDataFiles;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommand implements CommandInterface {

    private GiftawayCore plugin;

    public InfoCommand(GiftawayCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (plugin.getConfig().getString("language").equals("en_US")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.info")) {
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&aName &f▶ &7Giftaway"));
                sender.sendMessage(ColorCodeTranslate.chat("&aCommand &f▶ &7/giftaway [command]"));
                sender.sendMessage(ColorCodeTranslate.chat("&aAliases &f▶ &7/ga [command]"));
                sender.sendMessage(ColorCodeTranslate.chat("&aDescription &f▶ &7" + GiftawayCore.getInstance().getDesc()));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&8&oThis Giftaway Plugin Make by Atha."));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("ind_ID")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.info")) {
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&aNama &f▶ &7Giftaway"));
                sender.sendMessage(ColorCodeTranslate.chat("&aCommand &f▶ &7/giftaway [command]"));
                sender.sendMessage(ColorCodeTranslate.chat("&aAlias &f▶ &7/ga [command]"));
                sender.sendMessage(ColorCodeTranslate.chat("&aDeskripsi &f▶ &7" + GiftawayCore.getInstance().getDesc()));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&8&oPlugin Giftaway ini dibuat oleh Atha."));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("rus_RU")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.info")) {
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&aИмя &f▶ &7Giftaway"));
                sender.sendMessage(ColorCodeTranslate.chat("&aкомандование &f▶ &7/giftaway [command]"));
                sender.sendMessage(ColorCodeTranslate.chat("&aПсевдонимы &f▶ &7/ga [command]"));
                sender.sendMessage(ColorCodeTranslate.chat("&aОписание &f▶ &7" + GiftawayCore.getInstance().getDesc()));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&8&oЭтот плагин Giftaway создан компанией Atha."));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("ara_SA")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.info")) {
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&aالاسم &f▶ &7Giftaway"));
                sender.sendMessage(ColorCodeTranslate.chat("&aأمر &f▶ &7/giftaway [command]"));
                sender.sendMessage(ColorCodeTranslate.chat("&aالأسماء المستعارة &f▶ &7/ga [command]"));
                sender.sendMessage(ColorCodeTranslate.chat("&aوصف &f▶ &7" + GiftawayCore.getInstance().getDesc()));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&8&oهذا البرنامج المساعد Giftaway صنع بواسطة Atha."));
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
