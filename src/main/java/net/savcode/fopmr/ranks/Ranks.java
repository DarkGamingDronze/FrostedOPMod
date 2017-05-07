package net.savcode.fopmr.ranks;

import net.savcode.fopmr.config.FOPMR_ConfigEntry;
import net.savcode.fopmr.utils.FOPMR_Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ranks {

    // Admin Section
    
    public static boolean isImposter(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }
        
        final Player player = (Player) sender;
        
        return FOPMR_ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".isimposter");
    }
    
    public static boolean isSuperAdmin(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }
        
        final Player player = (Player) sender;
        
        return FOPMR_ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".isadmin");
    }
    
    public static boolean isTelnetAdmin(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }
        
        final Player player = (Player) sender;
        
        return FOPMR_ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".istelnet");
    }
    
    public static boolean isSeniorAdmin(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }
        
        final Player player = (Player) sender;
        
        return FOPMR_ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".issenior");
    }
    
    public static boolean isOwner(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }
        
        final Player player = (Player) sender;
        
        return FOPMR_Utils.OWNER.contains(player.getName());
    }
    
    public static boolean isDeveloper(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }
        
        final Player player = (Player) sender;
        
        return FOPMR_Utils.DEVELOPER.contains(player.getName());
    }
    
    // Donator Section that will be done in a bit.
    
    // player section
    
    public static boolean isOp(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }
        
        final Player player = (Player) sender;
        
        return !FOPMR_ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".isadmin");
    }
}