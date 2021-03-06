package net.savcode.fopmr.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import net.savcode.fopmr.FrostedOPMod;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FOPMR_Config {
    
    private final String fileName;
    private final JavaPlugin plugin;

    private File configFile;
    private FileConfiguration fileConfiguration;
    
    public FOPMR_Config(FrostedOPMod plugin, String fileName) {
        
        if(plugin == null) {
            throw new IllegalArgumentException("plugin cannot be null");
        }
        
        this.plugin = plugin;
        this.fileName = fileName;
        File dataFolder = plugin.getDataFolder();
        
        if(dataFolder == null) {
            throw new IllegalStateException();
        }
        
        this.configFile = new File(plugin.getDataFolder(), fileName);
    }

    public void reloadConfig() {
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);
        InputStream defConfigStream = plugin.getResource(fileName);
        
        if(defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            fileConfiguration.setDefaults(defConfig);
        }
    }

    public FileConfiguration getConfig() {
        
        if(fileConfiguration == null) {
            this.reloadConfig();
        }
        
        return fileConfiguration;
    }

    public void saveConfig() {
        if(fileConfiguration == null || configFile == null) {
        }else {
            try {
                getConfig().save(configFile);
            } catch(IOException ex) {
                plugin.getLogger().log(Level.WARNING, "Couldn't save config to " + configFile + ex);
            }
        }
    }

    public void saveDefaultConfig() {
        
        if(!configFile.exists()) {
            this.plugin.saveResource(fileName, false);
        }
    }
}
