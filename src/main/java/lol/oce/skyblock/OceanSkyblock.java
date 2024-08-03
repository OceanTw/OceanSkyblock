package lol.oce.skyblock;

import lol.oce.skyblock.commands.PetAdminCommand;
import lol.oce.skyblock.commands.PetCommand;
import lol.oce.skyblock.configs.ConfigManager;
import lol.oce.skyblock.listeners.DamageListeners;
import lol.oce.skyblock.listeners.PlayerListeners;
import lol.oce.skyblock.pets.PetManager;
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
    private PetManager petManager;


    @Override
    public void onEnable() {

        instance = this;
        this.configManager = new ConfigManager();
        this.petManager = new PetManager();

        this.configManager.load();
        this.petManager.load();

        Bukkit.getPluginManager().registerEvents(new DamageListeners(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
        Bukkit.getPluginCommand("pet").setExecutor(new PetCommand());
        Bukkit.getPluginCommand("petadmin").setExecutor(new PetAdminCommand());
    }

    @Override
    public void onDisable() {

    }

    public static OceanSkyblock get() {
        return instance;
    }
}
