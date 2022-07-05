package game.items.types;

import game.player.Player;

public interface Consumable {
    // basic interface for Items that can be consumed by the player

    void useConsumable(Player player);

    boolean isConsumable();

}
