package net.savcode.fopmr;

import java.util.Arrays;
import net.savcode.fopmr.config.FOPMR_ConfigEntry;
import net.savcode.fopmr.config.FOPMR_ConfigFiles;
import net.savcode.fopmr.ranks.Ranks;
import net.savcode.fopmr.utils.FOPMR_Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class FOPMR_MainListener implements Listener {
    
    FrostedOPMod plugin;
    
    public FOPMR_MainListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        
        if (FOPMR_ConfigEntry.PlayerConfig().getBoolean(event.getPlayer().getUniqueId().toString() + ".muted")) {
            event.getPlayer().sendMessage(ChatColor.GRAY + "You can't talk while muted!");
            event.setCancelled(true);
        }
        
        if (FOPMR_ConfigEntry.PlayerConfig().getString(event.getPlayer().getUniqueId().toString() + ".tag") == null) {
            String chatformat = event.getPlayer().getDisplayName() + ChatColor.GOLD + "»" + ChatColor.GRAY + event.getMessage().trim();
            event.setFormat(chatformat);
        }else {
            String chatformat = FOPMR_Utils.color(FOPMR_ConfigEntry.PlayerConfig().getString(event.getPlayer().getUniqueId().toString() + ".tag")) + 
                    " " + event.getPlayer().getDisplayName() + ChatColor.GOLD + "»" + ChatColor.GRAY + event.getMessage().trim();
            event.setFormat(chatformat);
        }
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String UUID = event.getPlayer().getUniqueId().toString();
        
        if (!FOPMR_ConfigEntry.PlayerConfig().contains(UUID)) {
            FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".name", event.getPlayer().getName().toLowerCase());
            FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".ip", event.getPlayer().getAddress().getHostString());
            FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".tag", null);
            FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".muted", false);
            FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".frozen", false);
            FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".cmdsblcked", false);
            FOPMR_ConfigFiles.getPlayer().reloadConfig();
        }else {
            FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".name", event.getPlayer().getName().toLowerCase());
            FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".ip", event.getPlayer().getAddress().getHostString());
        }
    }
    
    @EventHandler
    public void onAdminJoin(PlayerJoinEvent event, CommandSender sender) {
        String UUID = event.getPlayer().getUniqueId().toString();
        
        if (!FOPMR_ConfigEntry.AdminConfig().getBoolean(UUID + ".isactive")) {
            if (event.getPlayer().getAddress().getHostString() != FOPMR_ConfigEntry.AdminConfig().getString(UUID + ".ip")) {
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".frozen", true);
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".cmdsblcked", true);
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".tag", "&eImposter &8&m-");
                FOPMR_Utils.bcastMsg(ChatColor.AQUA + event.getPlayer().getName() + " is a " + ChatColor.YELLOW + "Imposter" + ChatColor.AQUA + "!");
                FOPMR_ConfigFiles.getPlayer().reloadConfig();
            }else {
                // make sure they are not frozen or cmdsblocked
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".frozen", false);
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".cmdsblcked", false);
                FOPMR_ConfigFiles.getPlayer().reloadConfig();
            }
        }
        
        if (FOPMR_ConfigEntry.AdminConfig().getBoolean(UUID + ".isactive")) {
            
            if (Ranks.isSuperAdmin(sender)) {
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".tag", "&6Super Admin &8&m-");
                FOPMR_Utils.bcastMsg(ChatColor.AQUA + event.getPlayer().getName() + " is a " + ChatColor.GOLD + ChatColor.ITALIC + "Super Admin" + ChatColor.AQUA + FOPMR_Utils.color(FOPMR_ConfigEntry.AdminConfig().getString(UUID + ".loginmsg")) + "!");
            }
            
            if (Ranks.isTelnetAdmin(sender)) {
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".tag", "&3Telnet Admin &8&m-");
                FOPMR_ConfigFiles.getPlayer().saveConfig();
                FOPMR_Utils.bcastMsg(ChatColor.AQUA + event.getPlayer().getName() + " is a " + ChatColor.GREEN + ChatColor.ITALIC + "Telnet Admin" + ChatColor.AQUA + FOPMR_Utils.color(FOPMR_ConfigEntry.AdminConfig().getString(UUID + ".loginmsg")) + "!");
            }
            
            if (Ranks.isSeniorAdmin(sender)) {
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".tag", "&dSenior Admin &8&m-");
                FOPMR_ConfigFiles.getPlayer().saveConfig();
                FOPMR_Utils.bcastMsg(ChatColor.AQUA + event.getPlayer().getName() + " is a " + ChatColor.LIGHT_PURPLE + ChatColor.ITALIC + "Senior Admin" + ChatColor.AQUA + FOPMR_Utils.color(FOPMR_ConfigEntry.AdminConfig().getString(UUID + ".loginmsg")) + "!");
            }
            
            if (Ranks.isOwner(sender)) {
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".tag", "&9Owner &8&m-");
                FOPMR_ConfigFiles.getPlayer().saveConfig();
                FOPMR_Utils.bcastMsg(ChatColor.AQUA + event.getPlayer().getName() + " is the " + ChatColor.BLUE + ChatColor.ITALIC + "Owner" + ChatColor.AQUA + FOPMR_Utils.color(FOPMR_ConfigEntry.AdminConfig().getString(UUID + ".loginmsg")) + "!");
            }
            
            if (Ranks.isDeveloper(sender)) {
                FOPMR_ConfigEntry.PlayerConfig().set(UUID + ".tag", "&5Developer &8&m-");
                FOPMR_ConfigFiles.getPlayer().saveConfig();
                FOPMR_Utils.bcastMsg(ChatColor.AQUA + event.getPlayer().getName() + " is the " + ChatColor.DARK_PURPLE + ChatColor.ITALIC + "Developer" + ChatColor.AQUA + FOPMR_Utils.color(FOPMR_ConfigEntry.AdminConfig().getString(UUID + ".loginmsg")) + "!");
            }
        }
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        
        if(FOPMR_ConfigEntry.PlayerConfig().getBoolean(event.getPlayer().getUniqueId().toString() + ".frozen")) {
            event.getPlayer().sendMessage(ChatColor.GRAY + "You can't move while frozen!");
            event.setCancelled(true);
            event.getPlayer().teleport(event.getPlayer());
        }
    }
    
    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        if(event.getMessage().split(" ")[0].contains(":")) {
            event.getPlayer().sendMessage(ChatColor.GRAY + "You cannot send plugin specific commands.");
            event.setCancelled(true);
        }
        
        if (FOPMR_ConfigEntry.PlayerConfig().getBoolean(event.getPlayer().getUniqueId().toString() + ".cmdsblcked")) {
            event.getPlayer().sendMessage(ChatColor.GRAY + "Your commands are currently blocked!");
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerPing(ServerListPingEvent event, Player player) {
        
        if(Arrays.asList(Bukkit.getOnlinePlayers()).size() >= Bukkit.getMaxPlayers()) {
            event.setMotd(FOPMR_Utils.color(FOPMR_ConfigEntry.MainConfig().getString("server.motd-full-server").replace("%servername%", FOPMR_ConfigEntry.MainConfig().getString("server.name"))));
        }
        
        if (!FOPMR_ConfigEntry.PlayerConfig().contains(player.getUniqueId().toString())) {
            String nj_motd_line1 = FOPMR_ConfigEntry.MainConfig().getString("server.nj-motd-line1");
            String nj_motd_line2 = FOPMR_ConfigEntry.MainConfig().getString("server.nj-motd-line2").replace("%playername%", player.getName());
            String NJ_Motd = nj_motd_line1 + " \n" + nj_motd_line2;
            NJ_Motd = FOPMR_Utils.color(NJ_Motd);
            event.setMotd(NJ_Motd.trim());
        } else {
            String lineone = FOPMR_ConfigEntry.MainConfig().getString("server.motd-line-1");
            String linetwo = FOPMR_ConfigEntry.MainConfig().getString("server.motd-line-2").replace("%playername%", player.getName()).replace("%servername%", FOPMR_ConfigEntry.MainConfig().getString("server.name"));
            String Motd = lineone + " \n" + linetwo;
            Motd = FOPMR_Utils.color(Motd);
            event.setMotd(Motd.trim());
        }
    }
}
