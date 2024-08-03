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

public class PetAdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        SPlayer sPlayer = Skyblock.get().getPlayers().get(Bukkit.getPlayer(commandSender.getName()).getUniqueId());
        if (!commandSender.hasPermission("skyblock.admin")) {
            commandSender.sendMessage(CC.color("&cYou do not have permission to use this command!"));
            return true;
        }
        if (strings.length == 0) {
            commandSender.sendMessage(CC.color("&7&m-------------------------------------"));
            commandSender.sendMessage(CC.color("&b&lPet Admin Command Help"));
            commandSender.sendMessage(CC.color("&7&m-------------------------------------"));
            commandSender.sendMessage(CC.color("&b/pet list &7- &fList all available pets"));
            commandSender.sendMessage(CC.color("&b/pet give <player> <pet> &7- &fGive a player a pet"));
            commandSender.sendMessage(CC.color("&b/pet remove <player> <pet> &7- &fRemove a pet from a player"));
            commandSender.sendMessage(CC.color("&7&m-------------------------------------"));
            return true;
        }
        if (strings[0].equalsIgnoreCase("list")) {
            for (Pet pet : Skyblock.get().getPets()) {
                commandSender.sendMessage(CC.color("&7- &b" + pet.getName()));
            }
            return true;
        }
        if (strings[0].equalsIgnoreCase("give")) {
            if (strings.length != 3) {
                commandSender.sendMessage(CC.color("&cUsage: /pet give <player> <pet>"));
                return true;
            }
            if (OceanSkyblock.get().getServer().getPlayer(strings[1]) == null) {
                commandSender.sendMessage(CC.color("&cPlayer not found!"));
                return true;
            }
            if (Skyblock.get().getPets().stream().noneMatch(pet -> pet.getName().equalsIgnoreCase(strings[2]))) {
                commandSender.sendMessage(CC.color("&cPet not found!"));
                return true;
            }
            sPlayer.getData().getPets().add(Skyblock.get().getPets().stream().filter(pet -> pet.getName().equalsIgnoreCase(strings[2])).findFirst().get());
            return true;
        }
        if (strings[0].equalsIgnoreCase("remove")) {
            if (strings.length != 3) {
                commandSender.sendMessage(CC.color("&cUsage: /pet remove <player> <pet>"));
                return true;
            }
            if (OceanSkyblock.get().getServer().getPlayer(strings[1]) == null) {
                commandSender.sendMessage(CC.color("&cPlayer not found!"));
                return true;
            }
            if (Skyblock.get().getPets().stream().noneMatch(pet -> pet.getName().equalsIgnoreCase(strings[2]))) {
                commandSender.sendMessage(CC.color("&cPet not found!"));
                return true;
            }
            sPlayer.getData().getPets().remove(Skyblock.get().getPets().stream().filter(pet -> pet.getName().equalsIgnoreCase(strings[2])).findFirst().get());
            return true;
        }
        return false;
    }
}
