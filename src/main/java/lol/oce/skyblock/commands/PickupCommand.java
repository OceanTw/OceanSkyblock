package lol.oce.skyblock.commands;

import lol.oce.skyblock.OceanSkyblock;
import lol.oce.skyblock.Skyblock;
import lol.oce.skyblock.players.SPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PickupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // give the player the items in their itemstash
        Player player = (Player) commandSender;
        SPlayer sPlayer = Skyblock.get().getPlayers().get(player.getUniqueId());
        if (sPlayer.getData().getItemStash().isEmpty()) {
            player.sendMessage("You have no items to pick up!");
            return true;
        }
        for (int i = 0; i < sPlayer.getData().getItemStash().size(); i++) {
            if (player.getInventory().firstEmpty() == -1) {
                return true;
            }
            player.getInventory().addItem(sPlayer.getData().getItemStash().get(i));
        }
        return true;
    }
}
