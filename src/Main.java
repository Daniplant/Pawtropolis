import animals.Eagle;
import animals.Lion;
import animals.Tiger;
import game.Game;
import game.items.Apple;
import game.items.Key;
import game.items.template.Item;
import game.map.room.door.Entrance;
import zoo.Zoo;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

        Random rand = new Random();

        // making fields of names for  a pseudo-random name generator
        String[] name = {"Natasha", "Giuseppina", "Michele", "Rossastri", "Mogh",
                "Azune", "Ramingo", "Zavala", "Chamber", "Rossastri", "Alessandro",
                "Trussardi", "Genovesi", "Marcanti", "Colossali", "Ronaldigno",
                "Giovanotti", "Gregorio il grande Greg", "Costantino", "Gren",
                "Sasha", "Jetstream", "Sam", "Genovesi", "Il ramingo"};

        String[] fav_food = {"Pesce", "Prosciutto", "Ciliege","Porchetta", "Banane",
                "Bolognese", "Carbonara", "Totani", "Calamaro", "Anguilla", "Pane", "Lattuga",
                "Carciofi", "Melanzane", "Tartufo", "Stufato d'erba", "Olive", "Cereali",
                "Sushi", "Miele", "Anguille", "Spaghetti", "Bananane", "Grasso", "Abbacchio",
                "Ciambellone", "Cozze", "Mozzarelle", "Carciofini", "risotto nero"};

        String[] possibleEntrances = {"north", "east", "ovest"};

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

        Zoo zoo = new Zoo();

        List<Item> items = new ArrayList<>();
        Set<Entrance> entrances = new HashSet<>();

        for (int i = 0; i < possibleEntrances.length; i++){
            entrances.add(new Entrance(rand.nextBoolean(), possibleEntrances[i]));
        }

       for(int i = 0; i < zooSize; i++){
            if(rand.nextInt(3) == 0){
                zoo.addAnimal(new Lion(name[rand.nextInt(name.length)],
                        fav_food[rand.nextInt(name.length)],
                        rand.nextInt(maxAge),
                        minWeight + rand.nextFloat() * (maxWeight - minWeight),
                        minHeight + rand.nextFloat() * (maxHeight - minHeight),
                        LocalDate.ofEpochDay(randomDay),
                minTail + rand.nextFloat() * (maxTail - minTail)));

            } else if (rand.nextInt(3) == 1) {
                zoo.addAnimal(new Eagle(name[rand.nextInt(name.length)],
                        fav_food[rand.nextInt(name.length)],
                        rand.nextInt(maxAge),
                        minWeight + rand.nextFloat() * (maxWeight - minWeight),
                        minHeight + rand.nextFloat() * (maxHeight - minHeight),
                        LocalDate.ofEpochDay(randomDay),
                        minWingOpening + rand.nextFloat() * (maxWingOpening - minWingOpening)));
            }
            else if (rand.nextInt(3) == 2){
                zoo.addAnimal(new Tiger(name[rand.nextInt(name.length)],
                        fav_food[rand.nextInt(name.length)],
                        rand.nextInt(maxAge),
                        minWeight + rand.nextFloat() * (maxWeight - minWeight),
                        minHeight + rand.nextFloat() * (maxHeight - minHeight),
                        LocalDate.ofEpochDay(randomDay),
                        minTail + rand.nextFloat() * (maxTail - minTail)));

                rand.nextInt(3);
            }
        }

       items.add(new Apple("god's apple",2));
       items.add(new Apple("common apple",2));
       items.add(new Key("pawtropolis key",10));

        Game newGame = new Game(zoo, items,entrances);
        newGame.startGame();

        /* TODO priorità :
        // fare pulizia del codice []
        // cambiare le liste in sets dove è necessario [x]
        // guarda la classe game dei todo [x]
           implementa bene ROOM (Guarda la classe Room) []
        */
    }
}