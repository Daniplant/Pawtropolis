package game.map.room;

import game.items.template.Item;
import game.map.room.door.Entrance;
import zoo.Zoo;

import java.util.ArrayList;
import java.util.List;

public class Room {
    // TODO: le stanze dovrebbe contenere le stanze adiacenti
    // come le linked list
    private List<Item> items;
    private Zoo animals;
    private List<Entrance> entrances;

    private boolean isInBounds(int index){
        if (index < this.items.size()){
            return true;
        }
        return false;
    }

    public Room(List<Item> items,Zoo animals, List<Entrance> entrances) {
        this.items = items;
        this.animals = animals;
        this.entrances = entrances;
    }
    

    public List<Item> getItems() {
        return  this.items;
    }

    public Item getItem(int index){
        if (isInBounds(index)){
            return new Item(items.get(index).getName(), items.get(index).getRequiredSlotSpace());
        }
        return null;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void removeItem(int index) {
        if (isInBounds(index)){
            this.items.remove(index);
        }
    }

    public Zoo getAnimals() {
        return animals;
    }

    public void setAnimals(Zoo animals) {this.animals = animals;}

    public List<Entrance> getEntrances() {
        return entrances;
    }
    public void setEntrances(List<Entrance> entrances) {
        this.entrances = entrances;
    }



}
