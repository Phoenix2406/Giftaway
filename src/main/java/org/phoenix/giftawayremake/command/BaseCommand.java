package org.phoenix.giftawayremake.command;

import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BaseCommand implements CommandInterface {

    private GiftawayCore plugin;

    public BaseCommand(GiftawayCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (plugin.getConfig().getString("language").equals("en_US")) {
            sender.sendMessage(ColorCodeTranslate.chat(""));
            sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&lGiftaway Plugin"));
            sender.sendMessage(ColorCodeTranslate.chat("&bTo see all command use /giftaway help."));
            sender.sendMessage(ColorCodeTranslate.chat(""));
        } else if (plugin.getConfig().getString("language").equals("ind_ID")) {
            sender.sendMessage(ColorCodeTranslate.chat(""));
            sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&lGiftaway Plugin"));
            sender.sendMessage(ColorCodeTranslate.chat("&bUntuk melihat semua command gunakan /giftaway help."));
            sender.sendMessage(ColorCodeTranslate.chat(""));
        } else if (plugin.getConfig().getString("language").equals("rus_RU")) {
            sender.sendMessage(ColorCodeTranslate.chat(""));
            sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&lGiftaway Plugin"));
            sender.sendMessage(ColorCodeTranslate.chat("&bЧтобы увидеть все команды, используйте /giftaway help."));
            sender.sendMessage(ColorCodeTranslate.chat(""));
        } else if (plugin.getConfig().getString("language").equals("ara_SA")) {
            sender.sendMessage(ColorCodeTranslate.chat(""));
            sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&lGiftaway Plugin"));
            sender.sendMessage(ColorCodeTranslate.chat("&bلرؤية كل استخدام الأوامر /giftaway help."));
            sender.sendMessage(ColorCodeTranslate.chat(""));
        } else {
            p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "Sorry! This language is not available in giftaway plugin. Check console/config to check typo in Language configuration."));
            throw new RuntimeException("language " + plugin.getConfig().getString("language") + " is not available!");
        }
        return false;
    }
}
