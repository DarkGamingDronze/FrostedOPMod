package net.savcode.fopmr.ranks;

import net.savcode.fopmr.utils.FOPMR_Utils;
import org.bukkit.command.CommandSender;

public class Rank {
    public static boolean isAdmin(CommandSender sender) {
        if (!Ranks.isSuperAdmin(sender) && !Ranks.isTelnetAdmin(sender) && !Ranks.isSeniorAdmin(sender)) {
            return false;
        }else {
            return true;
        }
    }
    
    public static boolean isTelnetAdmin(CommandSender sender) {
        if (!Ranks.isTelnetAdmin(sender) && !Ranks.isSeniorAdmin(sender)) {
            return false;
        }else {
            return true;
        }
    }
    
    public static boolean isSeniorAdmin(CommandSender sender) {
        if (!Ranks.isSeniorAdmin(sender)) {
            return false;
        }else {
            return true;
        }
    }
    
    public static boolean isExecutive(String name) {
        if (!FOPMR_Utils.DEVELOPER.contains(name) && !FOPMR_Utils.OWNER.contains(name)) {
            return false;
        }else {
            return true;
        }
    }
}
