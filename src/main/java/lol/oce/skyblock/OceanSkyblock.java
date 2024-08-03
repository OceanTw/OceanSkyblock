package lol.oce.skyblock;

import lol.oce.skyblock.commands.AbilityCommand;
import lol.oce.skyblock.commands.CustomItemCommand;
import lol.oce.skyblock.commands.PetAdminCommand;
import lol.oce.skyblock.commands.PetCommand;
import lol.oce.skyblock.configs.ConfigManager;
import lol.oce.skyblock.listeners.DamageListeners;
import lol.oce.skyblock.listeners.PlayerListeners;
import lol.oce.skyblock.pets.PetManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

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
        Bukkit.getPluginCommand("customitem").setExecutor(new CustomItemCommand());
        Bukkit.getPluginCommand("ability").setExecutor(new AbilityCommand());

    }

    @Override
    public void onDisable() {

    }

    public static OceanSkyblock get() {
        return instance;
    }
}
