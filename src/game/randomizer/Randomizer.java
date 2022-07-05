package game.randomizer;

import animals.Eagle;
import animals.Tiger;
import game.items.template.Item;
import game.map.room.Room;
import game.map.room.door.Entrance;
import models.Animal;
import zoo.Zoo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    // This is only a class for randomize everything that I need,
    private Random random;

    public Randomizer(){
        random = new Random();
    }

    public String randomizeString(List<String> field){
        return field.get(this.random.nextInt(field.size()-1));
    }


    public Item randomizeItem(List<Item> field){
        return field.get(this.random.nextInt(field.size()-1));
    }

    public List<Item> randomizeItems(List<Item> field){
        List<Item> randomItems = new ArrayList<>();
        int items = this.random.nextInt(field.size()-1);

        for (int i = 0; i < items; i++){
            randomItems.add(field.get(this.random.nextInt(field.size()-1)));
        }
        return randomItems;
    }

    public LocalDate randomizeDate(LocalDate minDate, LocalDate maxDate) {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();

        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        return LocalDate.ofEpochDay(randomDay);

    }

    public Animal randomizeAnimal(List<String> fieldName, List<String> fieldFavoriteFood, int maxAge, float maxWeight,
                                  float maxHeight, LocalDate minDate, LocalDate maxDate){
        Animal randomAnimal;

        if(this.random.nextInt(2) == 0) {
            randomAnimal = new Tiger(randomizeString(fieldName),randomizeString(fieldFavoriteFood), this.random.nextInt(maxAge)
            ,this.random.nextFloat() * (maxWeight), this.random.nextFloat() * (maxHeight), randomizeDate(minDate,maxDate),
                    this.random.nextFloat());
        }

        else{
            randomAnimal = new Eagle(randomizeString(fieldName),randomizeString(fieldFavoriteFood), this.random.nextInt(maxAge)
                    ,this.random.nextFloat() * (maxWeight), this.random.nextFloat() * (maxHeight), randomizeDate(minDate,maxDate),
                    this.random.nextFloat());
            }

        return randomAnimal;
    }

    public Zoo randomizeAnimals(List<Animal> animals, int size){
        Zoo randAnimals = new Zoo();
        int sizeRandomList = this.random.nextInt(animals.size()-1);


        for (int i = 0; i < size; i++){
            randAnimals.addAnimal(animals.get(this.random.nextInt(animals.size()-1)));
        }

        return randAnimals;
    }
    public Entrance randomizeEntrance(List<String> fieldEntrances){
        return new Entrance(this.random.nextBoolean(),fieldEntrances.get(this.random.nextInt(fieldEntrances.size()-1)));
    }

    public List<Entrance> randomizeEntrances(List<Entrance> fieldEntrances, int size){
        List<Entrance> randomEntraces = new ArrayList<>();
        for (int i=0;i < size; i++){
            randomEntraces.add(fieldEntrances.get(this.random.nextInt(fieldEntrances.size()-1)));
        }
        return randomEntraces;
    }

    public Room randomizeRoom(List<Item> items, List<Animal> animals, List<Entrance> entrances){
        return new Room(randomizeItems(items),randomizeAnimals(animals, this.random.nextInt(5)),entrances);
    }
}

