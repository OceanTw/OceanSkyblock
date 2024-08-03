package lol.oce.skyblock.players;

import lol.oce.skyblock.pets.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PlayerData {

    private final List<ItemStack> itemStash;
    private final Stats stats;
    private final List<Pet> pets;
}
