package net.savcode.fopmr.commands;

import java.util.Arrays;
import net.savcode.fopmr.FrostedOPMod;
import net.savcode.fopmr.config.FOPMR_ConfigFiles;
import net.savcode.fopmr.ranks.Rank;
import net.savcode.fopmr.utils.FOPMR_Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class C_fopmr extends FOPMR_Command {
    
    public C_fopmr() {
        super("fopmr", "/fopmr <reload>", "Main CMD ;D", Arrays.asList("fopm", "frostedopmodr"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if (args.length == 1) {
            
            if (!args[0].equals("reload")) {
                return false;
            }

            if (!Rank.isAdmin(sender)) {
                sender.sendMessage(NO_PERM);
                return true;
            }
            
            FOPMR_ConfigFiles.getAdmins().reloadConfig();
            FOPMR_ConfigFiles.getDonators().reloadConfig();
            FOPMR_ConfigFiles.getMConfig().reloadConfig();
            FOPMR_ConfigFiles.getPlayer().reloadConfig();
            sender.sendMessage(ChatColor.GRAY + "FrostedOPMod: Remastered has been reloaded!");
            return true;
        }
        
        if (args.length == 0) {
            sender.sendMessage(FOPMR_Utils.color("&8&m----------------------"));
            sender.sendMessage(ChatColor.BLUE + "FrostedOPMod: Remastered");
            sender.sendMessage(ChatColor.BLUE + "Version: " + FrostedOPMod.plugin.getDescription().getVersion());
            sender.sendMessage(ChatColor.BLUE + "Developers: " + FrostedOPMod.plugin.getDescription().getAuthors());
            sender.sendMessage(FOPMR_Utils.color("&8&m----------------------"));
            return true;
        }
        return true;
    }
}

