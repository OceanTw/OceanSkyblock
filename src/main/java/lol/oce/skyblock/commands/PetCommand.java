package lol.oce.skyblock.commands;

import lol.oce.skyblock.OceanSkyblock;
import lol.oce.skyblock.Skyblock;
import lol.oce.skyblock.pets.Pet;
import lol.oce.skyblock.players.SPlayer;
import lol.oce.skyblock.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // check if the command sender has the permission "skyblock.admin", if he does then send a help message containing "/pet list", "/pet give <player> <pet>", "/pet remove <player> <pet>"
        SPlayer sPlayer = Skyblock.get().getPlayers().get(Bukkit.getPlayer(commandSender.getName()).getUniqueId());
        if (!commandSender.hasPermission("skyblock.admin")) {
            commandSender.sendMessage(CC.color("&cYou do not have permission to use this command!"));
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
            if (sPlayer.getData().getPets().stream().noneMatch(pet -> pet.getName().equalsIgnoreCase(strings[1]))) {
                commandSender.sendMessage(CC.color("&cPet not found!"));
                return true;
            }
            sPlayer.getData().setSpawnedPet(sPlayer.getData().getPets().stream().filter(pet -> pet.getName().equalsIgnoreCase(strings[1])).findFirst().get());
        }
        return false;
    }
}
