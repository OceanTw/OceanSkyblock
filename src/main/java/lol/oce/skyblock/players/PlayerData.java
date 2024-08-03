package lol.oce.skyblock.players;

import lol.oce.skyblock.pets.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class PlayerData {

    private final List<ItemStack> itemStash;
    private final Stats stats;
    private final List<Pet> pets;
    private Pet spawnedPet;
    private final Map<Pet, Integer> petLevels = new HashMap<>();

    public void levelUpPet(Pet pet) {
        petLevels.put(pet, petLevels.getOrDefault(pet, 1) + 1);
    }

    public int getPetLevel(Pet pet) {
        return petLevels.getOrDefault(pet, 1);
    }
}