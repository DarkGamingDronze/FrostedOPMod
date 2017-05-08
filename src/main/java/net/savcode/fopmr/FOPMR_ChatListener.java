package net.savcode.fopmr;

import net.savcode.fopmr.config.FOPMR_ConfigEntry;
import net.savcode.fopmr.utils.FOPMR_Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class FOPMR_ChatListener implements Listener {
    
    FrostedOPMod plugin;
    
    @SuppressWarnings("LeakingThisInConstructor")
    public FOPMR_ChatListener() {
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
    
}
