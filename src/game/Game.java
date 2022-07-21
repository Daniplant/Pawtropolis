package game;
import game.bag.Bag;
import game.console.InputController;
import game.items.template.Item;
import game.items.types.Consumable;
import game.items.types.KeyItem;
import game.map.room.Room;
import game.map.room.door.Entrance;
import game.player.Player;
import game.randomizer.Randomizer;
import models.animalBaseModel.Animal;
import zoo.Zoo;

import java.util.*;
import java.util.function.Function;


public class Game {
    Random random;

    private List<Room> currentExploredRooms;

    private Room currentRoom;

    private Player player;

    // fields is used for randomly generate things such as animals, items and etc
    private Zoo animalField;

    private List<Item> itemField;

    private Set<Entrance> entrancesField;

    // TODO: Opzionale:
    // cerca la classe che racchiude tutte le funzioni e non Object
    // TROPPO GENERICO
    private Map<String, Object> availableCommands;


    private void makePlayer(){
        this.player = new Player(InputController.readString("Insert you name : "), 100, new Bag(20));
        System.out.println("Welcome "  + "'" + player.getName() + "'" + " Enjoy your permanence at Pawtropolis! owo");

    }
    public Game(Zoo animalField, List<Item> itemField, Set<Entrance> entrances) {
        this.animalField = animalField;
        this.itemField = itemField;
        this.entrancesField = entrances;
        this.currentExploredRooms = new ArrayList<>();
        random = new Random();

        this.availableCommands = new HashMap<>();
        this.availableCommands.put("help : prints all the commands available", (Runnable) this::printAllCommands);
        this.availableCommands.put("go <Direction> : move to a room by his entrace", (Function<String,Boolean>) this::nextRoom);     // x
        this.availableCommands.put("pick <Item> : get an item from a room ", (Function<String,Boolean>) this::pickItem);  // x
        this.availableCommands.put("bag : checks your bag", (Runnable) this::printMyBag);
        this.availableCommands.put("drop <Item> : drops an item in the current room", (Function<String , Boolean>) this::dropItem); // x
        this.availableCommands.put("look : check the current room", (Runnable) this::printAllRoomInfo); //x
        this.availableCommands.put("use <Item> : use an item on you bag", (Function<String, Boolean>) this::useItem); //x
        this.availableCommands.put("distance : print how much rooms have you moved from the start", (Runnable) this::printExploredRooms); //x
        this.availableCommands.put("status : prints your player status", (Runnable) this::printPlayerStatus);
        // back function
        // inspect function (In bag)


    }


    public void startGame(){

        String choice = "";
        String[] userCommand;
        boolean found = false;

        System.out.println("You have entered the first room of the game...");

        makePlayer();

        currentRoom = Randomizer.randomizeRoom(this.itemField,this.animalField,this.entrancesField,
                this.random.nextInt(this.itemField.size()),2,this.entrancesField.size());
        this.currentExploredRooms.add(currentRoom);
        while (this.player.getLifePoints() != 0){

            choice = InputController.readString("What would you like to do");
            userCommand = choice.split("\\s",2);
            System.out.println("\n");
            //OutputController.clearTerminal();
            for (String command : this.availableCommands.keySet()){
                if (command.contains(userCommand[0]) && !userCommand[0].equals("")){
                    found = true;
                    // if the command needs i parameter "go <direction>"
                    if (this.availableCommands.get(command) instanceof Function && userCommand.length == 2){
                        ((Function)this.availableCommands.get(command)).apply(userCommand[1]);
                    }
                    // if the command needs no parameters "look"
                    else if (this.availableCommands.get(command) instanceof Runnable){
                        ((Runnable)this.availableCommands.get(command)).run();
                    }
                }
            }
            if (!found) System.out.println("Unable to find the command");
            else {
                found = false;
            }
        }
    }

    public String getCurrentRoomInfo(){
        StringBuilder info = new StringBuilder();
        info.append("Items : ");
        for (Item item : this.currentRoom.getItems()){
            info.append(item.getName()).append("(").append(item.getClass().getSimpleName()).append(") ");
        }
        info.append("\n");
        info.append("Animals : ");
        for (Animal animals : this.currentRoom.getAnimals().getAllAnimals()){
            info.append(animals.getName()).append("(").append(animals.getClass().getSimpleName()).append(") ");
        }
        info.append("\n");
        info.append("Entraces : ");
        for (Entrance entrance : this.currentRoom.getEntrances()){
            info.append(entrance.getPosition());
            if (entrance.isOpen()) info.append("(").append("open").append(") "); else info.append("(").append("closed").append(") ");
        }
        return info.toString();

    }


