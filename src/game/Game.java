package game;

import game.console.InputController;
import game.items.template.Item;
import game.map.room.Room;
import game.map.room.door.Entrance;
import game.player.Player;
import game.randomizer.Randomizer;
import models.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Game {


    private List<Room> currentExploredRooms;

    private Room currentRoom;

    private Player player;

    private Randomizer rand;

    private List<Animal> animalField;

    private List<Item> itemField;

    private List<Entrance> entrances;

    private Map<String,String> availableCommands;

    public Game(List<Room> currentExploredRooms, Player player, List<Animal> animalField, List<Item> itemField, List<Entrance> entrances) {
        this.currentExploredRooms = currentExploredRooms;
        this.player = player;
        this.animalField = animalField;
        this.itemField = itemField;
        this.entrances = entrances;
        this.rand = new Randomizer();

        this.availableCommands = new HashMap<>();
        this.availableCommands.put("go <direction>", "Makes your character move to another room if the door is open" );
        this.availableCommands.put("get <item>", "Makes you take items depending on therire name");
        this.availableCommands.put("bag", "Show you what do you have on your bag");
        this.availableCommands.put("drop <item>", "Drops an item in the current room");
        this.availableCommands.put("look", "Print every information of the current room");
    }

    public void startGame(){
        String choice = "";
        String[] buffer;
        System.out.println("Wealcome " + player.getName() + ". Enjoy you permanence at Pawtropolis! owo");
        System.out.println("You have entered the first room of the game...");

        currentRoom = this.rand.randomizeRoom(this.itemField,this.animalField,this.entrances);
        this.currentExploredRooms.add(currentRoom);

        while (this.player.getLifePoints() != 0){

            System.out.println("What would you like to do?");
            this.printAllCommands();

            choice = InputController.readString();

            if (availableCommands.containsKey(choice)){
                if (choice.contains("go")){
                    buffer = choice.split("\\s");
                    for (Entrance e : currentRoom.getEntrances()){
                        if (buffer[1].contains(e.getPosition()) && e.isOpen()){
                            System.out.println("You went to " + buffer[1]);
                            currentRoom = this.rand.randomizeRoom(this.itemField,this.animalField,this.entrances);
                            currentExploredRooms.add(currentRoom);
                        }
                    }
                }
                else if (choice.contains("look")){
                    System.out.println("Info of this room : ");
                    currentRoom.getAnimals().printAllAnimals();
                }
            }
            else {
                System.out.println("This command is not available");
            }


        }
    }

    public void printAllCommands(){
        for (String command : this.availableCommands.keySet()){
            System.out.println(command);
        }
    }

    public void printAllItems(Room room){
        for (int i = 0; i < room.getItems().size(); i++){
            System.out.println(room.getItem(i).getName());
            System.out.println(room.getItem(i).getDescription());
        }
    }
}
