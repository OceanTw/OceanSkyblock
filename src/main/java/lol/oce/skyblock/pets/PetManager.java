package lol.oce.skyblock.pets;

import lol.oce.skyblock.OceanSkyblock;
import lol.oce.skyblock.Skyblock;
import lol.oce.skyblock.players.SPlayer;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class PetManager {

    List<Pet> pets = Skyblock.get().getPets();

    public void load() {
        Configuration petsFile = OceanSkyblock.get().getConfigManager().getPetsFile().getConfiguration();
        for (String key : petsFile.getConfigurationSection("pets").getKeys(false)) {
            String name = petsFile.getString("pets." + key + ".name");
            String category = petsFile.getString("pets." + key + ".category");
            PetStats stats = new PetStats(
                    petsFile.getInt("pets." + key + ".stats.health"),
                    petsFile.getInt("pets." + key + ".stats.defense"),
                    petsFile.getInt("pets." + key + ".stats.strength"),
                    petsFile.getInt("pets." + key + ".stats.critChance"),
                    petsFile.getInt("pets." + key + ".stats.critDamage"),
                    petsFile.getInt("pets." + key + ".stats.intelligence"),
                    petsFile.getInt("pets." + key + ".stats.speed")
            );
            String skinOwner = petsFile.getString("pets." + key + ".skin-owner");

            pets.add(new Pet(name, category, stats, skinOwner));
        }
    }

    public void spawnPet(Player player, Pet pet) {
        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setHelmet(getSkull(pet.getSkinOwner()));

        SPlayer sPlayer = getSPlayer(player);
        sPlayer.getData().setSpawnedPet(pet);

        new PetRunnable(player, armorStand, 3.0).runTaskTimer(OceanSkyblock.get(), 0L, 3L); // Increased update frequency
    }

    private ItemStack getSkull(String owner) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(owner);
        skull.setItemMeta(meta);
        return skull;
    }

    private SPlayer getSPlayer(Player player) {
        return Skyblock.get().getPlayers().get(player.getUniqueId());
    }
}