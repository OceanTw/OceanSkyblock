package lol.oce.skyblock.players;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class SPlayer {

    private final String name;
    private final PlayerData data;

    public static SPlayer createDefault(String name) {
        return new SPlayer(name, new PlayerData(new ArrayList<>(), new Stats(100, 100, 0, 30, 50, 0, 100), new ArrayList<>()));
    }
}
