package org.phoenix.giftawayremake.eventhandler;

import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateNotifyEvent implements Listener {

    private GiftawayCore plugin;

    // Register the plugin variable to get variable in Main Class
    public UpdateNotifyEvent(GiftawayCore plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        // Register the Event for PlayerJoinEvent
        if (plugin.getConfig().getString("language").equals("en_US")){
            if (p.isOp() || p.hasPermission("giftaway.admin.notification")) {
                if (plugin.getConfig().getBoolean("updateNotify") == true) {
                    p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aIf you want to check update for this plugin available or not, you can dm &bThunderBolt0624#0469&a."));
                } else if (plugin.getConfig().getBoolean("updateNotify") != true) {
                    p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&cUpdate notify has been disable on config.yml, you can re-enable it later."));
                }
            }
        } else if (plugin.getConfig().getString("language").equals("ind_ID")){
            if (p.isOp() || p.hasPermission("giftaway.admin.notification")) {
                if (plugin.getConfig().getBoolean("updateNotify") == true) {
                    p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aJika kamu ingin cek update untuk plugin ini tersedia atau tidak, kamu bisa dm &bThunderBolt0624#0469&a."));
                } else if (plugin.getConfig().getBoolean("updateNotify") != true) {
                    p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&cNotif update telah dinonaktifkan pada config.yml, kamu bisa mengaktifkan kembali nanti."));
                }
            }
        } else if (plugin.getConfig().getString("language").equals("rus_RU")){
            if (p.isOp() || p.hasPermission("giftaway.admin.notification")) {
                if (plugin.getConfig().getBoolean("updateNotify") == true) {
                    p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aЕсли вы хотите проверить наличие обновления для этого плагина, вы можете dm &bThunderBolt0624#0469&a."));
                } else if (plugin.getConfig().getBoolean("updateNotify") != true) {
                    p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&cУведомление об обновлении отключено в config.yml, вы можете снова включить его позже."));
                }
            }
        } else if (plugin.getConfig().getString("language").equals("ara_SA")){
            if (p.isOp() || p.hasPermission("giftaway.admin.notification")) {
                if (plugin.getConfig().getBoolean("updateNotify") == true) {
                    p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aإذا كنت تريد التحقق من توفر تحديث لهذا المكون الإضافي أم لا ، فيمكنك dm &bThunderBolt0624#0469&a."));
                } else if (plugin.getConfig().getBoolean("updateNotify") != true) {
                    p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&cتم تعطيل إشعار التحديث في config.yml ، يمكنك إعادة تمكينه لاحقًا."));
                }
            }
        } else {
            p.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&cSorry! This language is not available in giftaway plugin. Check console/config to check typo in Language configuration."));
            throw new RuntimeException("language " + plugin.getConfig().getString("language") + " is not available!");
        }
    }
}
