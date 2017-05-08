package net.savcode.fopmr;

import java.util.logging.Level;
import net.savcode.fopmr.blocking.BlockPlace;
import net.savcode.fopmr.commands.FOPMR_CommandRegister;
import net.savcode.fopmr.config.FOPMR_ConfigFiles;
import org.bukkit.plugin.java.JavaPlugin;

public class FrostedOPMod extends JavaPlugin {
    
    public static FrostedOPMod plugin;
    
    @Override
    public void onLoad() {
        plugin = this;
    }
    
    @Override
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void onEnable() {
        getLogger().log(Level.INFO, "---------------------");
        getLogger().log(Level.INFO, "FrostedOPMod: R version: {0} authors: {1}", new Object[] { 
            this.getDescription().getVersion(), this.getDescription().getAuthors() });
        getLogger().log(Level.INFO, "---------------------");
        // start stuff
        new FOPMR_ConfigFiles();
        new FOPMR_CommandRegister();
        new FOPMR_MainListener();
        new FOPMR_PlayerListener();
        new FOPMR_ChatListener();
        new BlockPlace();
    }
    
    
    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "FOPMR has been disabled!");
    }
}
