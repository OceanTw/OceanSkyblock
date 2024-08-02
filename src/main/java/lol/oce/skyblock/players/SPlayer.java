package lol.oce.skyblock.players;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SPlayer {

    private final String name;
    private ItemStash itemStash;
}
