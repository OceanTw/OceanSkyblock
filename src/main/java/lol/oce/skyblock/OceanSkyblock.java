package lol.oce.skyblock;

import lol.oce.skyblock.configs.ConfigManager;
import lol.oce.skyblock.players.SPlayer;
import lol.oce.skyblock.utils.CC;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class OceanSkyblock extends JavaPlugin {

    private static OceanSkyblock instance;
    private ConfigManager configManager;



    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.configManager = new ConfigManager();

        this.configManager.load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static OceanSkyblock get() {
        return instance;
    }
}
