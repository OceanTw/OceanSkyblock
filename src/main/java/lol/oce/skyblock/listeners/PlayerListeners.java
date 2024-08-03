package lol.oce.skyblock.listeners;

import lol.oce.skyblock.OceanSkyblock;
import lol.oce.skyblock.Skyblock;
import lol.oce.skyblock.players.PlayerData;
import lol.oce.skyblock.players.SPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        SPlayer sPlayer = SPlayer.createDefault(player.getName());
        Skyblock.get().getPlayers().put(player.getUniqueId(), sPlayer);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Skyblock.get().getPlayers().remove(player.getUniqueId());
    }
}