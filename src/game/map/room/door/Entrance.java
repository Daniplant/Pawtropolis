package game.map.room.door;

public class Entrance {

    private boolean isOpen;
    private String position;

    public Entrance(boolean state, String position) {
        this.isOpen = state;
        this.position = position;
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



}
