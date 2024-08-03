package lol.oce.skyblock.listeners;

import lol.oce.skyblock.OceanSkyblock;
import lol.oce.skyblock.Skyblock;
import lol.oce.skyblock.pets.Pet;
import lol.oce.skyblock.pets.PetManager;
import lol.oce.skyblock.players.SPlayer;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerListeners implements Listener {

    private final PetManager petManager = OceanSkyblock.get().getPetManager();
    private final Map<UUID, Location> petLocations = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        SPlayer sPlayer = SPlayer.createDefault(player.getName());
        Skyblock.get().getPlayers().put(player.getUniqueId(), sPlayer);

        Location petLocation = petLocations.remove(player.getUniqueId());
        if (petLocation != null) {
            Pet pet = sPlayer.getData().getPets().stream()
                    .findFirst()
                    .orElse(null);
            if (pet != null) {
                petManager.spawnPetAtLocation(player, pet, petLocation);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        SPlayer sPlayer = petManager.getSPlayer(player);
        Pet pet = sPlayer.getData().getSpawnedPet();
        if (pet != null) {
            petLocations.put(player.getUniqueId(), player.getLocation());
            sPlayer.getData().setSpawnedPet(null);
            petManager.removePet(pet);
        }
        Skyblock.get().getPlayers().remove(player.getUniqueId());
    }

    @EventHandler
    public void OnInteractAtEntity(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.ARMOR_STAND) {
            SPlayer sPlayer = Skyblock.get().getPlayers().get(e.getPlayer().getUniqueId());
            e.setCancelled(true);
        }
    }
}