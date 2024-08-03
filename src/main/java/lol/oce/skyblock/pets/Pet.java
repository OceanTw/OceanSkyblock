package lol.oce.skyblock.pets;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pet {

    private final String name;
    private final String category;
    private final PetStats stats;
    private final String skinOwner;
}