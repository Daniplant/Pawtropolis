package game.bag;

import game.items.template.Item;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    /*    Bag
        elenco di item
        slot totali a disposizione (valore immutabile, rappresenta la dimensione della borsa)*/
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

    public Item getItem(int index){
        if (index < this.itemList.size()){
            return this.itemList.get(index);
        }
        else {
            return null;
        }
    }
    public  void addItem(Item item){
        if (this.slotUsed + item.getRequiredSlotSpace() < totalSlots){
            this.itemList.add(item);
            this.slotUsed += item.getRequiredSlotSpace();
        }
    }

    public void deleteItem(int index){
        if (index < this.itemList.size()){
            this.slotUsed -= this.itemList.get(index).getRequiredSlotSpace();
            this.itemList.remove(index);
        }
    }

}
