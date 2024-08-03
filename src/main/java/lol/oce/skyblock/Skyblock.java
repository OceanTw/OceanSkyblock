package lol.oce.skyblock;

import lol.oce.skyblock.pets.Pet;
import lol.oce.skyblock.players.SPlayer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
public class Skyblock {
    private static Skyblock instance;

    public List<Pet> pets = new ArrayList<>();
    public HashMap<UUID, SPlayer> players = new HashMap<>();

    public static Skyblock get() {
        if (instance == null) {
            instance = new Skyblock();
        }
        return instance;
    }
}
