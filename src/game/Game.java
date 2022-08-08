package game;
import game.bag.Bag;
import game.console.InputController;
import game.items.template.Item;
import game.items.types.Consumable;
import game.items.types.KeyItem;
import game.map.Room;

import game.map.room.door.Entrance;
import game.map.gameMap.GameMap;
import game.player.Player;
import models.animalBaseModel.Animal;
import zoo.Zoo;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;


public class Game {

    Random random;

    private GameMap map;
    private List<Room> currentExploredRooms;

    private Room currentRoom;

    private Player player;

    // fields is used for randomly generate things such as animals, items and etc
    private Zoo animalField;

    private List<Item> itemField;

    private Set<String> entrancesField;

    // TODO: Opzionale:
    // cerca la classe che racchiude tutte le funzioni e non Object
    // TROPPO GENERICO
    private Map<String, Object> availableCommands;

    private Map<String,String> infoCommands;


    private void makePlayer(){
        this.player = new Player(InputController.readString("Insert you name : "), 100, new Bag(20));
        System.out.println("Welcome "  + "'" + player.getName() + "'" + " Enjoy your permanence at Pawtropolis! owo");

    }
    public Game(Zoo animalField, List<Item> itemField, Set<String> entranceRange,
                int maxItems, int maxAnimals, List<String> predefinedPath) {
        this.animalField = animalField;
        this.itemField = itemField;
        this.entrancesField = entranceRange;
        this.currentExploredRooms = new ArrayList<>();
        this.infoCommands = new HashMap<>();
        random = new Random();
        this.map = new GameMap(entranceRange,itemField,animalField,maxItems, maxAnimals,0,predefinedPath);

        this.availableCommands = new HashMap<>();

        this.availableCommands.put("about", (Consumer<String>) this::printAbout);
        this.infoCommands.put("about", "Prints what that command does");
        this.availableCommands.put("help", (Runnable) this::printAllCommands);
        infoCommands.put("help", "Prints all the available commands and information about them");
        this.availableCommands.put("go", (Function<String,Boolean>) this::goToTheNextRoom);
        infoCommands.put("go", "Go to a selected entrance by just typing the entrance name");
        this.availableCommands.put("pick", (Function<String,Boolean>) this::pickItem);
        infoCommands.put("pick", "Picks an item in the current room that you are in");
        this.availableCommands.put("bag", (Runnable) this::printMyBag);
        infoCommands.put("bag", "Prints all the available items on you bag");
        this.availableCommands.put("drop", (Function<String , Boolean>) this::dropItem); // x
        infoCommands.put("drop", "Drops an item in the current room from your bag");
        this.availableCommands.put("look", (Runnable) this::printAllRoomInfo); //x
        infoCommands.put("look", "Prints all the information about the current room");
        this.availableCommands.put("use", (Function<String, Boolean>) this::useItem); //x
        infoCommands.put("use", "Select an item which you'd like to use in your bag");
        this.availableCommands.put("distance", (Runnable) this::printExploredRooms); //x
        infoCommands.put("distance", "Shows how many room have you been");
        this.availableCommands.put("status", (Runnable) this::printPlayerStatus);
        infoCommands.put("status", "Shows your player status");

    }


