package game.items;

import game.items.template.Item;
import game.items.types.KeyItem;
import game.map.room.door.Entrance;

public class Key extends Item implements KeyItem {
    boolean isUsable;

    public Key(String name, int requiredSlotSpace) {
        super(name, requiredSlotSpace);
        isUsable = true;
        this.setDescription("A common key that opens a closed door");
    }

    @Override
    public boolean useKey(Entrance entrance) {
        if (!entrance.isOpen()){
            entrance.setOpen(true);
        }
        isUsable = false;
        return false;
    }

    @Override
    public boolean isUsable() {
        return isUsable;
    }
}
