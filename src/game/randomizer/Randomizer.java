package game.randomizer;

import game.items.template.Item;
import game.map.room.Room;
import game.map.room.door.Entrance;
import models.animalBaseModel.Animal;
import zoo.Zoo;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    // This is only a class for randomize everything that I need,

    private Randomizer(){
    }


    public static <T> T randomize(List<T> list){
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
        //return list.get(random.nextInt(list.size()-1));
    }

    public static <T>  List<T> randomizeRange(List<T> list, int range, boolean areUnique){
       range = (range > list.size() && areUnique) ? list.size(): range;

        Random random = new Random();
        List<T> randomList;
        List<Integer> usedItems;
        int randomNumber;

        usedItems = new ArrayList<>();
        randomList = new ArrayList<>();

        int i = 0;
        while (i < range){
            randomNumber = random.nextInt(list.size());
            if (areUnique){
                if (!usedItems.contains(randomNumber)){
                    randomList.add(list.get(randomNumber));
                    usedItems.add(randomNumber);
                    i++;
                }
            }
            else {
                randomList.add(list.get(randomNumber));
                i++;
            }
        }
        return randomList;
    }

    public LocalDate randomizeDate(LocalDate minDate, LocalDate maxDate) {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();

        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        return LocalDate.ofEpochDay(randomDay);

    }

    public static int randomizeInteger(int maxNumber){
        Random random = new Random();
        return random.nextInt(maxNumber);
    }

    public static Zoo randomizeZoo(Zoo zoo, int rangePerSpecie){
        // TODO: trova un modo per randomizzare lo zoo [x]
        Random random = new Random();
        Zoo randomZoo = new Zoo();
        List<Class<? extends Animal>> specie = zoo.getAllSpecies();
        int specieTotalSize = specie.size();
        Class<? extends Animal> aimedSpecie;

        for (int i = 0; i < specieTotalSize; i++){
            aimedSpecie = randomize(specie);
            for (int j = 0; j < rangePerSpecie; j++){
                 // attento che se specie.size è 1 allora darà un throw error
                randomZoo.addAnimal(zoo.getAnimal(aimedSpecie, random.nextInt(zoo.getAllAnimalsFrom(aimedSpecie).size())));
            }
            specie.remove(aimedSpecie);

        }

        return randomZoo;
    }

    public static void randomizeEntranceStates(List<Entrance> entrances){
        Random state = new Random();
        for (int i = 0; i < entrances.size(); i++){
            entrances.get(i).setOpen(state.nextBoolean());
        }
    }

    public static Room randomizeRoom(List<Item> items, Zoo animals, List<Entrance> entrances,
                                     int rangeItem, int rangeAnimals, int rangeEntrances){
        List<Class<? extends Animal>> specie = animals.getAllSpecies();
        List<Item> randItems;
        Zoo randIAnimals;
        List<Entrance> randEntrances;

        randItems = randomizeRange(items,rangeItem,false);
        randIAnimals = randomizeZoo(animals,rangeAnimals);
        randEntrances = randomizeRange(entrances,rangeEntrances,true);

        return new Room(randItems,randIAnimals,randEntrances);
    }
}

