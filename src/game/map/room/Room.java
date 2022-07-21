package game.map.room;

import game.items.template.Item;
import game.map.room.door.Entrance;
import zoo.Zoo;

import java.util.List;
import java.util.Set;

public class Room {
    // TODO: le stanze dovrebbe contenere le stanze adiacenti
    // come le linked list
    private List<Item> items;
    private Zoo animals;
    private Set<Entrance> entrances;

    private boolean isInBounds(int index){
        if (index < this.items.size()){
            return true;
        }
        return false;
    }

    public Room(List<Item> items, Zoo animals, Set<Entrance> entrances) {
        this.items = items;
        this.animals = animals;
        this.entrances = entrances;
    }


    public List<Item> getItems() {
        return this.items;
    }

    public Item getItem(String itemToBeSearched){
        for (Item item : this.items){
            if (item.getName().equals(itemToBeSearched)){
                return item;
            }
        }
        return null;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Zoo getAnimals() {
        return animals;
    }

    public void setAnimals(Zoo animals) {this.animals = animals;}

    public Set<Entrance> getEntrances() {
        return entrances;
    }
    public void setEntrances(Set<Entrance> entrances) {
        this.entrances = entrances;
    }

    public Boolean deleteItem(String itemName){
        return this.items.remove(getItem(itemName));
    }

}
