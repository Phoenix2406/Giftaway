package org.phoenix.giftawayremake.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandInterface {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);
}