    public void startGame(){

        String choice = "";
        String[] userCommand;
        boolean found = false;

        System.out.println("You have entered the first room of the game...");


        makePlayer();
        System.out.println("Type 'help' for printing all the commands available");
        currentRoom = this.map.getFirstRoom();
        while (this.player.getLifePoints() != 0){


            choice = InputController.readString("What would you like to do");
            userCommand = choice.split("\\s",2);
            System.out.println("\n");
            if (this.availableCommands.get(userCommand[0]) instanceof Function && userCommand.length == 2){
                ((Function)this.availableCommands.get(userCommand[0])).apply(userCommand[1]);
            }
            // if the command needs no parameters "look"
            else if (this.availableCommands.get(userCommand[0]) instanceof Runnable){
                ((Runnable)this.availableCommands.get(userCommand[0])).run();

            }
            else if (this.availableCommands.get(userCommand[0]) instanceof Consumer && userCommand.length == 2){
                ((Consumer) this.availableCommands.get(userCommand[0])).accept(userCommand[1]);
            }
            else {
                System.out.println("Unable to find the command '" + choice + "'");
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
        for (Map.Entry<String, String> entry: this.infoCommands.entrySet()){
            output.append(entry.getKey()).append(": ").append(" ").append(entry.getValue()).append("\n");
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
            output.append("Name : ").append(item.getName()).append("\n").append("Description : ").append(item.getDescription()).append("\n")
                    .append("Slot space :").append(item.getRequiredSlotSpace()).append("\n");
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

    public void printAbout(String command){
        if (this.infoCommands.containsKey(command)){
            System.out.println(this.infoCommands.get(command));
        }
    }
    public String getAllCommands(){
        StringBuilder output = new StringBuilder();
        for (String command : this.availableCommands.keySet()){
            output.append(command).append("\n");
        }
        return output.toString();
    }


    public Boolean goToTheNextRoom(String entranceLocation){
        for (Entrance entrance : currentRoom.getEntrances()){
            if (entrance.getPosition().equals(entranceLocation)){
                if (entrance.isOpen()){
                    this.currentExploredRooms.add(this.currentRoom);
                    this.currentRoom = this.currentRoom.getEntrance(entranceLocation).getNextRoom();
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
        if (this.currentRoom.getItem(itemName) != null){
            if(!this.player.getBag().addItem(this.currentRoom.getItem(itemName))){
                System.out.println("You cannot carry this item, not enough slot space");
            }
            else {
                System.out.println("Item has been picked");
            }

        }
        else {
            System.out.println("No items with name '" + itemName + "' exist in this room");
        }

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
        System.out.println("No item called '" + itemName + "' exist in you bag");
        return false;
    }

    public Boolean useItem(String itemName){
        String selectedEntrance;
        Boolean hasBeenUsed = false;
        Item item = this.player.getBag().getItem(itemName);
        if (item != null) {
           if (item instanceof Consumable){
               hasBeenUsed =  this.useAsConsumable(((Consumable) item));
           } else if (item instanceof KeyItem) {
               hasBeenUsed =  this.useAsKey(((KeyItem) item));
           }


           if (hasBeenUsed){
                this.player.getBag().deleteItem(item);
           }
        }
        else {
            System.out.println("Item has not been found");
        }
        return hasBeenUsed;
    }

    private Boolean useAsConsumable(Consumable consumable) {
        if (confirmChoice()){
            consumable.useConsumable(this.player);
            System.out.println("Item has been consumed");
            if (consumable.isConsumable()){
                System.out.println("You can still use it");
            }
            return true;
        }
        return false;
    }

    private Boolean useAsKey(KeyItem key){
        StringBuilder allOpenEntrances = new StringBuilder();
        boolean hasBeenUsed = false;
        if (confirmChoice()){
            for (Entrance entrance : this.currentRoom.getAllOpenEntrances()){
                allOpenEntrances.append(entrance.getPosition()).append("\n");
            }
            String selectedEntrance = InputController.readString("Select which entrance to open\n" + allOpenEntrances);
            if (this.currentRoom.getEntrance(selectedEntrance) != null){
                if (!this.currentRoom.getEntrance(selectedEntrance).isOpen()){
                    this.currentRoom.getEntrance(selectedEntrance).setOpen(true);
                    System.out.println("Entrance has been opened");
                    hasBeenUsed = true;
                }
                else {
                    System.out.println("The door is already opened");
                }
            }
            else {
                System.out.println("No entrance named : " + selectedEntrance);
            }
        }
        return hasBeenUsed;
    }

    private Boolean confirmChoice(){
        String choice = InputController.readString("Are you sure? y/n");

        return choice.contains("y") || choice.contains("Y");
    }

    private void printExploredRooms() {
        System.out.println("Rooms explored : " + this.currentExploredRooms.size());
    }
}
