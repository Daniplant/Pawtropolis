package game.items.types;

import game.map.room.door.Entrance;

public interface KeyItem {
    boolean useKey(Entrance entrance);

    boolean isUsable();
}
