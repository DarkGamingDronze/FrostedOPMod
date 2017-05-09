
package net.savcode.fopmr.commands;

import java.util.Collection;
import static net.savcode.fopmr.commands.FOPMR_Command.NO_PERM;
import net.savcode.fopmr.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_deopall extends FOPMR_Command {
    
    public C_deopall() {
        super("deopall", "/deopall", "De-ops all players on the server");
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
                player.sendMessage(ChatColor.AQUA + sender.getName() + " - De-opping all players on the server!");
                player.sendMessage(DEOP);
                player.setOp(false);
            }
            return true;
        }
        return true;
    }
}
