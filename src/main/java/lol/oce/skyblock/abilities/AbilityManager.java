package lol.oce.skyblock.abilities;

import java.util.HashMap;
import java.util.Map;

public class AbilityManager {

    private final Map<String, Ability> abilities = new HashMap<>();

    public void createAbility(String name, Map<String, Integer> levelRequirements) {
        Ability ability = new Ability(name, levelRequirements);
        abilities.put(name, ability);
    }

    public void addAction(String name, Map<String, Integer> actions) {
        Ability ability = abilities.get(name);
        if (ability != null) {
            ability.getActions().putAll(actions);
        }
    }

    public Ability getAbility(String name) {
        return abilities.get(name);
    }
}