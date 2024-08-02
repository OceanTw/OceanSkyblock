package lol.oce.skyblock.pets;

import lol.oce.skyblock.OceanSkyblock;
import lol.oce.skyblock.configs.ConfigManager;
import org.bukkit.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public class PetManager {

    List<Pets> pets = new ArrayList<>();

    public void load() {
        // Load pets from config
        for (String key : OceanSkyblock.get().getConfigManager().getPetsFile().getConfiguration().getKeys(false)) {
            Configuration petsFile = OceanSkyblock.get().getConfigManager().getPetsFile().getConfiguration();
            String name = petsFile.getString(key + ".name");
            String category = petsFile.getString(key + ".category");
            PetStats stats = new PetStats(
                    petsFile.getInt(key + ".stats.health"),
                    petsFile.getInt(key + ".stats.defense"),
                    petsFile.getInt(key + ".stats.strength"),
                    petsFile.getInt(key + ".stats.critChance"),
                    petsFile.getInt(key + ".stats.critDamage"),
                    petsFile.getInt(key + ".stats.intelligence"),
                    petsFile.getInt(key + ".stats.speed")
            );
            String skinOwner = petsFile.getString(key + ".skinOwner");

            pets.add(new Pets(name, category, stats, skinOwner));
        }
    }
}