    public void printAllCommands(){
        StringBuilder output = new StringBuilder();
        for (String command : this.availableCommands.keySet()){
            output.append(command).append("\n");
        }
        System.out.println(output);
    }

    public void printAllRoomInfo(){
        System.out.println(getCurrentRoomInfo());
    }

    public void printMyBag(){
        StringBuilder output = new StringBuilder();
        if (this.player.getBag().getItemList().size() == 0){
            System.out.println("There are no items in you bag");
            return;
        }
        output.append("In my bag : ").append("\n");
        for (Item item : this.player.getBag().getItemList()){
            output.append("Name : ").append(item.getName()).append(" Description : ").append(item.getDescription()).append(" ")
                    .append("Slot space ").append(item.getRequiredSlotSpace()).append("\n");
        }
        System.out.println(output);
    }

    public void printPlayerStatus(){
        StringBuilder status = new StringBuilder();
        status.append("Name : ").append(this.player.getName()).append("\n");
        status.append("HP : ").append(this.player.getLifePoints()).append("\n");
        status.append("remained bag space : ").append(this.player.getBag().getEmptySlots()).append("\n");
        System.out.println(status);
    }
    public String getAllCommands(Void unused){
        StringBuilder output = new StringBuilder();
        for (String command : this.availableCommands.keySet()){
            output.append(command).append("\n");
        }
        return output.toString();
    }


    public Boolean nextRoom(String entranceLocation){
        for (Entrance entrance : currentRoom.getEntrances()){
            if (entrance.getPosition().equals(entranceLocation)){
                if (entrance.isOpen()){
                    this.currentExploredRooms.add(this.currentRoom);
                    // TODO: RAndomizza bene il parametro degl'animali
                    this.currentRoom = Randomizer.randomizeRoom(this.itemField, this.animalField, this.entrancesField,
                            this.random.nextInt(this.itemField.size())
                            ,2,random.nextInt(this.entrancesField.size()));
                    System.out.println("You've entered another room");
                    return true;
                }
                else {
                    System.out.println("The door is looked you need a key to open it");
                    return false;
                }

            }
        }
        System.out.println("Couldn't find any entrance with that name");
        return false;
    }

    public Boolean pickItem(String itemName){
        this.player.getBag().addItem(this.currentRoom.getItem(itemName));
        System.out.println("Item has been picked");
        return this.currentRoom.deleteItem(itemName);
    }
    public Boolean dropItem(String itemName){
        for (Item item : this.player.getBag().getItemList()){
            if (item.getName().equals(itemName)){
                this.currentRoom.addItem(item);
                System.out.println("Item has been dropped");
                return this.player.getBag().deleteItem(item);
            }
        }
        return false;
    }

    public Boolean useItem(String itemName){
        String selectedEntrance;
        for (Item item : this.player.getBag().getItemList()){
            if (item.getName().contains(itemName)){
                if (item instanceof Consumable){
                    System.out.println(item.getDescription());
                    if (confirmChoice()){
                        ((Consumable) item).useConsumable(this.player);
                        if (((Consumable) item).isConsumable()) System.out.println("You can still use it");
                        else this.player.getBag().deleteItem(item);
                        System.out.println("The item has been consumed");
                        return true;
                    }
                } else if (item instanceof KeyItem) {
                    if (confirmChoice()){
                        selectedEntrance = InputController.readString("select the door position");
                        for (Entrance entrance : this.currentRoom.getEntrances()){
                            if (entrance.getPosition().equals(selectedEntrance)){
                                ((KeyItem) item).useKey(entrance);
                                this.player.getBag().deleteItem(item);
                                System.out.println("The door has been opened");
                                return true;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Item has not been found");
        return false;
    }

    private Boolean confirmChoice(){
        String choice = InputController.readString("Are you sure? y/n");

        return choice.contains("y") || choice.contains("Y");
    }

    private void printExploredRooms(){
        System.out.println("Rooms explored : " + this.currentExploredRooms.size());
    }

    
}
