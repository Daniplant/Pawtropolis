package game.map.gameMap;

import game.items.template.Item;

import game.map.Room;
import game.map.room.door.Entrance;

import game.randomizer.Randomizer;
import zoo.Zoo;

import java.util.*;
import java.util.List;

public class GameMap {


    // TODO : save player current player position by using a room
    // TODO : implement a go to the room function

    private int mapSize;
    // togli le responsabilità di muovimento al game... è la cosa migliore da fare

    private List<Room> generatedRooms;
    private Room firstRoom;
    private Set<String> entrancesRange;
    private List<Item> itemRange;
    private Zoo zooRange;
    private int maxItems,maxAnimals,minOpenEntrances;
    private Map<String,String> oppositeEntrances;

    public GameMap(Set<String> entranceRange, List<Item> itemRange, Zoo zooRange, int maxItems,
            int maxAnimals, int minOpenEntrances, List<String> predefinedPath){

        this.mapSize = predefinedPath.size();

        this.generatedRooms = new ArrayList<>();
        this.maxAnimals = maxAnimals;
        this.maxItems = maxItems;
        this.minOpenEntrances = minOpenEntrances;
        this.entrancesRange = entranceRange;
        this.itemRange = itemRange;
        this.zooRange = zooRange;
        this.firstRoom = Randomizer.randomizeRoom(itemRange,zooRange,entranceRange,
                maxItems,maxAnimals,0);

        this.generatedRooms.add(this.firstRoom);
        this.mapSize--;
        this.oppositeEntrances = new HashMap<>();

        this.oppositeEntrances.put("north", "south");
        this.oppositeEntrances.put("east", "ovest");
        this.oppositeEntrances.put("south", "north");
        this.oppositeEntrances.put("ovest", "east");

        generateRoomsUsing(predefinedPath, this.firstRoom);
        connectRooms();
        //printAllRoomEntrances();
    }


    private void generateRandomMap(Room start){
        while (this.mapSize != 0){
            Entrance entrance = Randomizer.randomizeSet(start.getAllOpenEntrances());
            // TODO : è una costrizione che devo applicare, trova un modo per non farlo

            // takes an open random entrance of a room that has not a room in it
            while (entrance.getNextRoom() != null){
                entrance = Randomizer.randomizeSet(start.getAllOpenEntrances());
            }
            // TODO : c'è la possibilità che quando viene randomizzato il percorso, il suo percorso PUNTA verso una stanza già creata
            // EMERGENZA CHE DEVI FIXARE

            // randomize the next room at the selected entrance
            entrance.setNextRoom(Randomizer.randomizeRoom(this.itemRange,this.zooRange,this.entrancesRange,
                    this.maxItems,this.maxAnimals,this.minOpenEntrances));

            this.generatedRooms.add(entrance.getNextRoom());

            // Special case if minOpenEntrances = 1 in main.java when we call the constructor of GameMap:
                // IF the entrance.nextRoom has only one open entrance (Which means that the randomizer picked the already opened door)
                // [Because the Randomizer of entrances doesn't have any "filter"]
                // We obbligate the room to open another one
            // Reason of this mess:
                // When we randomize the entrance we don't want that he "goes back to an already generated room"
                // (Here's why I also added a while loop on line 68)
            entrance.getNextRoom().getEntrance(this.oppositeEntrances.get(entrance.getPosition())).setNextRoom(entrance.getNextRoom());
            if (entrance.getNextRoom().getAllOpenEntrances().size() == 1){
                while (entrance.getNextRoom().getAllOpenEntrances().size()!= 2){
                    entrance.getNextRoom().getEntrance(Randomizer.randomizeSet(this.entrancesRange)).setOpen(true);
                }
            }



            System.out.println("went to " + entrance.getPosition());
            this.mapSize--;
            generateRandomMap(entrance.getNextRoom());

        }
    }

    private void generateRoomsUsing(List<String> openEntrances, Room start){
        while (openEntrances.size() != 0){
            // I should copy the entrances but for now i'll leave it as it is
            start.getEntrance(openEntrances.get(0)).setNextRoom(
                    Randomizer.randomizeRoom(this.itemRange, this.zooRange,this.entrancesRange
                            ,this.maxItems,this.maxAnimals,0));

            start.getEntrance(openEntrances.get(0)).getNextRoom()
                    .getEntrance(this.oppositeEntrances.get(openEntrances.get(0))).setNextRoom(start);

            // TODO : potresti implementare direttamente connectRooms qui

            this.generatedRooms.add(start.getEntrance(openEntrances.get(0)).getNextRoom());
            start = start.getEntrance(openEntrances.get(0)).getNextRoom();
            openEntrances.remove(0);
            generateRoomsUsing(openEntrances, start);

        }
    }
    private void connectRooms(){
        for (Room room : this.generatedRooms){
            for (Entrance entrance : room.getEntrances()){
                if (entrance.getNextRoom() != null){
                    entrance.setOpen(true);
                }
            }
        }
    }



    public List<Room> getGeneratedRooms() {
        return generatedRooms;
    }

    public void setGeneratedRooms(List<Room> generatedRooms) {
        this.generatedRooms = generatedRooms;
    }

    private void printAllRoomEntrances(){
        System.out.println("------------------------------------------");
        for (Room room : this.generatedRooms){
            for (Entrance entrance : room.getEntrances()){
                System.out.print(entrance.getPosition() + " ");
                System.out.print(entrance.isOpen() + " ");
                System.out.println();
            }
            System.out.println("------------------------------------------");
        }
    }

    public Room getFirstRoom() {
        return firstRoom;
    }

    public void setFirstRoom(Room firstRoom) {
        this.firstRoom = firstRoom;
    }

}

