package net.savcode.fopmr;

import java.util.Arrays;
import net.savcode.fopmr.config.FOPMR_ConfigEntry;
import net.savcode.fopmr.ranks.Rank;
import net.savcode.fopmr.utils.FOPMR_Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class FOPMR_MainListener implements Listener {
    
    FrostedOPMod plugin;
    
    @SuppressWarnings("LeakingThisInConstructor")
    public FOPMR_MainListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }
    
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)
    {
        Player player = event.getPlayer();
        
        if (FOPMR_Bans.isBanned(player) && !Rank.isAdmin(player)) {
            event.disallow(Result.KICK_BANNED, ChatColor.GOLD + "You are currently banned from this server! \nAppeal at: https://forums.frostedop.net"
            + ChatColor.RED + "\nReason: " + ChatColor.GOLD + FOPMR_Bans.getBanReason(player) + ChatColor.RED 
                    + "\nBanned by: " + ChatColor.GOLD + FOPMR_Bans.getBanner(player));
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
            event.setCancelled(true);
        }
        
        if (FOPMR_ConfigEntry.AdminConfig().getBoolean(event.getPlayer().getUniqueId().toString() + ".cmdspy")) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(event.getPlayer() + ": " + event.getMessage().toLowerCase());
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerPing(ServerListPingEvent event) {
        
        if(Arrays.asList(Bukkit.getOnlinePlayers()).size() >= Bukkit.getMaxPlayers()) {
            event.setMotd(FOPMR_Utils.color(FOPMR_ConfigEntry.MainConfig().getString("server.motd-full-server").replace("%servername%", FOPMR_ConfigEntry.MainConfig().getString("server.name"))));
        }
        
        String lineone = FOPMR_ConfigEntry.MainConfig().getString("server.motd-line-1");
        String linetwo = FOPMR_ConfigEntry.MainConfig().getString("server.motd-line-2").replace("%servername%", FOPMR_ConfigEntry.MainConfig().getString("server.name"));
        String Motd = lineone + " \n" + linetwo;
        Motd = FOPMR_Utils.color(Motd);
        event.setMotd(Motd.trim());
    }
}
