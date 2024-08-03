package lol.oce.skyblock.commands;

import lol.oce.skyblock.items.CustomItemManager;
import lol.oce.skyblock.utils.CC;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class CustomItemCommand implements CommandExecutor {

    private final CustomItemManager customItemManager = new CustomItemManager();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(CC.color("&cOnly players can use this command!"));
            return true;
        }

        Player player = (Player) commandSender;

        if (strings.length == 0) {
            commandSender.sendMessage(CC.color("&7&m-------------------------------------"));
            commandSender.sendMessage(CC.color("&b&lCustom Item Command Help"));
            commandSender.sendMessage(CC.color("&7&m-------------------------------------"));
            commandSender.sendMessage(CC.color("&b/ci create <id> <rarity> <material> &7- &fCreate a custom item"));
            commandSender.sendMessage(CC.color("&b/ci delete <id> &7- &fDelete a custom item"));
            commandSender.sendMessage(CC.color("&b/ci addlore <id> <message> &7- &fAdd lore to a custom item"));
            commandSender.sendMessage(CC.color("&b/ci setdisplayname <id> <displayname> &7- &fSet display name of a custom item"));
            commandSender.sendMessage(CC.color("&b/ci give <id> &7- &fGive a custom item to yourself"));
            commandSender.sendMessage(CC.color("&7&m-------------------------------------"));
            return true;
        }

        String action = strings[0];
        String id = strings.length > 1 ? strings[1] : "";

        switch (action.toLowerCase()) {
            case "create":
                if (strings.length < 4) {
                    commandSender.sendMessage(CC.color("&cUsage: /ci create <id> <rarity> <material>"));
                    return true;
                }
                String rarity = strings[2];
                Material material;
                try {
                    material = Material.valueOf(strings[3].toUpperCase());
                } catch (IllegalArgumentException e) {
                    commandSender.sendMessage(CC.color("&cInvalid material: " + strings[3]));
                    return true;
                }
                customItemManager.createItem(id, rarity, material);
                commandSender.sendMessage(CC.color("&aCustom item created: " + id));
                break;
            case "delete":
                customItemManager.deleteItem(id);
                commandSender.sendMessage(CC.color("&aCustom item deleted: " + id));
                break;
            case "addlore":
                if (strings.length < 3) {
                    commandSender.sendMessage(CC.color("&cUsage: /ci addlore <id> <message>"));
                    return true;
                }
                String message = String.join(" ", Arrays.copyOfRange(strings, 2, strings.length));
                customItemManager.addLore(id, message);
                commandSender.sendMessage(CC.color("&aLore added to item: " + id));
                break;
            case "setdisplayname":
                if (strings.length < 3) {
                    commandSender.sendMessage(CC.color("&cUsage: /ci setdisplayname <id> <displayname>"));
                    return true;
                }
                String displayName = String.join(" ", Arrays.copyOfRange(strings, 2, strings.length));
                customItemManager.setDisplayName(id, displayName);
                commandSender.sendMessage(CC.color("&aDisplay name set for item: " + id));
                break;
            case "give":
                ItemStack item = customItemManager.getItem(id);
                if (item == null) {
                    commandSender.sendMessage(CC.color("&cItem not found: " + id));
                    return true;
                }
                player.getInventory().addItem(item);
                commandSender.sendMessage(CC.color("&aItem given: " + id));
                break;
            default:
                commandSender.sendMessage(CC.color("&cUnknown action: " + action));
                break;
        }

        return true;
    }
}