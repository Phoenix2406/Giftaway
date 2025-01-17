package org.phoenix.giftawayremake.command;

import org.phoenix.giftawayremake.GiftawayCore;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandHandler implements CommandExecutor {

    private static HashMap<String, CommandInterface> commands = new HashMap<String, CommandInterface>();

    public void register(String name, CommandInterface cmd) {
        commands.put(name, cmd);
    }

    public boolean exists(String name) {
        return commands.containsKey(name);
    }

    public CommandInterface getExecutor(String name) {
        return commands.get(name);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                getExecutor("giftaway").onCommand(sender, cmd, label, args);
                return true;
            }

            if (args.length > 0) {
                if (exists(args[0])) {
                    getExecutor(args[0]).onCommand(sender, cmd, label, args);
                    return true;
                } else {
                    sender.sendMessage(ColorCodeTranslate.chat(GiftawayCore.getInstance().getConfig().getString("prefix") + "&cInvalid command! Please use /giftaway help."));
                    return true;
                }
            }
        } else {
            sender.sendMessage(ColorCodeTranslate.chat(GiftawayCore.getInstance().getConfig().getString("prefix") + "&cYou must be a player to use this command!"));
            return true;
        }
        return false;
    }
}
