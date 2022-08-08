package game.randomizer;

import game.items.template.Item;

import game.map.Room;
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


    public static <T> T randomizeList(List<T> list){
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
        //return list.get(random.nextInt(list.size()-1));
    }

    public static <T> T randomizeSet(Set<T> set){
        Random randomInt = new Random();
        int randomObj = randomInt.nextInt(set.size());
        int i = 0;
        for (T obj : set){
            if (randomObj == i) return obj;
            i++;
        }
        return null;
    }


    public static <T>  List<T> randomizeRangeList(List<T> list, int range, boolean areUnique){
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

    public static <T>  Set<T> randomizeRangeSet(Set<T> set, int range){
        if (range > set.size()){
            Collections.shuffle(Arrays.asList(set.toArray()));
            return set;
        }

        Set<T> randomSet = new HashSet<>();

        int i = 0;

        Collections.shuffle(Arrays.asList(set.toArray()));

        for (T object : set){
            if (randomSet.size() == range){
                break;
            }
            randomSet.add(object);

        }
        return randomSet;
    }

    public static LocalDate randomizeDate(LocalDate minDate, LocalDate maxDate) {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();

        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        return LocalDate.ofEpochDay(randomDay);

    }

    public static int randomizeInteger(int maxNumber){
        Random random = new Random();
        return random.nextInt(maxNumber);
    }

    public static Zoo randomizeZooPerRange(Zoo zoo, int rangePerSpecie){
        Random random = new Random();
        Zoo randomZoo = new Zoo();
        List<Class<? extends Animal>> specie = zoo.getAllSpecies();
        int specieTotalSize = specie.size();
        Class<? extends Animal> aimedSpecie;

        for (int i = 0; i < specieTotalSize; i++){
            aimedSpecie = randomizeList(specie);
            for (int j = 0; j < rangePerSpecie; j++){
                 // attento che se specie.size è 1 allora darà un throw error
                randomZoo.addAnimal(zoo.getAnimal(aimedSpecie, random.nextInt(zoo.getAllAnimalsFrom(aimedSpecie).size())));
            }
            specie.remove(aimedSpecie);

        }

        return randomZoo;
    }

    public static Zoo randomizeZoo(Zoo zoo, int totalAnimals){
        Random random = new Random();
        Zoo randomZoo = new Zoo();
        List<Class<? extends Animal>> specie = zoo.getAllSpecies();
        int specieTotalSize = specie.size();
        int animalsInserted = 0;
        int randomNum;

        while (animalsInserted < totalAnimals){
            randomNum = random.nextInt(specieTotalSize);
            // get from the specie selected by the random number a random animal from that specie randomized
            randomZoo.addAnimal(zoo.getAnimal(specie.get(randomNum), random.nextInt(zoo.getRaceSizeList(specie.get(randomNum)))));
            animalsInserted++;
        }

        return randomZoo;
    }

    public static Set<Entrance> randomizeEntrances(Set<String> entrances,int maxOpenEntrances){
        int i = 0;
        Set<Entrance> randEntrances = new HashSet<>();
        for (String entrance : entrances){
            randEntrances.add(new Entrance(false, entrance));
        }

        while (i < maxOpenEntrances){
            Randomizer.randomizeSet(randEntrances).setOpen(true);
            i++;
        }
        return randEntrances;
    }


    public static Room randomizeRoom(List<Item> items, Zoo animals, Set<String> entrances,
                                     int rangeItem, int rangeAnimals, int minOpenEntrances){

        //randomizeEntranceStates(entrances,maxOpenEntrances);
        return new Room(randomizeRangeList(items,rangeItem,false),
                randomizeZoo(animals,rangeAnimals),
                Randomizer.randomizeEntrances(entrances, minOpenEntrances));
    }
}

