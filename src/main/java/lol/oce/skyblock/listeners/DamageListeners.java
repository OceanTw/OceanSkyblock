package lol.oce.skyblock.listeners;

import lol.oce.skyblock.Skyblock;
import lol.oce.skyblock.players.SPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DamageListeners implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        if (event.getEntity().getKiller() == null) return;
        Player player = event.getEntity().getKiller();
        if (player.getInventory().firstEmpty() == -1) {
            SPlayer sPlayer = Skyblock.get().getPlayers().get(player.getUniqueId());
            event.getDrops().forEach(drop -> sPlayer.getData().getItemStash().add(drop));
        } else {
            event.getDrops().forEach(drop -> player.getInventory().addItem(drop));
        }
        event.getDrops().clear();
    }
}
