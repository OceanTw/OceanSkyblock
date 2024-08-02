package lol.oce.skyblock.listeners;

import lol.oce.skyblock.OceanSkyblock;
import lol.oce.skyblock.players.ItemStash;
import lol.oce.skyblock.players.SPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SPlayer sPlayer = new SPlayer(player.getName(), new ItemStash());
        OceanSkyblock.get().getPlayers().put(player.getUniqueId(), sPlayer);
    }

    @EventHandler
    public void onPlayerQuit(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        OceanSkyblock.get().getPlayers().remove(player.getUniqueId());
    }
}
