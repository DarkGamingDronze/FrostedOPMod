package net.savcode.fopmr;

import net.savcode.fopmr.config.FOPMR_ConfigEntry;
import net.savcode.fopmr.config.FOPMR_ConfigFiles;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Start the banning system :P
public class FOPMR_Bans {
    
    public static void addBan(Player player, CommandSender sender, String reason) {
        FOPMR_ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".name", player.getName());
        FOPMR_ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".ip", player.getAddress().getHostString());
        FOPMR_ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".banner", sender.getName());
        FOPMR_ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".reason", reason);
        FOPMR_ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".isbanned", true);
        FOPMR_ConfigFiles.getBans().reloadConfig();
    }
    
    public static void addBan(Player player, CommandSender sender) {
        FOPMR_ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".name", player.getName());
        FOPMR_ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".ip", player.getAddress().getHostString());
        FOPMR_ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".banner", sender.getName());
        FOPMR_ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".reason", "No reason added!");
        FOPMR_ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".isbanned", true);
        FOPMR_ConfigFiles.getBans().reloadConfig();
    }
    
    public static void removeBan(Player player) {
        FOPMR_ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".isbanned", false);
        FOPMR_ConfigFiles.getBans().reloadConfig();
    }
    public static String getBanReason(Player player) {
        return FOPMR_ConfigEntry.BansConfig().getString(player.getUniqueId().toString() + ".reason");
    }
    
    public static String getBanner(Player player) {
        return FOPMR_ConfigEntry.BansConfig().getString(player.getUniqueId().toString() + ".banner");
    }
    
    public static boolean isBanned(Player player) {
        return FOPMR_ConfigEntry.BansConfig().getBoolean(player.getUniqueId().toString() + ".isbanned");
    }
}
