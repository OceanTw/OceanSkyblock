package lol.oce.skyblock.commands;

import lol.oce.skyblock.Skyblock;
import lol.oce.skyblock.pets.Pet;
import lol.oce.skyblock.pets.PetManager;
import lol.oce.skyblock.players.SPlayer;
import lol.oce.skyblock.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

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
                int petLevel = sPlayer.getData().getPetLevel(pet);
                String rarityColor = pet.getRarity().getColor();
                commandSender.sendMessage(CC.color("&7- " + rarityColor + pet.getName() + " &7(Level: " + petLevel + ", Rarity: " + pet.getRarity().name() + ")"));
            }
            return true;
        }

        if (strings[0].equalsIgnoreCase("spawn")) {
            if (strings.length < 2) {
                commandSender.sendMessage(CC.color("&cUsage: /pet spawn <pet>"));
                return true;
            }

            if (sPlayer.getData().getSpawnedPet() != null) {
                commandSender.sendMessage(CC.color("&cYou already have a pet spawned!"));
                return true;
            }

            String petName = String.join(" ", Arrays.copyOfRange(strings, 1, strings.length));

            Pet pet = sPlayer.getData().getPets().stream()
                    .filter(p -> p.getName().equalsIgnoreCase(petName))
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