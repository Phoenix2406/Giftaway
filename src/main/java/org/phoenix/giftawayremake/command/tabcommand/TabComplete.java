package org.phoenix.giftawayremake.command.tabcommand;

import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.Type;
import org.phoenix.giftawayremake.GiftawayCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabComplete implements TabCompleter {

    private GiftawayCore plugin;

    // Register the plugin variable to get variable in Main Class
    public TabComplete(GiftawayCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        // Tab Completer for command in argument 1
        if (args.length == 1) {
            if (sender.isOp() || sender.hasPermission("giftaway.admin.help")) {
                commands.add("help");
            }
            if (sender.isOp() || sender.hasPermission("giftaway.admin.info")) {
                commands.add("info");
            }
            if (sender.isOp() || sender.hasPermission("giftaway.admin.start")) {
                commands.add("start");
            }
            if (sender.isOp() || sender.hasPermission("giftaway.admin.reload")) {
                commands.add("reload");
            }
            if (sender.isOp() || sender.hasPermission("giftaway.admin.version")) {
                commands.add("version");
            }
            StringUtil.copyPartialMatches(args[0], commands, completions);

            // Tab Completer for command in argument 2
        } else if (args.length == 2) {
            if (args[0].equals("start")) {
                if (sender.isOp() || sender.hasPermission("giftaway.admin.start")) {
                    if (plugin.getConfig().getString("TypeMode").equals("Vanilla")) {
                        commands.add("[<Minecraft Item ID>]");
                    } else if (plugin.getConfig().getString("TypeMode").equals("MMO")) {
                        for (Type type : MMOItems.plugin.getTypes().getAll())
                            commands.add(type.getId());
                    } else if (plugin.getConfig().getString("TypeMode").equals("Custom")) {
                        commands.add("[<reward message>]");
                    }
                }
            }
            StringUtil.copyPartialMatches(args[1], commands, completions);

            // Tab Completer for command in argument 3
        } else if (args.length == 3) {
            if (args[0].equals("start")) {
                if (sender.isOp() || sender.hasPermission("giftaway.admin.start")) {
                    if (plugin.getConfig().getString("TypeMode").equals("Vanilla")) {
                        commands.add("8");
                        commands.add("16");
                        commands.add("24");
                        commands.add("32");
                        commands.add("48");
                        commands.add("56");
                        commands.add("64");
                    } else if (plugin.getConfig().getString("TypeMode").equals("MMO")) {
                        commands.add("[<MMO Item ID>]");
                    }
                }
            }
            StringUtil.copyPartialMatches(args[2], commands, completions);

            // Tab Completer for command in argument 4
        } else if (args.length == 4) {
            if (args[0].equals("start")) {
                if (sender.isOp() || sender.hasPermission("giftaway.admin.start")) {
                    if (plugin.getConfig().getString("TypeMode").equals("MMO")) {
                        commands.add("8");
                        commands.add("16");
                        commands.add("24");
                        commands.add("32");
                        commands.add("48");
                        commands.add("56");
                        commands.add("64");
                    }
                }
            }
            StringUtil.copyPartialMatches(args[3], commands, completions);

            // Tab Completer for command in argument 5
        } else if (args.length == 5) {
            if (args[0].equals("start")) {
                if (sender.isOp() || sender.hasPermission("giftaway.admin.start")) {
                    if (plugin.getConfig().getString("TypeMode").equals("MMO")) {
                        commands.add("0");
                        commands.add("10");
                        commands.add("20");
                        commands.add("30");
                        commands.add("40");
                        commands.add("50");
                        commands.add("60");
                        commands.add("70");
                        commands.add("80");
                        commands.add("90");
                        commands.add("100");
                    }
                }
            }
            StringUtil.copyPartialMatches(args[4], commands, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}
