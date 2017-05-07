package net.savcode.fopmr;

import java.util.Arrays;
import net.savcode.fopmr.config.FOPMR_ConfigEntry;
import net.savcode.fopmr.utils.FOPMR_Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class FOPMR_MainListener implements Listener {
    
    FrostedOPMod plugin;
    
    public FOPMR_MainListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
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
