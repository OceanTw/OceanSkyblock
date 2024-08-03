package lol.oce.skyblock.listeners;

import lol.oce.skyblock.OceanSkyblock;
import lol.oce.skyblock.Skyblock;
import lol.oce.skyblock.players.PlayerData;
import lol.oce.skyblock.players.SPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // TODO: Check if the player exists in the database and load their data from it, otherwise create default
        SPlayer sPlayer = SPlayer.createDefault(player.getName());
        Skyblock.get().getPlayers().put(player.getUniqueId(), sPlayer);
    }

    @EventHandler
    public void onPlayerQuit(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Skyblock.get().getPlayers().remove(player.getUniqueId());
    }
}
