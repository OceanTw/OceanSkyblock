package lol.oce.skyblock.commands;

import lol.oce.skyblock.OceanSkyblock;
import lol.oce.skyblock.listeners.PlayerListeners;
import lol.oce.skyblock.players.ItemStash;
import lol.oce.skyblock.players.SPlayer;
import lol.oce.skyblock.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PickupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // give the player the items in their itemstash
        Player player = (Player) commandSender;
        SPlayer sPlayer = OceanSkyblock.get().getPlayers().get(player.getUniqueId());
        if (ItemStash.getItems().isEmpty()) {
            player.sendMessage("You have no items to pick up!");
            return true;
        }
        for (int i = 0; i < ItemStash.getItems().size(); i++) {
            if (player.getInventory().firstEmpty() == -1) {
                return true;
            }
            player.getInventory().addItem(ItemStash.getItems().get(i));
        }
        return true;
    }
}
