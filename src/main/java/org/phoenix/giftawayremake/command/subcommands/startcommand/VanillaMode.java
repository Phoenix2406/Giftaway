package org.phoenix.giftawayremake.command.subcommands.startcommand;

import me.clip.placeholderapi.PlaceholderAPI;
import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.command.CommandInterface;
import org.phoenix.giftawayremake.filesmanager.LanguageDataFiles;
import org.phoenix.giftawayremake.filesmanager.SoundDataFiles;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class VanillaMode implements CommandInterface {

    private GiftawayCore plugin;

    public VanillaMode(GiftawayCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (plugin.getConfig().getString("language").equals("en_US")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.start")) {
                if (!(args.length >= 2)) {
                    sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                    sender.sendMessage(ColorCodeTranslate.chat(""));
                    sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start [minecraft item ID] [amount] &f▶ &7Start giftaway in Vanilla Mode System."));
                    sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start [MMO Type] [MMO Item] [amount] [MMO Unidentified] &f▶ &7Start giftaway in MMO Mode System."));
                    sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start [messages] &f▶ &7Start giftaway in Custom Mode System."));
                    sender.sendMessage(ColorCodeTranslate.chat(""));
                    sender.sendMessage(ColorCodeTranslate.chat("&cNote: You can change Giftaway Mode Type in config.yml!"));
                    sender.sendMessage(ColorCodeTranslate.chat(""));
                    sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
                } else {
                    if (plugin.getConfig().getInt("needPlayer") == 0) {
                        sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawaySystem.requiredPlayer")));
                        throw new IndexOutOfBoundsException("The integer of required player on config.yml must be greater than 0");
                    } else {
                        if (!(Bukkit.getServer().getOnlinePlayers().size() >= plugin.getConfig().getInt("needPlayer"))) {
                            sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&cSorry! Required " + plugin.getConfig().getInt("needPlayer") + " online players to start giftaway."));
                        } else {
                            Material itemType = Material.matchMaterial(args[1]);
                            if (itemType == null) {
                                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayReward.invalidReward") + args[1] + "."));
                            } else {
                                if (args.length != 3) {
                                    sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayReward.VanillaMode.noAmount")));
                                } else {
                                    int amount = 0;
                                    try {
                                        amount = Integer.parseInt(args[2]);
                                    } catch (NumberFormatException e) {
                                        sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawaySystem.invalidAmount")));
                                        return true;
                                    }
                                    if (plugin.alreadyCMD == false) {
                                        if (plugin.getConfig().getBoolean("chatMute") == true) {
                                            if (plugin.muted == false) {
                                                plugin.alreadyCMD = true;
                                                plugin.muted = true;
                                                for (Player players : Bukkit.getOnlinePlayers()) {
                                                    players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onLock")), 5, 1);
                                                }
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayChat.chatLock").replace("%player%", p.getName()))));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onStarting")), 5, 1);
                                                        }
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayBroadcast.VanillaMode.startedGiftaway") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay"));
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onSearching")), 5, 1);
                                                        }
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayBroadcast.searchingWinner")));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 100);
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onGetting")), 5, 1);
                                                        }
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayBroadcast.gettingWinner")));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 200);
                                                int vanillaAmount = amount;
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onWinner")), 5, 1);
                                                        }

                                                        if (!Bukkit.getServer().getOnlinePlayers().isEmpty()) {
                                                            int random = new Random().nextInt(Bukkit.getOnlinePlayers().size());
                                                            GiftawayCore.getInstance().rplayer = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[random];

                                                            if (Bukkit.getServer().getOnlinePlayers().size() >= plugin.getConfig().getInt("needPlayer")) {
                                                                if (!plugin.alreadyWin.contains(plugin.rplayer.getUniqueId())) {
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayBroadcast.VanillaMode.winnerBroadcast") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));

                                                                    ItemStack itemStack = new ItemStack(itemType, vanillaAmount);
                                                                    GiftawayCore.getInstance().rplayer.getInventory().addItem(itemStack);
                                                                    GiftawayCore.getInstance().rplayer.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aYou receive &b" + "x" + args[2] + " " + args[1].replace("_", " ") + " &a item from giftaway."));
                                                                    new BukkitRunnable() {

                                                                        @Override
                                                                        public void run() {
                                                                            for (Player players : Bukkit.getOnlinePlayers()) {
                                                                                players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onUnlock")), 5, 1);
                                                                            }
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayChat.chatUnlock").replace("%player%", p.getName()))));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            plugin.alreadyCMD = false;
                                                                            plugin.muted = false;
                                                                        }
                                                                    }.runTaskLater(plugin, plugin.getConfig().getInt("delay"));
                                                                } else {
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lSorry! Player " + plugin.rplayer.getName() + " already get reward from giftaway winner. No reward has been gave."));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    new BukkitRunnable() {

                                                                        @Override
                                                                        public void run() {
                                                                            for (Player players : Bukkit.getOnlinePlayers()) {
                                                                                players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onUnlock")), 5, 1);
                                                                            }
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayChat.chatUnlock"))));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            plugin.alreadyCMD = false;
                                                                            plugin.muted = false;
                                                                        }
                                                                    }.runTaskLater(plugin, plugin.getConfig().getInt("delay"));
                                                                }
                                                            } else {
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lGiftaway has been canceled due to player required " + plugin.getConfig().getInt("needPlayer") + " online players."));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                plugin.alreadyCMD = false;
                                                                plugin.muted = false;
                                                            }
                                                        } else {
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lGiftaway has been canceled due to no online players!"));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            plugin.alreadyCMD = false;
                                                            plugin.muted = false;
                                                        }
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 300);
                                            }
                                        } else if (plugin.getConfig().getBoolean("chatMute") != true) {
                                            plugin.alreadyCMD = true;
                                            for (Player players : Bukkit.getOnlinePlayers()) {
                                                players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onStarting")), 5, 1);
                                            }
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayBroadcast.VanillaMode.startedGiftaway") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                                        players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onSearching")), 5, 1);
                                                    }
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayBroadcast.searchingWinner")));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                }
                                            }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 100);
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                                        players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onGetting")), 5, 1);
                                                    }
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayBroadcast.gettingWinner")));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                }
                                            }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 200);
                                            int vanillaAmount = amount;
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                                        players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onWinner")), 5, 1);
                                                    }

                                                    if (!Bukkit.getServer().getOnlinePlayers().isEmpty()) {
                                                        int random = new Random().nextInt(Bukkit.getOnlinePlayers().size());
                                                        GiftawayCore.getInstance().rplayer = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[random];

                                                        if (Bukkit.getServer().getOnlinePlayers().size() >= plugin.getConfig().getInt("needPlayer")) {
                                                            if (!plugin.alreadyWin.contains(plugin.rplayer.getUniqueId())) {
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawayBroadcast.VanillaMode.winnerBroadcast") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));

                                                                ItemStack itemStack = new ItemStack(itemType, vanillaAmount);
                                                                GiftawayCore.getInstance().rplayer.getInventory().addItem(itemStack);
                                                                GiftawayCore.getInstance().rplayer.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aYou receive &b" + "x" + args[2] + " " + args[1].replace("_", " ") + " &a item from giftaway."));
                                                                plugin.alreadyCMD = false;
                                                            } else {
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lSorry! Player " + plugin.rplayer.getName() + " already get reward from giftaway winner. No reward has been gave."));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                plugin.alreadyCMD = false;
                                                            }
                                                        } else {
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lGiftaway has been canceled due to player required " + plugin.getConfig().getInt("needPlayer") + " online players."));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            plugin.alreadyCMD = false;
                                                        }
                                                    } else {
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lGiftaway has been canceled due to no online players!"));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        plugin.alreadyCMD = false;
                                                    }
                                                }
                                            }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 300);
                                        }
                                    } else if (plugin.alreadyCMD != false) {
                                        sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawaySystem.alreadyCMD")));
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageUSConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("ind_ID")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.start")) {
                if (!(args.length >= 2)) {
                    sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                    sender.sendMessage(ColorCodeTranslate.chat(""));
                    sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start [minecraft item ID] [amount] &f▶ &7Memulai Giftaway di Mode Vanilla Sistem."));
                    sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start [MMO Type] [MMO Item] [amount] [MMO Unidentified] &f▶ &7Memulai Giftaway di Mode MMO Sistem."));
                    sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start [messages] &f▶ &7Memulai Giftaway di Mode Custom Sistem."));
                    sender.sendMessage(ColorCodeTranslate.chat(""));
                    sender.sendMessage(ColorCodeTranslate.chat("&cCatatan: Kamu bisa merubah Tipe Mode Giftaway di config.yml!"));
                    sender.sendMessage(ColorCodeTranslate.chat(""));
                    sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
                } else {
                    if (plugin.getConfig().getInt("needPlayer") == 0) {
                        sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawaySystem.requiredPlayer")));
                        throw new IndexOutOfBoundsException("Integer dari player yang dibutuhkan pada config.yml harus lebih besar dari 0");
                    } else {
                        if (!(Bukkit.getServer().getOnlinePlayers().size() >= plugin.getConfig().getInt("needPlayer"))) {
                            sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&cMaaf! Dibutuhkan " + plugin.getConfig().getInt("needPlayer") + " players yang online untuk memulai giftaway."));
                        } else {
                            Material itemType = Material.matchMaterial(args[1]);
                            if (itemType == null) {
                                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayReward.invalidReward") + args[1] + "."));
                            } else {
                                if (args.length != 3) {
                                    sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayReward.VanillaMode.noAmount")));
                                } else {
                                    int amount = 0;
                                    try {
                                        amount = Integer.parseInt(args[2]);
                                    } catch (NumberFormatException e) {
                                        sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawaySystem.invalidAmount")));
                                        return true;
                                    }
                                    if (plugin.alreadyCMD == false) {
                                        if (plugin.getConfig().getBoolean("chatMute") == true) {
                                            if (plugin.muted == false) {
                                                plugin.alreadyCMD = true;
                                                plugin.muted = true;
                                                for (Player players : Bukkit.getOnlinePlayers()) {
                                                    players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onLock")), 5, 1);
                                                }
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayChat.chatLock").replace("%player%", p.getName()))));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onStarting")), 5, 1);
                                                        }
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayBroadcast.VanillaMode.startedGiftaway") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay"));
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onSearching")), 5, 1);
                                                        }
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayBroadcast.searchingWinner")));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 100);
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onGetting")), 5, 1);
                                                        }
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayBroadcast.gettingWinner")));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 200);
                                                int vanillaAmount = amount;
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onWinner")), 5, 1);
                                                        }

                                                        if (!Bukkit.getServer().getOnlinePlayers().isEmpty()) {
                                                            int random = new Random().nextInt(Bukkit.getOnlinePlayers().size());
                                                            GiftawayCore.getInstance().rplayer = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[random];

                                                            if (Bukkit.getServer().getOnlinePlayers().size() >= plugin.getConfig().getInt("needPlayer")) {
                                                                if (!plugin.alreadyWin.contains(plugin.rplayer.getUniqueId())) {
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayBroadcast.VanillaMode.winnerBroadcast") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));

                                                                    ItemStack itemStack = new ItemStack(itemType, vanillaAmount);
                                                                    GiftawayCore.getInstance().rplayer.getInventory().addItem(itemStack);
                                                                    GiftawayCore.getInstance().rplayer.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aKamu menerima barang &b" + "x" + args[2] + " " + args[1].replace("_", " ") + " &a dari giftaway."));
                                                                    new BukkitRunnable() {

                                                                        @Override
                                                                        public void run() {
                                                                            for (Player players : Bukkit.getOnlinePlayers()) {
                                                                                players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onUnlock")), 5, 1);
                                                                            }
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayChat.chatUnlock").replace("%player%", p.getName()))));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            plugin.alreadyCMD = false;
                                                                            plugin.muted = false;
                                                                        }
                                                                    }.runTaskLater(plugin, plugin.getConfig().getInt("delay"));
                                                                } else {
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lMaaf! Player " + plugin.rplayer.getName() + " telah mendapatkan hadiah dari pemenang giftaway. Tidak ada hadiah yang diberikan."));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    new BukkitRunnable() {

                                                                        @Override
                                                                        public void run() {
                                                                            for (Player players : Bukkit.getOnlinePlayers()) {
                                                                                players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onUnlock")), 5, 1);
                                                                            }
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayChat.chatUnlock"))));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            plugin.alreadyCMD = false;
                                                                            plugin.muted = false;
                                                                        }
                                                                    }.runTaskLater(plugin, plugin.getConfig().getInt("delay"));
                                                                }
                                                            } else {
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lGiftaway telah dibatalkan karena player yang dibutuhkan " + plugin.getConfig().getInt("needPlayer") + " players yang online."));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                plugin.alreadyCMD = false;
                                                                plugin.muted = false;
                                                            }
                                                        } else {
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lGiftaway telah dibatalkan karena tidak ada players yang online!"));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            plugin.alreadyCMD = false;
                                                            plugin.muted = false;
                                                        }
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 300);
                                            }
                                        } else if (plugin.getConfig().getBoolean("chatMute") != true) {
                                            plugin.alreadyCMD = true;
                                            for (Player players : Bukkit.getOnlinePlayers()) {
                                                players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onStarting")), 5, 1);
                                            }
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayBroadcast.VanillaMode.startedGiftaway") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                                        players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onSearching")), 5, 1);
                                                    }
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayBroadcast.searchingWinner")));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                }
                                            }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 100);
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                                        players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onGetting")), 5, 1);
                                                    }
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayBroadcast.gettingWinner")));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                }
                                            }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 200);
                                            int vanillaAmount = amount;
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                                        players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onWinner")), 5, 1);
                                                    }

                                                    if (!Bukkit.getServer().getOnlinePlayers().isEmpty()) {
                                                        int random = new Random().nextInt(Bukkit.getOnlinePlayers().size());
                                                        GiftawayCore.getInstance().rplayer = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[random];

                                                        if (Bukkit.getServer().getOnlinePlayers().size() >= plugin.getConfig().getInt("needPlayer")) {
                                                            if (!plugin.alreadyWin.contains(plugin.rplayer.getUniqueId())) {
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawayBroadcast.VanillaMode.winnerBroadcast") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));

                                                                ItemStack itemStack = new ItemStack(itemType, vanillaAmount);
                                                                GiftawayCore.getInstance().rplayer.getInventory().addItem(itemStack);
                                                                GiftawayCore.getInstance().rplayer.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aKamu menerima barang &b" + "x" + args[2] + " " + args[1].replace("_", " ") + " &a dari giftaway."));
                                                                plugin.alreadyCMD = false;
                                                            } else {
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lMaaf! Player " + plugin.rplayer.getName() + " telah mendapatkan hadiah dari pemenang giftaway. Tidak ada hadiah yang diberikan."));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                plugin.alreadyCMD = false;
                                                            }
                                                        } else {
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lGiftaway telah dibatalkan karena player yang dibutuhkan " + plugin.getConfig().getInt("needPlayer") + " players yang online."));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            plugin.alreadyCMD = false;
                                                        }
                                                    } else {
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lGiftaway telah dibattalkan karena tidak ada players yang online!"));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        plugin.alreadyCMD = false;
                                                    }
                                                }
                                            }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 300);
                                        }
                                    } else if (plugin.alreadyCMD != false) {
                                        sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawaySystem.alreadyCMD")));
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageIDConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("rus_RU")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.start")) {
                if (!(args.length >= 2)) {
                    sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                    sender.sendMessage(ColorCodeTranslate.chat(""));
                    sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start [minecraft item ID] [amount] &f▶ &7Начать подарок в системе Vanilla Mode."));
                    sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start [MMO Type] [MMO Item] [amount] [MMO Unidentified] &f▶ &7Начать подарочную игру в системе MMO Mode."));
                    sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start [messages] &f▶ &7Начать подарок в пользовательской системе режима."));
                    sender.sendMessage(ColorCodeTranslate.chat(""));
                    sender.sendMessage(ColorCodeTranslate.chat("&cПримечание: Вы можете изменить тип режима Giftaway в config.yml!"));
                    sender.sendMessage(ColorCodeTranslate.chat(""));
                    sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
                } else {
                    if (plugin.getConfig().getInt("needPlayer") == 0) {
                        sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawaySystem.requiredPlayer")));
                        throw new IndexOutOfBoundsException("Целое число требуемого игрока в config.yml должно быть больше 0");
                    } else {
                        if (!(Bukkit.getServer().getOnlinePlayers().size() >= plugin.getConfig().getInt("needPlayer"))) {
                            sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&cПрости! Требуется " + plugin.getConfig().getInt("needPlayer") + " онлайн-игроков, чтобы начать подарочный розыгрыш."));
                        } else {
                            Material itemType = Material.matchMaterial(args[1]);
                            if (itemType == null) {
                                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayReward.invalidReward") + args[1] + "."));
                            } else {
                                if (args.length != 3) {
                                    sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayReward.VanillaMode.noAmount")));
                                } else {
                                    int amount = 0;
                                    try {
                                        amount = Integer.parseInt(args[2]);
                                    } catch (NumberFormatException e) {
                                        sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawaySystem.invalidAmount")));
                                        return true;
                                    }
                                    if (plugin.alreadyCMD == false) {
                                        if (plugin.getConfig().getBoolean("chatMute") == true) {
                                            if (plugin.muted == false) {
                                                plugin.alreadyCMD = true;
                                                plugin.muted = true;
                                                for (Player players : Bukkit.getOnlinePlayers()) {
                                                    players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onLock")), 5, 1);
                                                }
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayChat.chatLock").replace("%player%", p.getName()))));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onStarting")), 5, 1);
                                                        }
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayBroadcast.VanillaMode.startedGiftaway") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay"));
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onSearching")), 5, 1);
                                                        }
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayBroadcast.searchingWinner")));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 100);
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onGetting")), 5, 1);
                                                        }
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayBroadcast.gettingWinner")));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 200);
                                                int vanillaAmount = amount;
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onWinner")), 5, 1);
                                                        }

                                                        if (!Bukkit.getServer().getOnlinePlayers().isEmpty()) {
                                                            int random = new Random().nextInt(Bukkit.getOnlinePlayers().size());
                                                            GiftawayCore.getInstance().rplayer = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[random];

                                                            if (Bukkit.getServer().getOnlinePlayers().size() >= plugin.getConfig().getInt("needPlayer")) {
                                                                if (!plugin.alreadyWin.contains(plugin.rplayer.getUniqueId())) {
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayBroadcast.VanillaMode.winnerBroadcast") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));

                                                                    ItemStack itemStack = new ItemStack(itemType, vanillaAmount);
                                                                    GiftawayCore.getInstance().rplayer.getInventory().addItem(itemStack);
                                                                    GiftawayCore.getInstance().rplayer.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aВы получите &b" + "x" + args[2] + " " + args[1].replace("_", " ") + " &a товар от Giftaway."));
                                                                    new BukkitRunnable() {

                                                                        @Override
                                                                        public void run() {
                                                                            for (Player players : Bukkit.getOnlinePlayers()) {
                                                                                players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onUnlock")), 5, 1);
                                                                            }
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayChat.chatUnlock").replace("%player%", p.getName()))));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            plugin.alreadyCMD = false;
                                                                            plugin.muted = false;
                                                                        }
                                                                    }.runTaskLater(plugin, plugin.getConfig().getInt("delay"));
                                                                } else {
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lПрости! Игрок " + plugin.rplayer.getName() + " уже получите вознаграждение от победителя Giftaway. Награда не дана."));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    new BukkitRunnable() {

                                                                        @Override
                                                                        public void run() {
                                                                            for (Player players : Bukkit.getOnlinePlayers()) {
                                                                                players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onUnlock")), 5, 1);
                                                                            }
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayChat.chatUnlock"))));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            plugin.alreadyCMD = false;
                                                                            plugin.muted = false;
                                                                        }
                                                                    }.runTaskLater(plugin, plugin.getConfig().getInt("delay"));
                                                                }
                                                            } else {
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lGiftaway был отменен из-за того, что требуется игрок " + plugin.getConfig().getInt("needPlayer") + " онлайн игроки."));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                plugin.alreadyCMD = false;
                                                                plugin.muted = false;
                                                            }
                                                        } else {
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lGiftaway был отменен из-за отсутствия онлайн-игроков!"));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            plugin.alreadyCMD = false;
                                                            plugin.muted = false;
                                                        }
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 300);
                                            }
                                        } else if (plugin.getConfig().getBoolean("chatMute") != true) {
                                            plugin.alreadyCMD = true;
                                            for (Player players : Bukkit.getOnlinePlayers()) {
                                                players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onStarting")), 5, 1);
                                            }
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayBroadcast.VanillaMode.startedGiftaway") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                                        players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onSearching")), 5, 1);
                                                    }
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayBroadcast.searchingWinner")));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                }
                                            }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 100);
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                                        players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onGetting")), 5, 1);
                                                    }
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayBroadcast.gettingWinner")));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                }
                                            }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 200);
                                            int vanillaAmount = amount;
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                                        players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onWinner")), 5, 1);
                                                    }

                                                    if (!Bukkit.getServer().getOnlinePlayers().isEmpty()) {
                                                        int random = new Random().nextInt(Bukkit.getOnlinePlayers().size());
                                                        GiftawayCore.getInstance().rplayer = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[random];

                                                        if (Bukkit.getServer().getOnlinePlayers().size() >= plugin.getConfig().getInt("needPlayer")) {
                                                            if (!plugin.alreadyWin.contains(plugin.rplayer.getUniqueId())) {
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawayBroadcast.VanillaMode.winnerBroadcast") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));

                                                                ItemStack itemStack = new ItemStack(itemType, vanillaAmount);
                                                                GiftawayCore.getInstance().rplayer.getInventory().addItem(itemStack);
                                                                GiftawayCore.getInstance().rplayer.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aВы получите &b" + "x" + args[2] + " " + args[1].replace("_", " ") + " &a товар от Giftaway."));
                                                                plugin.alreadyCMD = false;
                                                            } else {
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lПрости! Игрок " + plugin.rplayer.getName() + " уже получите вознаграждение от победителя Giftaway. Награда не дана."));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                plugin.alreadyCMD = false;
                                                            }
                                                        } else {
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lGiftaway был отменен из-за того, что требуется игрок " + plugin.getConfig().getInt("needPlayer") + " онлайн игроки."));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            plugin.alreadyCMD = false;
                                                        }
                                                    } else {
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lGiftaway был отменен из-за отсутствия онлайн-игроков!"));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        plugin.alreadyCMD = false;
                                                    }
                                                }
                                            }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 300);
                                        }
                                    } else if (plugin.alreadyCMD != false) {
                                        sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawaySystem.alreadyCMD")));
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageRUConfig().getString("languages.giftawaySystem.noPermission")));
            }
        } else if (plugin.getConfig().getString("language").equals("ara_SA")) {
            if (p.isOp() || p.hasPermission("giftaway.admin.start")) {
                if (!(args.length >= 2)) {
                    sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m------------&r&8&l[&#1BFF00&l Giftaway &8&l]&#1BFF00&l&m------------"));
                    sender.sendMessage(ColorCodeTranslate.chat(""));
                    sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start [minecraft item ID] [amount] &f▶ &7ابدأ Giftaway في نظام Vanilla Mode."));
                    sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start [MMO Type] [MMO Item] [amount] [MMO Unidentified] &f▶ &7ابدأ Giftaway في نظام MMO Mode."));
                    sender.sendMessage(ColorCodeTranslate.chat("&a/giftaway start [messages] &f▶ &7ابدأ الإهداء في نظام الوضع المخصص."));
                    sender.sendMessage(ColorCodeTranslate.chat(""));
                    sender.sendMessage(ColorCodeTranslate.chat("&cملاحظة :يمكنك تغيير نوع وضع Giftaway في config.yml!"));
                    sender.sendMessage(ColorCodeTranslate.chat(""));
                    sender.sendMessage(ColorCodeTranslate.chat("&#1BFF00&l&m----------------------------------"));
                } else {
                    if (plugin.getConfig().getInt("needPlayer") == 0) {
                        sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawaySystem.requiredPlayer")));
                        throw new IndexOutOfBoundsException("يجب أن يكون العدد الصحيح للاعب المطلوب في config.yml أكبر من 0");
                    } else {
                        if (!(Bukkit.getServer().getOnlinePlayers().size() >= plugin.getConfig().getInt("needPlayer"))) {
                            sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&cآسف! المطلوب " + plugin.getConfig().getInt("needPlayer") + " لاعبين عبر الإنترنت لبدء giftaway."));
                        } else {
                            Material itemType = Material.matchMaterial(args[1]);
                            if (itemType == null) {
                                sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayReward.invalidReward") + args[1] + "."));
                            } else {
                                if (args.length != 3) {
                                    sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayReward.VanillaMode.noAmount")));
                                } else {
                                    int amount = 0;
                                    try {
                                        amount = Integer.parseInt(args[2]);
                                    } catch (NumberFormatException e) {
                                        sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawaySystem.invalidAmount")));
                                        return true;
                                    }
                                    if (plugin.alreadyCMD == false) {
                                        if (plugin.getConfig().getBoolean("chatMute") == true) {
                                            if (plugin.muted == false) {
                                                plugin.alreadyCMD = true;
                                                plugin.muted = true;
                                                for (Player players : Bukkit.getOnlinePlayers()) {
                                                    players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onLock")), 5, 1);
                                                }
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayChat.chatLock").replace("%player%", p.getName()))));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onStarting")), 5, 1);
                                                        }
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayBroadcast.VanillaMode.startedGiftaway") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay"));
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onSearching")), 5, 1);
                                                        }
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayBroadcast.searchingWinner")));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 100);
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onGetting")), 5, 1);
                                                        }
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayBroadcast.gettingWinner")));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 200);
                                                int vanillaAmount = amount;
                                                new BukkitRunnable() {

                                                    @Override
                                                    public void run() {
                                                        for (Player players : Bukkit.getOnlinePlayers()) {
                                                            players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onWinner")), 5, 1);
                                                        }

                                                        if (!Bukkit.getServer().getOnlinePlayers().isEmpty()) {
                                                            int random = new Random().nextInt(Bukkit.getOnlinePlayers().size());
                                                            GiftawayCore.getInstance().rplayer = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[random];

                                                            if (Bukkit.getServer().getOnlinePlayers().size() >= plugin.getConfig().getInt("needPlayer")) {
                                                                if (!plugin.alreadyWin.contains(plugin.rplayer.getUniqueId())) {
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayBroadcast.VanillaMode.winnerBroadcast") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));

                                                                    ItemStack itemStack = new ItemStack(itemType, vanillaAmount);
                                                                    GiftawayCore.getInstance().rplayer.getInventory().addItem(itemStack);
                                                                    GiftawayCore.getInstance().rplayer.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aانت تستقبل &b" + "x" + args[2] + " " + args[1].replace("_", " ") + " &a  عنصر من giftaway."));
                                                                    new BukkitRunnable() {

                                                                        @Override
                                                                        public void run() {
                                                                            for (Player players : Bukkit.getOnlinePlayers()) {
                                                                                players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onUnlock")), 5, 1);
                                                                            }
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayChat.chatUnlock").replace("%player%", p.getName()))));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            plugin.alreadyCMD = false;
                                                                            plugin.muted = false;
                                                                        }
                                                                    }.runTaskLater(plugin, plugin.getConfig().getInt("delay"));
                                                                } else {
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lآسف! لاعب " + plugin.rplayer.getName() + "احصل بالفعل على مكافأة من الفائز الموهوب. لم يتم منح أي مكافأة."));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                    new BukkitRunnable() {

                                                                        @Override
                                                                        public void run() {
                                                                            for (Player players : Bukkit.getOnlinePlayers()) {
                                                                                players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onUnlock")), 5, 1);
                                                                            }
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayChat.chatUnlock"))));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                            plugin.alreadyCMD = false;
                                                                            plugin.muted = false;
                                                                        }
                                                                    }.runTaskLater(plugin, plugin.getConfig().getInt("delay"));
                                                                }
                                                            } else {
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lتم إلغاء Giftaway بسبب وجود لاعب مطلوب " + plugin.getConfig().getInt("needPlayer") + " لاعبين عبر الإنترنت."));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                plugin.alreadyCMD = false;
                                                                plugin.muted = false;
                                                            }
                                                        } else {
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lتم إلغاء Giftaway بسبب عدم وجود لاعبين على الإنترنت!"));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            plugin.alreadyCMD = false;
                                                            plugin.muted = false;
                                                        }
                                                    }
                                                }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 300);
                                            }
                                        } else if (plugin.getConfig().getBoolean("chatMute") != true) {
                                            plugin.alreadyCMD = true;
                                            for (Player players : Bukkit.getOnlinePlayers()) {
                                                players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onStarting")), 5, 1);
                                            }
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayBroadcast.VanillaMode.startedGiftaway") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                                        players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onSearching")), 5, 1);
                                                    }
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayBroadcast.searchingWinner")));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                }
                                            }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 100);
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                                        players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onGetting")), 5, 1);
                                                    }
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayBroadcast.gettingWinner")));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                    Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                }
                                            }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 200);
                                            int vanillaAmount = amount;
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                                        players.playSound(players.getLocation(), Sound.valueOf(SoundDataFiles.getSoundConfig().getString("sounds.onWinner")), 5, 1);
                                                    }

                                                    if (!Bukkit.getServer().getOnlinePlayers().isEmpty()) {
                                                        int random = new Random().nextInt(Bukkit.getOnlinePlayers().size());
                                                        GiftawayCore.getInstance().rplayer = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[random];

                                                        if (Bukkit.getServer().getOnlinePlayers().size() >= plugin.getConfig().getInt("needPlayer")) {
                                                            if (!plugin.alreadyWin.contains(plugin.rplayer.getUniqueId())) {
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(PlaceholderAPI.setPlaceholders(p, LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawayBroadcast.VanillaMode.winnerBroadcast") + "x" + args[2] + " " + args[1].replace("_", " "))));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));

                                                                ItemStack itemStack = new ItemStack(itemType, vanillaAmount);
                                                                GiftawayCore.getInstance().rplayer.getInventory().addItem(itemStack);
                                                                GiftawayCore.getInstance().rplayer.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + "&aانت تستقبل &b" + "x" + args[2] + " " + args[1].replace("_", " ") + " &a  عنصر من giftaway."));
                                                                plugin.alreadyCMD = false;
                                                            } else {
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lآسف! لاعب " + plugin.rplayer.getName() + "احصل بالفعل على مكافأة من الفائز الموهوب. لم يتم منح أي مكافأة."));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                                plugin.alreadyCMD = false;
                                                            }
                                                        } else {
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lتم إلغاء Giftaway بسبب وجود لاعب مطلوب " + plugin.getConfig().getInt("needPlayer") + " لاعبين عبر الإنترنت."));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                            plugin.alreadyCMD = false;
                                                        }
                                                    } else {
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat("&c&lتم إلغاء Giftaway بسبب عدم وجود لاعبين على الإنترنت!"));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        Bukkit.broadcastMessage(ColorCodeTranslate.chat(""));
                                                        plugin.alreadyCMD = false;
                                                    }
                                                }
                                            }.runTaskLater(plugin, plugin.getConfig().getInt("delay") + 300);
                                        }
                                    } else if (plugin.alreadyCMD != false) {
                                        sender.sendMessage(ColorCodeTranslate.chat(plugin.getConfig().getString("prefix") + LanguageDataFiles.getLanguageSAConfig().getString("languages.giftawaySystem.alreadyCMD")));
                                    }
                                }
                            }
                        }
                    }
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
