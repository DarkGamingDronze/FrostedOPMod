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
            for(Player player: online){
                player.sendMessage(ChatColor.AQUA + sender.getName() + " - Opping all players on the server!");
                player.sendMessage(OP);
                player.setOp(true);
            }
            return true;
        }
        
        if (args.length == 1) {
            
            if (args[0].equals("-c")) {
                for(Player player: online){
                    player.sendMessage(ChatColor.AQUA + sender.getName() + " - Opping all players on the server!");
                    player.sendMessage(OP);
                    player.setOp(true);
                    player.setGameMode(GameMode.CREATIVE);
                }
                return true;
            }
            
            if (args[0].equals("-s")) {
                for(Player player: online){
                    player.sendMessage(ChatColor.AQUA + sender.getName() + " - Opping all players on the server!");
                    player.sendMessage(OP);
                    player.setOp(true);
                    player.setGameMode(GameMode.SURVIVAL);
                }
                return true;
            }
        }
        return true;
    }
}
