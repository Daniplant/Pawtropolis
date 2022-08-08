package game.map.room.door;


import game.map.Room;

public class Entrance {
    private boolean isOpen;
    private String position;

    private Room nextRoom;

    public Entrance(boolean state, String position) {
        this.isOpen = state;
        this.position = position;
        this.nextRoom = null;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setNextRoom(Room room){
        this.nextRoom = room;
    }

    public Room getNextRoom(){
        return this.nextRoom;
    }

}
