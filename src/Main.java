import animals.Eagle;
import animals.Lion;
import animals.Tiger;
import game.bag.Bag;
import game.items.Apple;
import game.items.template.Item;
import game.items.types.Consumable;
import game.map.room.door.Entrance;
import game.player.Player;
import game.randomizer.Randomizer;
import models.Animal;
import zoo.Zoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

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
        Random rand = new Random();
        // formula for float random numbers
        //min + Math.random() * (max - min);

        Zoo newestZoo = new Zoo();

        for(int i = 0; i < zooSize; i++){


            /* TODO : nel fare il processo di randomizazzione, per qualche motivo, non prende tutti gli elementi di
                   quanto io ne ho richiesto, infatti ne prenderà una cerca fetta.
                   Quindi è come se non accettasse le copie. Ma come è possibile
                   Questo?
                    Ipotesi :
                    C'è una possibilità che crei oggetti uguali (Impossibile)
                    C'è una possibilità che crei oggetti con nomi uguali e li sovrascrive (molto probabile)
                    C'è una possibilità che la funzione addAnimal() sovrascrivre un oggetto per un certo criterio
                    ignoto da me (molto probabile)


             */
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


        // I/O verification

        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader inputReader = new BufferedReader(input);
        try {
             System.out.println(inputReader.readLine());
        } catch (IOException e) {
            System.err.println("Error while reading user input");
        }

    }
}