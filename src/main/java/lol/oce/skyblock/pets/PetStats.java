package lol.oce.skyblock.pets;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetStats {

    private final int health;
    private final int defense;
    private final int strength;
    private final int critChance;
    private final int critDamage;
    private final int intelligence;
    private final int speed;
}
