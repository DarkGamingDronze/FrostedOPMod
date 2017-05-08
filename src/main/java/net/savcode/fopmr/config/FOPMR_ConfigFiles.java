package net.savcode.fopmr.config;

import net.savcode.fopmr.FrostedOPMod;

public class FOPMR_ConfigFiles {
    
    private static FOPMR_Config players;
    private static FOPMR_Config bans;
    private static FOPMR_Config mainconfig;
    private static FOPMR_Config admins;
    private static FOPMR_Config donators;
    
    public FOPMR_ConfigFiles() {
        bans = new FOPMR_Config(FrostedOPMod.plugin, "bans.yml");
        bans.saveDefaultConfig();
        players = new FOPMR_Config(FrostedOPMod.plugin, "players.yml");
        players.saveDefaultConfig();
        admins = new FOPMR_Config(FrostedOPMod.plugin, "admins.yml");
        admins.saveDefaultConfig();
        donators = new FOPMR_Config(FrostedOPMod.plugin, "donators.yml");
        donators.saveDefaultConfig();
        mainconfig = new FOPMR_Config(FrostedOPMod.plugin, "config.yml");
        mainconfig.saveDefaultConfig();
    }
    
    public static FOPMR_Config getPlayer() {
        return players;
    }
    
    public static FOPMR_Config getBans() {
        return bans;
    }
    
    public static FOPMR_Config getAdmins() {
        return admins;
    }
    
    public static FOPMR_Config getDonators() {
        return donators;
    }
    
    public static FOPMR_Config getMConfig() {
        return mainconfig;
    }
}
