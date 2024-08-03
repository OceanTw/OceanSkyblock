package lol.oce.skyblock.items;

import lol.oce.skyblock.OceanSkyblock;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomItemManager {

    private final Map<String, ItemStack> customItems = new HashMap<>();
    private final File itemsFile;
    private final FileConfiguration itemsConfig;

    public CustomItemManager() {
        itemsFile = new File(OceanSkyblock.get().getDataFolder(), "items.yml");
        itemsConfig = YamlConfiguration.loadConfiguration(itemsFile);
        loadItems();
    }

    public void createItem(String id, String rarity, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(rarity + " " + id);
        item.setItemMeta(meta);
        customItems.put(id, item);
        saveItem(id, item);
    }

    public void deleteItem(String id) {
        customItems.remove(id);
        itemsConfig.set("items." + id, null);
        saveConfig();
    }

    public void addLore(String id, String message) {
        ItemStack item = customItems.get(id);
        if (item != null) {
            ItemMeta meta = item.getItemMeta();
            List<String> lore = meta.getLore() != null ? meta.getLore() : new ArrayList<>();
            if (!message.contains("§")) {
                message = "§8" + message;
            }
            lore.add(message);
            meta.setLore(lore);
            item.setItemMeta(meta);
            saveItem(id, item);
        }
    }

    public void setDisplayName(String id, String displayName) {
        ItemStack item = customItems.get(id);
        if (item != null) {
            ItemMeta meta = item.getItemMeta();
            String currentDisplayName = meta.getDisplayName();
            String rarity = currentDisplayName.split(" ")[0];
            String coloredDisplayName = rarity + " " + displayName;
            meta.setDisplayName(coloredDisplayName);
            item.setItemMeta(meta);
            saveItem(id, item);
        }
    }

    public ItemStack getItem(String id) {
        return customItems.get(id);
    }

    private void saveItem(String id, ItemStack item) {
        itemsConfig.set("items." + id + ".type", item.getType().toString());
        itemsConfig.set("items." + id + ".displayName", item.getItemMeta().getDisplayName());
        itemsConfig.set("items." + id + ".lore", item.getItemMeta().getLore());
        saveConfig();
    }

    private void saveConfig() {
        try {
            itemsConfig.save(itemsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadItems() {
        if (itemsConfig.contains("items")) {
            for (String key : itemsConfig.getConfigurationSection("items").getKeys(false)) {
                Material type = Material.valueOf(itemsConfig.getString("items." + key + ".type"));
                String displayName = itemsConfig.getString("items." + key + ".displayName");
                List<String> lore = itemsConfig.getStringList("items." + key + ".lore");

                ItemStack item = new ItemStack(type);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(displayName);
                meta.setLore(lore);
                item.setItemMeta(meta);

                customItems.put(key, item);
            }
        } else {
            itemsConfig.createSection("items");
        }
    }
}