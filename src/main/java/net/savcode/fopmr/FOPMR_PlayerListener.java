package net.savcode.fopmr;

import net.savcode.fopmr.config.FOPMR_ConfigEntry;
import net.savcode.fopmr.config.FOPMR_ConfigFiles;
import net.savcode.fopmr.ranks.Ranks;
import net.savcode.fopmr.utils.FOPMR_Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FOPMR_PlayerListener implements Listener {
    
    FrostedOPMod plugin;
    
    @SuppressWarnings("LeakingThisInConstructor")
    public FOPMR_PlayerListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String UUID = event.getPlayer().getUniqueId().toString();
        FileConfiguration PlayerConfig = FOPMR_ConfigFiles.getPlayer().getConfig();
        
        if (!PlayerConfig.contains(UUID)) {
            PlayerConfig.set(UUID + ".name", event.getPlayer().getName());
            PlayerConfig.set(UUID + ".ip", event.getPlayer().getAddress().getHostString());
            PlayerConfig.set(UUID + ".tag", null);
            PlayerConfig.set(UUID + ".muted", false);
            PlayerConfig.set(UUID + ".frozen", false);
            PlayerConfig.set(UUID + ".cmdsblcked", false);
            FOPMR_ConfigFiles.getPlayer().reloadConfig();
        }
        else if (PlayerConfig.contains(UUID)) {
            PlayerConfig.set(UUID + ".name", event.getPlayer().getName());
            PlayerConfig.set(UUID + ".ip", event.getPlayer().getAddress().getHostString());
            FOPMR_ConfigFiles.getPlayer().reloadConfig();
        }
        
        if (FOPMR_ConfigEntry.AdminConfig().getBoolean(UUID + ".isactive")) {
            
            final Player player = event.getPlayer();
            
            if (Ranks.isSuperAdmin(player)) {
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".tag", "&6Super Admin &8&m-&b");
                FOPMR_Utils.bcastMsg(ChatColor.AQUA + event.getPlayer().getName() + " is a " + ChatColor.GOLD + ChatColor.ITALIC + "Super Admin" + ChatColor.AQUA + FOPMR_Utils.color(FOPMR_ConfigEntry.AdminConfig().getString(UUID + ".loginmsg")) + "!");
            }
            
            if (Ranks.isTelnetAdmin(player)) {
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".tag", "&3Telnet Admin &8&m-&b");
                FOPMR_ConfigFiles.getPlayer().saveConfig();
                FOPMR_Utils.bcastMsg(ChatColor.AQUA + event.getPlayer().getName() + " is a " + ChatColor.GREEN + ChatColor.ITALIC + "Telnet Admin" + ChatColor.AQUA + FOPMR_Utils.color(FOPMR_ConfigEntry.AdminConfig().getString(UUID + ".loginmsg")) + "!");
            }
            
            if (Ranks.isSeniorAdmin(player)) {
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".tag", "&dSenior Admin &8&m-&b");
                FOPMR_ConfigFiles.getPlayer().saveConfig();
                FOPMR_Utils.bcastMsg(ChatColor.AQUA + event.getPlayer().getName() + " is a " + ChatColor.LIGHT_PURPLE + ChatColor.ITALIC + "Senior Admin" + ChatColor.AQUA + FOPMR_Utils.color(FOPMR_ConfigEntry.AdminConfig().getString(UUID + ".loginmsg")) + "!");
            }
            
            if (Ranks.isOwner(player)) {
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".tag", "&9Owner &8&m-&b");
                FOPMR_ConfigFiles.getPlayer().saveConfig();
                FOPMR_Utils.bcastMsg(ChatColor.AQUA + event.getPlayer().getName() + " is the " + ChatColor.BLUE + ChatColor.ITALIC + "Owner" + ChatColor.AQUA + FOPMR_Utils.color(FOPMR_ConfigEntry.AdminConfig().getString(UUID + ".loginmsg")) + "!");
            }
            
            if (Ranks.isDeveloper(player)) {
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".tag", "&5Developer &8&m-&b");
                FOPMR_ConfigFiles.getPlayer().saveConfig();
                FOPMR_Utils.bcastMsg(ChatColor.AQUA + event.getPlayer().getName() + " is the " + ChatColor.DARK_PURPLE + ChatColor.ITALIC + "Developer" + ChatColor.AQUA + FOPMR_Utils.color(FOPMR_ConfigEntry.AdminConfig().getString(UUID + ".loginmsg")) + "!");
            }
        }
    }
    
}
