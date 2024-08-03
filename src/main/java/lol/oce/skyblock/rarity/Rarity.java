package lol.oce.skyblock.rarity;

public enum Rarity {
    COMMON("&f"),
    UNCOMMON("&a"),
    RARE("&9"),
    EPIC("&5"),
    LEGENDARY("&6"),
    MYTHIC("&d");

    private final String color;

    Rarity(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}