/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.savcode.fopmr.commands;

import java.util.Arrays;
import net.savcode.fopmr.FOPMR_Bans;
import static net.savcode.fopmr.commands.FOPMR_Command.NO_PERM;
import net.savcode.fopmr.config.FOPMR_ConfigEntry;
import net.savcode.fopmr.ranks.Rank;
import net.savcode.fopmr.utils.FOPMR_Utils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class C_gtfo extends FOPMR_Command {
    
    public C_gtfo() {
        super("gtfo", "/gtfo <playername> [reason]", "Ban someone!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }
        
        if (args.length == 0) {
            return false;
        }
        
        final String reason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
        Player target = Bukkit.getServer().getPlayer(args[0]);
        
        if (args.length == 1)
        {
            if (target == null)
            {
                sender.sendMessage(PLAYER_NOT_FOUND);
                return false;
            }
            FOPMR_Utils.bcastMsg(target.getName() + "has been a VERY naughty, naughty boy. \nBanning: " + target.getName() + ", IP: " 
                    + FOPMR_ConfigEntry.PlayerConfig().getString(target.getUniqueId().toString() + ".ip"));
            target.kickPlayer(ChatColor.RED + "GTFO");
            FOPMR_Bans.addBan(target, sender);
        }
        
        if (args.length > 1) {
            if (reason != null) {
                FOPMR_Utils.bcastMsg(target.getName() + "has been a VERY naughty, naughty boy. \nBanning: " + target.getName() + ", IP: " 
                    + FOPMR_ConfigEntry.PlayerConfig().getString(target.getUniqueId().toString() + ".ip") + "\nReason: " + ChatColor.GOLD + reason);
                target.kickPlayer(ChatColor.RED + "GTFO");
                FOPMR_Bans.addBan(target, sender, reason);
            }
        }
        return true;
    }
    
}
