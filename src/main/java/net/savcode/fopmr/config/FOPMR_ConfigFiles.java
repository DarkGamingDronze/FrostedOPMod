package net.savcode.fopmr.config;

import net.savcode.fopmr.FrostedOPMod;

public class FOPMR_ConfigFiles {
    
    private static FOPMR_Config players;
    private static FOPMR_Config mainconfig;
    
    public FOPMR_ConfigFiles() {
        players = new FOPMR_Config(FrostedOPMod.plugin, "players.yml");
        players.saveDefaultConfig();
        mainconfig = new FOPMR_Config(FrostedOPMod.plugin, "config.yml");
        mainconfig.saveDefaultConfig();
    }
    
    public static FOPMR_Config getPlayer() {
        return players;
    }
    
    public static FOPMR_Config getMConfig() {
        return mainconfig;
    }
}
