package game.map.room;

import game.items.template.Item;
import game.map.room.door.Entrance;
import models.Animal;
import zoo.Zoo;

import java.util.List;

public class Room {
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
        return items;
    }

    public Item getItem(int index){
        if (isInBounds(index)){
            return this.items.get(index);
        }
        return null;
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
