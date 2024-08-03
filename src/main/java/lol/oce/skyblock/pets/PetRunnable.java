package lol.oce.skyblock.pets;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PetRunnable extends BukkitRunnable {

    private final Player player;
    private final Entity pet;
    private final double minDistance;

    public PetRunnable(Player player, Entity pet, double minDistance) {
        this.player = player;
        this.pet = pet;
        this.minDistance = minDistance;
    }

    @Override
    public void run() {
        if (player.isOnline() && pet.isValid()) {
            Location playerLocation = player.getLocation();
            Location petLocation = pet.getLocation();

            double distance = playerLocation.distance(petLocation);

            if (distance < minDistance) {
                return;
            }

            double dx = playerLocation.getX() - petLocation.getX();
            double dy = playerLocation.getY() - petLocation.getY();
            double dz = playerLocation.getZ() - petLocation.getZ();

            double speed = 1; // Adjusted speed for smoother movement
            double step = speed / distance;

            double newX = petLocation.getX() + dx * step;
            double newY = petLocation.getY() + dy * step;
            double newZ = petLocation.getZ() + dz * step;

            pet.teleport(new Location(petLocation.getWorld(), newX, newY, newZ));
        } else {
            this.cancel();
        }
    }
}