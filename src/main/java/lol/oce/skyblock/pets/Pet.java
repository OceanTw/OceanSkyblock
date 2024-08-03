package lol.oce.skyblock.pets;

import lol.oce.skyblock.rarity.Rarity;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pet {

    private final String key;
    private final String name;
    private final String category;
    private final PetStats stats;
    private final String skinOwner;
    private final Rarity rarity;
}