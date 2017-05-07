package net.savcode.fopmr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import net.savcode.fopmr.blocking.BlockPlace;
import net.savcode.fopmr.config.FOPMR_ConfigEntry;
import net.savcode.fopmr.config.FOPMR_ConfigFiles;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class FrostedOPMod extends JavaPlugin implements Listener {
    
    private Connection connection;
    public static FrostedOPMod plugin;
    PluginDescriptionFile pdf = this.getDescription();
    
    @Override
    public void onLoad() {
        plugin = this;
    }
    
    @Override
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void onEnable() {
        getLogger().log(Level.INFO, "---------------------");
        getLogger().log(Level.INFO, "FrostedOPMod: R\nversion: {0}\nauthors: {1}", new Object[] { 
            pdf.getVersion(), pdf.getAuthors() });
        getLogger().log(Level.INFO, "---------------------");
        // start stuff
        new FOPMR_ConfigFiles();
        new BlockPlace();
        this.openConnection();
    }
    
    
    @Override
    public void onDisable() {
        
        // attempt to diable mysql
        try {
            if (connection == null || connection.isClosed())
                connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:" + 
                            FOPMR_ConfigEntry.MainConfig().getString("mysql.ip-port") + "/" + 
                            FOPMR_ConfigEntry.MainConfig().getString("mysql.database") + "?user=" +
                            FOPMR_ConfigEntry.MainConfig().getString("mysql.database-user") + "&password=" +
                            FOPMR_ConfigEntry.MainConfig().getString("mysql.database-password"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isInitialized() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
