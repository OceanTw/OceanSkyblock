package lol.oce.skyblock.abilities;

import java.util.HashMap;
import java.util.Map;

public class Ability {

    private final String name;
    private final Map<String, Integer> levelRequirements;
    private final Map<String, Integer> actions;

    public Ability(String name, Map<String, Integer> levelRequirements) {
        this.name = name;
        this.levelRequirements = levelRequirements;
        this.actions = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getLevelRequirements() {
        return levelRequirements;
    }

    public Map<String, Integer> getActions() {
        return actions;
    }
}