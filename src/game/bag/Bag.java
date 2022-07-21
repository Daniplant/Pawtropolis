package game.bag;

import game.items.template.Item;
import game.map.room.Room;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bag {

    private List<Item> itemList;
    private final int totalSlots;

    private int slotUsed;

    public Bag(int totalSlots){
        this.itemList = new ArrayList<>();
        this.totalSlots = totalSlots;
        this.slotUsed = 0;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public int getEmptySlots(){
        return this.getTotalSlots() - this.slotUsed;
    }

    public Item getItem(String name){
        for (Item item : this.itemList){
            if (item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    public Boolean addItem(Item item){
        if (this.slotUsed + item.getRequiredSlotSpace() < totalSlots){
            this.itemList.add(item);
            this.slotUsed += item.getRequiredSlotSpace();
            return true;
        }
        return false;
    }


    public Boolean deleteItem(String name){
        if (getItem(name) != null){
            this.itemList.remove(getItem(name));
            return true;
        }
        return false;
    }

    public Boolean deleteItem(Item item){
        return this.itemList.remove(item);
    }

    public Boolean dropItem(Item item, Room room){
        // non mi ispisra il delete item:
        // se l'item viene messo nella stanza e poi
        // accade che l'oggetto non venga cancellato?
        room.addItem(item);
        return deleteItem(item);
    }

    public <T extends Item>List<Item> searchItemsByType(Class<T> item){
        List<Item> sameItemType = new ArrayList<>();
        for (Item itemSelected : this.itemList){
            if (itemSelected.getClass().equals(item)){
                sameItemType.add(itemSelected);
            }
        }
        return sameItemType;
    }

}
