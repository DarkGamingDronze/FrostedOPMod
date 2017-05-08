package net.savcode.fopmr.commands;

import net.savcode.fopmr.utils.FOPMR_Utils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_kick extends FOPMR_Command {
    
    public C_kick() {
        super("kick", "/kick <player>", "Kick a player");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        final String reason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
        final Player target = Bukkit.getServer().getPlayer(args[0]);
        
        if (args.length == 1) {
            
            if (target != null) {
                    FOPMR_Utils.bcastMsg(ChatColor.RED + sender.getName() + " has kicked, " + target.getName() + "!");
                    target.kickPlayer(ChatColor.RED + "You have been kicked by " + sender.getName() + "!");
                    return true;
                }
                else if (target == null) {
                    sender.sendMessage(PLAYER_NOT_FOUND);
                    return true;
                }
            }
        
            if (args.length > 1) {
                
                if (reason != null) {
                    FOPMR_Utils.bcastMsg(ChatColor.RED + sender.getName() + " kicked " + target.getName() + " for, " + reason + "!");
                    target.kickPlayer(ChatColor.RED + "You have been kicked by " + sender.getName() + " for, " + reason + "!");
                }
            }
        return true;
    }
}
