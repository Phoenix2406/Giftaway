package org.phoenix.giftawayremake.eventhandler;

import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.filesmanager.LanguageDataFiles;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatMuteEvent implements Listener {

    public GiftawayCore plugin;

    public ChatMuteEvent(GiftawayCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (plugin.getConfig().getString("language").equals("en_US")) {
            if (plugin.muted != false) {
                if (p.isOp() || p.hasPermission("giftaway.admin.chatmute.bypass")) {
                    e.setCancelled(false);
                } else {
                    e.setCancelled(true);
                    p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayChat.chatMutePerm")));
                }
            }
        } else if (plugin.getConfig().getString("language").equals("ind_ID")) {
            if (plugin.muted != false) {
                if (p.isOp() || p.hasPermission("giftaway.admin.chatmute.bypass")) {
                    e.setCancelled(false);
                } else {
                    e.setCancelled(true);
                    p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayChat.chatMutePerm")));
                }
            }
        } else if (plugin.getConfig().getString("language").equals("rus_RU")) {
            if (plugin.muted != false) {
                if (p.isOp() || p.hasPermission("giftaway.admin.chatmute.bypass")) {
                    e.setCancelled(false);
                } else {
                    e.setCancelled(true);
                    p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayChat.chatMutePerm")));
                }
            }
        } else if (plugin.getConfig().getString("language").equals("ara_SA")) {
            if (plugin.muted != false) {
                if (p.isOp() || p.hasPermission("giftaway.admin.chatmute.bypass")) {
                    e.setCancelled(false);
                } else {
                    e.setCancelled(true);
                    p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayChat.chatMutePerm")));
                }
            }
        } else {
            p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "Sorry! This language is not available in giftaway plugin. Check console/config to check typo in Language configuration."));
            throw new RuntimeException("language " + plugin.getConfig().getString("language") + " is not available!");
        }
    }
}
