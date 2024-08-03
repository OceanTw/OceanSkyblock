package lol.oce.skyblock.pets;

import lol.oce.skyblock.OceanSkyblock;
import lol.oce.skyblock.Skyblock;
import lol.oce.skyblock.players.SPlayer;
import lol.oce.skyblock.rarity.Rarity;
import lol.oce.skyblock.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class PetManager {

    private final List<Pet> pets = Skyblock.get().getPets();

    public void load() {
        Configuration petsFile = OceanSkyblock.get().getConfigManager().getPetsFile().getConfiguration();
        for (String key : petsFile.getConfigurationSection("pets").getKeys(false)) {
            String name = petsFile.getString("pets." + key + ".name");
            String category = petsFile.getString("pets." + key + ".category");
            Rarity rarity = Rarity.valueOf(petsFile.getString("pets." + key + ".rarity").toUpperCase());
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

            pets.add(new Pet(key, name, category, stats, skinOwner, rarity));
        }
    }

    public void spawnPet(Player player, Pet pet) {
        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setHelmet(getSkull(pet.getSkinOwner()));
        armorStand.setBasePlate(false);
        armorStand.setArms(false);
        armorStand.setSmall(true);
        armorStand.setRemoveWhenFarAway(false);

        SPlayer sPlayer = getSPlayer(player);
        sPlayer.getData().setSpawnedPet(pet);

        int petLevel = sPlayer.getData().getPetLevel(pet);


        new PetRunnable(player, armorStand, 3.0).runTaskTimer(OceanSkyblock.get(), 0L, 3L);

        ArmorStand nametagStand = createNametag(player, pet, armorStand);
        updateNametagPosition(armorStand, nametagStand);


        new BukkitRunnable() {
            @Override
            public void run() {
                if (armorStand.isDead() || player.isDead()) {
                    this.cancel();
                    return;
                }
                facePlayer(armorStand, player);
            }
        }.runTaskTimer(OceanSkyblock.get(), 0L, 1L);
    }

    public void spawnPetAtLocation(Player player, Pet pet, Location location) {
        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setHelmet(getSkull(pet.getSkinOwner()));
        armorStand.setBasePlate(false);
        armorStand.setArms(false);
        armorStand.setSmall(true);
        armorStand.setRemoveWhenFarAway(false);

        SPlayer sPlayer = getSPlayer(player);
        sPlayer.getData().setSpawnedPet(pet);

        int petLevel = sPlayer.getData().getPetLevel(pet);


        new PetRunnable(player, armorStand, 3.0).runTaskTimer(OceanSkyblock.get(), 0L, 3L);

        ArmorStand nametagStand = createNametag(player, pet, armorStand);
        updateNametagPosition(armorStand, nametagStand);


        new BukkitRunnable() {
            @Override
            public void run() {
                if (armorStand.isDead() || player.isDead()) {
                    this.cancel();
                    return;
                }
                facePlayer(armorStand, player);
            }
        }.runTaskTimer(OceanSkyblock.get(), 0L, 1L);
    }

    public void removePet(Pet pet) {

    }

    private ItemStack getSkull(String owner) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(owner);
        skull.setItemMeta(meta);
        return skull;
    }

    public SPlayer getSPlayer(Player player) {
        return Skyblock.get().getPlayers().get(player.getUniqueId());
    }

    private ArmorStand createNametag(Player player, Pet pet, ArmorStand petArmorStand) {
        Configuration locale = OceanSkyblock.get().getConfigManager().getLocaleFile().getConfiguration();
        String nametagFormat = locale.getString("pets.nametag-format");

        String nametag = nametagFormat.replace("{name}", pet.getRarity().getColor() + pet.getName())
                .replace("{level}", String.valueOf(getSPlayer(player).getData().getPetLevel(pet)))
                .replace("{player}", player.getName());

        nametag = CC.color(nametag);

        ArmorStand nametagStand = (ArmorStand) petArmorStand.getWorld().spawnEntity(petArmorStand.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
        nametagStand.setCustomName(nametag);
        nametagStand.setCustomNameVisible(true);
        nametagStand.setVisible(false);
        nametagStand.setGravity(false);
        nametagStand.setSmall(true);
        nametagStand.setMarker(true);

        return nametagStand;
    }

    private void updateNametagPosition(ArmorStand petArmorStand, ArmorStand nametagStand) {
        Bukkit.getScheduler().runTaskTimer(OceanSkyblock.get(), () -> {
            if (petArmorStand.isDead() || nametagStand.isDead()) {
                nametagStand.remove();
                return;
            }
            nametagStand.teleport(petArmorStand.getLocation().add(0, 1, 0));
        }, 0L, 1L);
    }

    private void facePlayer(ArmorStand armorStand, Player player) {
        double deltaX = player.getLocation().getX() - armorStand.getLocation().getX();
        double deltaZ = player.getLocation().getZ() - armorStand.getLocation().getZ();
        float yaw = (float) Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90;
        Location location = armorStand.getLocation();
        location.setYaw(yaw);
        armorStand.teleport(location);
    }
}