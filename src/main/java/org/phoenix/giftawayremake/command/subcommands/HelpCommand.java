package org.phoenix.giftawayremake.command.subcommands;

import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.command.CommandInterface;
import org.phoenix.giftawayremake.filesmanager.LanguageDataFiles;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandInterface {

    private GiftawayCore plugin;

    public HelpCommand(GiftawayCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (plugin.getConfig().getString("language").equals("en_US")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.help")) {
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway help &f▶ &7To see all command in this plugin."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway info &f▶ &7To see information about this plugin."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start &f▶ &7More information about start Giftaway."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway reload &f▶ &7To reload this plugin."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway version &f▶ &7See version of this plugin."));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("ind_ID")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.help")) {
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway help &f▶ &7Untuk melihat semua command di plugin ini."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway info &f▶ &7Untuk melihat informasi tentang plugin ini."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start &f▶ &7Informasi lebih lanjut tentang memulai Giftaway"));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway reload &f▶ &7Untuk memuat ulang plugin ini."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway version &f▶ &7Melihat versi dari plugin ini."));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("rus_RU")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.help")) {
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway help &f▶ &7Чтобы увидеть все команды в этом плагине."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway info &f▶ &7Чтобы увидеть информацию об этом плагине."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start &f▶ &7Подробная информация о старте Giftaway."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway reload &f▶ &7Чтобы перезагрузить этот плагин."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway version &f▶ &7Посмотреть версию этого плагина."));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("ara_SA")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.help")) {
                sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                sender.sendMessage(ColorCodeTranslate.chat(""));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway help &f▶ &7لرؤية كل الأوامر في هذا البرنامج المساعد."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway info &f▶ &7لرؤية معلومات حول هذا البرنامج المساعد."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start &f▶ &7مزيد من المعلومات حول البدء Giftaway."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway reload &f▶ &7لإعادة تحميل هذا البرنامج المساعد."));
                sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway version &f▶ &7انظر نسخة من هذا البرنامج المساعد."));
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
