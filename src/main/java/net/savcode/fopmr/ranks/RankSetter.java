package net.savcode.fopmr.ranks;

import net.savcode.fopmr.config.FOPMR_ConfigEntry;
import net.savcode.fopmr.config.FOPMR_ConfigFiles;
import net.savcode.fopmr.utils.FOPMR_Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankSetter {
    
    public static void setSuperAdmin(Player player, CommandSender sender) {
        
        String UUID = player.getUniqueId().toString();
        if (!FOPMR_ConfigEntry.AdminConfig().contains(UUID)) {
            // Add the player entry if it isn't there
            FOPMR_ConfigEntry.AdminConfig().set(UUID + ".name", player.getName().toLowerCase());
            FOPMR_ConfigEntry.AdminConfig().set(UUID + ".ip", player.getAddress().getHostString());
            FOPMR_ConfigEntry.AdminConfig().set(UUID + ".loginmsg", "");
            FOPMR_ConfigEntry.AdminConfig().set(UUID + ".cmdspy", false);
            FOPMR_ConfigEntry.AdminConfig().set(UUID + ".isactive", true);
            FOPMR_ConfigEntry.AdminConfig().set(UUID + ".isadmin", true);
            FOPMR_ConfigEntry.AdminConfig().set(UUID + ".istelnet", false);
            FOPMR_ConfigEntry.AdminConfig().set(UUID + ".issenior", false);
            // Then save the config
            FOPMR_ConfigFiles.getAdmins().reloadConfig();
        }else {
            sender.sendMessage(FOPMR_Utils.color("&cThe player is already a Super Admin!"));
        }
    }
    
    public static void setTelnetAdmin(Player player, CommandSender sender) {
        String UUID = player.getUniqueId().toString();
        
        if (FOPMR_ConfigEntry.AdminConfig().getBoolean(UUID + ".istelnet")) {
            sender.sendMessage(ChatColor.RED + "Player is already telnet admin!");
        }
        else {
            if (!FOPMR_ConfigEntry.AdminConfig().contains(UUID)) {
                sender.sendMessage(ChatColor.RED + "Player must be Super Admin to add them to a higher rank!");
            }
            else {
                FOPMR_ConfigEntry.AdminConfig().set(UUID + ".name", player.getName().toLowerCase());
                FOPMR_ConfigEntry.AdminConfig().set(UUID + ".ip", player.getAddress().getHostString());
                FOPMR_ConfigEntry.AdminConfig().set(UUID + ".istelnet", true);
                FOPMR_ConfigFiles.getAdmins().reloadConfig();
            }
        } 
    }
    
    public static void setSeniorAdmin(Player player, CommandSender sender) {
        String UUID = player.getUniqueId().toString();
        
        if (FOPMR_ConfigEntry.AdminConfig().getBoolean(UUID + ".issenior")) {
            sender.sendMessage(ChatColor.RED + "Player is already senior admin!");
        }
        else {
            if (!FOPMR_ConfigEntry.AdminConfig().contains(UUID)) {
                sender.sendMessage(ChatColor.RED + "Player must be Super Admin to add them to a higher rank!");
            }
            else {
                FOPMR_ConfigEntry.AdminConfig().set(UUID + ".name", player.getName().toLowerCase());
                FOPMR_ConfigEntry.AdminConfig().set(UUID + ".ip", player.getAddress().getHostString());
                FOPMR_ConfigEntry.AdminConfig().set(UUID + ".istelnet", true);
                FOPMR_ConfigEntry.AdminConfig().set(UUID + ".issenior", true);
                FOPMR_ConfigFiles.getAdmins().reloadConfig();
            }
        } 
    }
    
    public static void removeAdmin(Player player, CommandSender sender) {
        if (FOPMR_ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".isadmin")) {
            sender.sendMessage(ChatColor.RED + "Player must be Admin to remove them from admin :P");
        }else {
            FOPMR_ConfigEntry.AdminConfig().set(player.getUniqueId().toString() + ".isactive", false);
            FOPMR_ConfigFiles.getAdmins().reloadConfig();
        }
    }
}
