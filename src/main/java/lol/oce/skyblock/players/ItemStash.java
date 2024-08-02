package lol.oce.skyblock.players;

import lol.oce.skyblock.utils.CC;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ItemStash {

    @Getter
    public static List<ItemStack> items = new ArrayList<>();

    public void addItem(ItemStack item) {
        items.add(item);
    }

    public static void notifyPlayer(Player player) {
        player.sendMessage(CC.color("&eYou have &6" + items.size() + " items in your stash."));
        TextComponent message = new TextComponent(CC.color("&6&lClick here to pick up your stashes."));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/pickup"));
        player.spigot().sendMessage(message);
    }

}
