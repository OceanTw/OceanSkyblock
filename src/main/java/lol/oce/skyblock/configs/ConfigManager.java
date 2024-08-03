package lol.oce.skyblock.configs;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import lol.oce.skyblock.OceanSkyblock;
import lol.oce.skyblock.utils.ConfigFile;
import lombok.Getter;

@Getter
public class ConfigManager {

    private final OceanSkyblock plugin;
    private ConfigFile settingsFile;
    private ConfigFile localeFile;
    private ConfigFile scoreboardFile;
    private ConfigFile menuFile;
    private ConfigFile reforgesFile;
    private ConfigFile petsFile;
    private ConfigFile itemsFile;
    private ConfigFile dataFile;


    public ConfigManager() {
        this.plugin = OceanSkyblock.get();
    }

    public void load() {
        this.settingsFile = new ConfigFile("settings");
        this.localeFile = new ConfigFile("locale");
        this.scoreboardFile = new ConfigFile("scoreboard");
        this.menuFile = new ConfigFile("menu");
        this.reforgesFile = new ConfigFile("reforges");
        this.petsFile = new ConfigFile("pets");
        this.itemsFile = new ConfigFile("items");
        this.dataFile = new ConfigFile("data");
    }
}
