package lol.oce.skyblock.commands;

import lol.oce.skyblock.abilities.AbilityManager;
import lol.oce.skyblock.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class AbilityCommand implements CommandExecutor {

    private final AbilityManager abilityManager = new AbilityManager();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(CC.color("&7&m-------------------------------------"));
            commandSender.sendMessage(CC.color("&b&lAbility Command Help"));
            commandSender.sendMessage(CC.color("&7&m-------------------------------------"));
            commandSender.sendMessage(CC.color("&b/ability create <name> <levelrequirements> &7- &fCreate an ability"));
            commandSender.sendMessage(CC.color("&b/ability addaction <name> <actions> &7- &fAdd actions to an ability"));
            commandSender.sendMessage(CC.color("&7&m-------------------------------------"));
            return true;
        }

        String action = strings[0];
        String name = strings.length > 1 ? strings[1] : "";

        switch (action.toLowerCase()) {
            case "create":
                if (strings.length < 3) {
                    commandSender.sendMessage(CC.color("&cUsage: /ability create <name> <levelrequirements>"));
                    return true;
                }
                Map<String, Integer> levelRequirements = parseRequirements(strings[2]);
                abilityManager.createAbility(name, levelRequirements);
                commandSender.sendMessage(CC.color("&aAbility created: " + name));
                break;
            case "addaction":
                if (strings.length < 3) {
                    commandSender.sendMessage(CC.color("&cUsage: /ability addaction <name> <actions>"));
                    return true;
                }
                Map<String, Integer> actions = parseActions(strings[2]);
                abilityManager.addAction(name, actions);
                commandSender.sendMessage(CC.color("&aActions added to ability: " + name));
                break;
            default:
                commandSender.sendMessage(CC.color("&cUnknown action: " + action));
                break;
        }

        return true;
    }

    private Map<String, Integer> parseRequirements(String input) {
        Map<String, Integer> requirements = new HashMap<>();
        String[] parts = input.split(",");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            requirements.put(keyValue[0].toUpperCase(), Integer.parseInt(keyValue[1]));
        }
        return requirements;
    }

    private Map<String, Integer> parseActions(String input) {
        Map<String, Integer> actions = new HashMap<>();
        String[] parts = input.split("\\|");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            actions.put(keyValue[0].toUpperCase(), Integer.parseInt(keyValue[1]));
        }
        return actions;
    }
}