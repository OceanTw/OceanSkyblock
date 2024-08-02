package lol.oce.skyblock;

import lol.oce.skyblock.configs.ConfigManager;
import lol.oce.skyblock.players.ItemStash;
import lol.oce.skyblock.players.SPlayer;
import lol.oce.skyblock.utils.CC;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
public class OceanSkyblock extends JavaPlugin {

    private static OceanSkyblock instance;
    private ConfigManager configManager;

    @Getter
    public HashMap<UUID, SPlayer> players = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.configManager = new ConfigManager();

        this.configManager.load();

        runEverySecond();
        runEveryMinute();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static OceanSkyblock get() {
        return instance;
    }

    // make a runnable that runs every second
    public void runEverySecond() {
        getServer().getScheduler().runTaskTimer(this, () -> {
        }, 0, 20);
    }

    // make a runnable that runs every minute
    public void runEveryMinute() {
        getServer().getScheduler().runTaskTimer(this, () -> {
            // notify all players of their stashes
            for (SPlayer sPlayer : players.values()) {
                ItemStash.notifyPlayer(Bukkit.getPlayer(sPlayer.getName()));
            }

            // notify players with their inventory full
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getInventory().firstEmpty() == -1) {
                    player.sendTitle(CC.color("&cYour inventory is full!"), "");
                }
            }
        }, 0, 1200);
    }
}
