/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.savcode.fopmr.commands;

import java.util.Arrays;
import net.savcode.fopmr.config.FOPMR_ConfigEntry;
import net.savcode.fopmr.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class C_ip extends FOPMR_Command {
    
    public C_ip() {
        super("ip", "/ip <playername>", "Find someones ip!", Arrays.asList("iphist", "iphistory", "findip"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }
        
        if (args.length != 1) {
            return false;
        }
        
        final Player player = Bukkit.getServer().getPlayer(args[0]);
        
        if (player == null) {
            sender.sendMessage(PLAYER_NOT_FOUND);
            return true;
        }
        
        sender.sendMessage(ChatColor.GRAY + player.getName() + " IP: " + FOPMR_ConfigEntry.PlayerConfig().getString(player.getUniqueId().toString() + ".ip"));
        return true;
    }
}