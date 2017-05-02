package net.savcode.fopmr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class FrostedOPMod extends JavaPlugin implements Listener {
    
    private Connection connection;
    public static FrostedOPMod plugin;
    
    @Override
    public void onLoad() {
        plugin = this;
    }
    
    public void onEnable() {
        this.openConnection();
        getConfig().options().copyDefaults(true);
	saveConfig();
    }
    
    public void onDisable() {
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
                            plugin.getConfig().getString("mysql.ip-port") + "/" + 
                            plugin.getConfig().getString("mysql.database") + "?user=" +
                            plugin.getConfig().getString("mysql.database-user") + "&password=" +
                            plugin.getConfig().getString("mysql.database-password"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
