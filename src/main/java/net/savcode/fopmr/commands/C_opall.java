package net.savcode.fopmr.commands;

import java.util.Collection;
import net.savcode.fopmr.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_opall extends FOPMR_Command {
    
    public C_opall() {
        super("opall", "/opall <-c, -s>", "Ops all players on the server");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return false;
        }
        
        Collection<Player> online = (Collection<Player>) Bukkit.getOnlinePlayers();
        
        if (args.length == 0) {
            online.forEach((target) -> {
                target.sendMessage(ChatColor.AQUA + sender.getName() + " - Opping all players on the server!");
                target.sendMessage(OP);
                target.setOp(true);
            });
            return true;
        }
        
        if (args.length == 1) {
            
            if (args[0].equals("-c")) {
                online.forEach((target) -> {
                    target.sendMessage(ChatColor.AQUA + sender.getName() + " - Opping all players on the server!");
                    target.sendMessage(OP);
                    target.setOp(true);
                    target.setGameMode(GameMode.CREATIVE);
                });
                return true;
            }
            
            if (args[0].equals("-s")) {
                online.forEach((target) -> {
                    target.sendMessage(ChatColor.AQUA + sender.getName() + " - Opping all players on the server!");
                    target.sendMessage(OP);
                    target.setOp(true);
                    target.setGameMode(GameMode.SURVIVAL);
                });
                return true;
            }
        }
        return true;
    }
}
