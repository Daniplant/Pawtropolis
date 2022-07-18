package game.items;

import game.items.template.Item;
import game.items.types.Consumable;
import game.player.Player;

public class Apple extends Item implements Consumable {
    private boolean canBeConsumed;

    public Apple(String name, int requirementSpace) {
        super(name, requirementSpace);
        this.setDescription("a common apple that gives you 20HP");
        canBeConsumed = true;
    }

    @Override
    public void useConsumable(Player player) {
        // + 20 hp
        player.setLifePoints(player.getLifePoints() + 20);
        this.canBeConsumed = false;
    }

    @Override
    public boolean isConsumable(){
        return this.canBeConsumed;
    }
}
