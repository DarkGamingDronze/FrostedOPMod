package net.savcode.fopmr.config;

import org.bukkit.configuration.file.FileConfiguration;

public class FOPMR_ConfigEntry {
    
    public FOPMR_ConfigEntry() {
    }
    
    public static final FileConfiguration PlayerConfig() {
        return FOPMR_ConfigFiles.getPlayer().getConfig();
    }
    
    public static final FileConfiguration BansConfig() {
        return FOPMR_ConfigFiles.getBans().getConfig();
    }
    public static final FileConfiguration MainConfig() {
        return FOPMR_ConfigFiles.getMConfig().getConfig();
    }
    
    public static final FileConfiguration AdminConfig() {
        return FOPMR_ConfigFiles.getAdmins().getConfig();
    }
    
    public static final FileConfiguration DonatorConfig() {
        return FOPMR_ConfigFiles.getDonators().getConfig();
    }
}
