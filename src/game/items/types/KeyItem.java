package game.items.types;

import game.map.room.door.Entrance;

public interface KeyItem {
    void useKey(Entrance entrance);

    boolean isUsable();
}
