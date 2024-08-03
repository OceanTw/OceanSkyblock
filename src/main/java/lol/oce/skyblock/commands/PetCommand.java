package lol.oce.skyblock.commands;

import lol.oce.skyblock.Skyblock;
import lol.oce.skyblock.pets.Pet;
import lol.oce.skyblock.pets.PetManager;
import lol.oce.skyblock.players.SPlayer;
import lol.oce.skyblock.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PetCommand implements CommandExecutor {

    private final PetManager petManager = new PetManager();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(CC.color("&cOnly players can use this command!"));
            return true;
        }

        Player player = (Player) commandSender;
        SPlayer sPlayer = Skyblock.get().getPlayers().get(player.getUniqueId());

        if (sPlayer == null) {
            commandSender.sendMessage(CC.color("&cPlayer data not found!"));
            return true;
        }

        if (strings.length == 0) {
            commandSender.sendMessage(CC.color("&7&m-------------------------------------"));
            commandSender.sendMessage(CC.color("&b&lPet Command Help"));
            commandSender.sendMessage(CC.color("&7&m-------------------------------------"));
            commandSender.sendMessage(CC.color("&b/pet list &7- &fList all pets you own"));
            commandSender.sendMessage(CC.color("&b/pet spawn <pet> &7- &fSpawn one of your pets"));
            commandSender.sendMessage(CC.color("&7&m-------------------------------------"));
            return true;
        }

        if (strings[0].equalsIgnoreCase("list")) {
            for (Pet pet : sPlayer.getData().getPets()) {
                commandSender.sendMessage(CC.color("&7- &b" + pet.getName()));
            }
            return true;
        }

        if (strings[0].equalsIgnoreCase("spawn")) {
            if (strings.length != 2) {
                commandSender.sendMessage(CC.color("&cUsage: /pet spawn <pet>"));
                return true;
            }

            Pet pet = sPlayer.getData().getPets().stream()
                    .filter(p -> p.getName().equalsIgnoreCase(strings[1]))
                    .findFirst()
                    .orElse(null);

            if (pet == null) {
                commandSender.sendMessage(CC.color("&cPet not found!"));
                return true;
            }

            petManager.spawnPet(player, pet);
            return true;
        }

        return false;
    }
}