import animals.Eagle;
import animals.Lion;
import animals.Tiger;
import game.bag.Bag;
import game.items.Apple;
import game.items.template.Item;
import game.map.room.Room;
import game.map.room.door.Entrance;
import game.randomizer.Randomizer;
import zoo.Zoo;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        Random rand = new Random();

        // making fields of names for making a pseudo-random name generator
        String[] name = {"Natasha", "Giuseppina", "Michele", "Rossastri", "Mogh",
                "Azune", "Ramingo", "Zavala", "Chamber", "Rossastri", "Alessandro",
                "Trussardi", "Genovesi", "Marcanti", "Colossali", "Ronaldigno",
                "Giovanotti", "Gregorio il grande Greg", "Costantino", "Gren",
                "Sasha", "Jetstream", "Sam"};

        String[] fav_food = {"Pesce", "Prosciutto", "Ciliege","Porchetta", "Banane",
                "Bolognese", "Carbonara", "Totani", "Calamaro", "Anguilla", "Pane", "Lattuga",
                "Carciofi", "Melanzane", "Tartufo", "Stufato d'erba", "Olive", "Cereali",
                "Sushi", "Miele", "Anguille", "Spaghetti", "Bananane", "Grasso", "Abbacchio",
                "Ciambellone", "Cozze", "Mozzarelle", "Carciofini"};

        String[] possibleEntraces = {"north", "south", "east", "ovest"};

        // creation of the zoo
        int zooSize = 12;

        // initialize variable for the randomize process
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();

        int maxAge = 100;

        float maxWeight = 400;
        float minWeight = 1;

        float maxHeight = 50;
        float minHeight = 1;

        float maxWingOpening = 10;
        float minWingOpening = 1;

        float maxTail = 30;
        float minTail = 2;

        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        // formula for float random numbers
        //min + Math.random() * (max - min);

        Zoo newestZoo = new Zoo();

       for(int i = 0; i < zooSize; i++){


            if(rand.nextInt(3) == 0){
                newestZoo.addAnimal(new Lion(name[rand.nextInt(name.length)],
                        fav_food[rand.nextInt(name.length)],
                        rand.nextInt(maxAge),
                        minWeight + rand.nextFloat() * (maxWeight - minWeight),
                        minHeight + rand.nextFloat() * (maxHeight - minHeight),
                        LocalDate.ofEpochDay(randomDay),
                minTail + rand.nextFloat() * (maxTail - minTail)));

            } else if (rand.nextInt(3) == 1) {
                newestZoo.addAnimal(new Eagle(name[rand.nextInt(name.length)],
                        fav_food[rand.nextInt(name.length)],
                        rand.nextInt(maxAge),
                        minWeight + rand.nextFloat() * (maxWeight - minWeight),
                        minHeight + rand.nextFloat() * (maxHeight - minHeight),
                        LocalDate.ofEpochDay(randomDay),
                        minWingOpening + rand.nextFloat() * (maxWingOpening - minWingOpening)));
            }
            else if (rand.nextInt(3) == 2){
                newestZoo.addAnimal(new Tiger(name[rand.nextInt(name.length)],
                        fav_food[rand.nextInt(name.length)],
                        rand.nextInt(maxAge),
                        minWeight + rand.nextFloat() * (maxWeight - minWeight),
                        minHeight + rand.nextFloat() * (maxHeight - minHeight),
                        LocalDate.ofEpochDay(randomDay),
                        minTail + rand.nextFloat() * (maxTail - minTail)));

                // TODO: usi sempre rand quindi si fotte certe volte dani attento !!!
                // quindi dopo lo devi rirandomizzare alla normalità

                rand.nextInt(3);
            }
        }


       List<Item> items = new ArrayList<>();
       List<Entrance> entrances = new ArrayList<>();

       Bag bag = new Bag(39);
       items.add(new Apple("boh",2));
       items.add(new Item("boh",2));


        // I/O verification

        Room newRoom = Randomizer.randomizeRoom(items,newestZoo,entrances);

        System.out.println(newRoom.getItem(0).getName());
        newRoom.getAnimals().printAllAnimals();
        System.out.println(newRoom.getEntrances().get(0).getPosition());


        List<String> testaaaa = new ArrayList<>();
        testaaaa.add("ciao");

        bag.addItem(items.get(0));
        bag.addItem(items.get(0));
        bag.addItem(items.get(0));
        bag.addItem(items.get(1));


        List<Item> found = bag.searchItemsByType(Apple.class);

        // TODO : FINISCI IL GIOCO
    }
}