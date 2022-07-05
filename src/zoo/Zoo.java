package zoo;

import models.Animal;
import models.Tailed;
import models.species.Feathered;

import java.util.*;

public class Zoo {

    private int zooSize;

    private Map<Class<? extends Animal>, List<? extends Animal>> animalsSpecie;

    //private List<? extends Animal> animals;


    public Zoo() {
        this.animalsSpecie = new HashMap<>();
        zooSize = 0;
        //this.animals = new ArrayList<>();
        System.out.println(Arrays.toString(Animal.class.getMethods()));
    }

    public <T extends Animal> void addAnimal(T animal) {
        List<T> alreadyPresentList = (List<T>) animalsSpecie.get(animal.getClass());
        if (alreadyPresentList == null) {
            alreadyPresentList = new ArrayList<>();
        }
        alreadyPresentList.add(animal);
        animalsSpecie.put(animal.getClass(), alreadyPresentList);
        //System.out.println(animal.getClass().getPackage().getClass());
        zooSize++;
    }

    // metodo che data una classe di animale mi da la lista di animali di
    // quella specifica classe dalla mappa
    public <T extends Animal> List<T> getAllAnimalsFrom(Class<T> specie) {
        // T si collega agli altri T Dani quindi se gli dai un tipo "leone"
        // da leone
        if (this.animalsSpecie.get(specie) != null) {
            return new ArrayList<>((List<T>) this.animalsSpecie.get(specie));
        }
        return null;

        //return (List<T>) this.animalsSpecie.get(specie);
    }

    public <T extends Animal> Map<String, List<T>> getAllAnimals() {
        /*
            Ho riflettuto riguardo al dare la copia oppure la vera mappa dello zoo:
            Secondo me non ha molto senso se, io gestore, offro a qualcuno la possibilità di cambiare
            "il mio zoo" quindi nonostante sia un operazione dispensiona perché credo che se qualcuno
            "in un universo parallero dove qualcuno usarelà queste funzioni", il programma dovrebbe dare la
            possibilità di dare le informazioni del proprio zoo
         */
        HashMap<String, List<T>> newHashMap = null;
        if (this.animalsSpecie != null) {
            newHashMap = new HashMap<>();
            for (Map.Entry<Class<? extends Animal>, List<? extends Animal>> entry : this.animalsSpecie.entrySet()) {
                newHashMap.put(entry.getKey().getSimpleName(), (List<T>) entry.getValue());
            }
        }
        return newHashMap;
    }

    public <T extends Animal> void removeAnimalFrom(Class<T> specie, int index) {
        if (this.animalsSpecie.get(specie).size() > index && index >= 0) {
            this.animalsSpecie.get(specie).remove(index);
        }
/*        if (this.animalsSpecie.get(specie).size() == 0){
            // canceling the key witch holds nothing
            removeAllAnimalsFrom(specie);
        }*/

    }

    public <T extends Animal> void removeAllAnimalsFrom(Class<T> specie) {
        this.animalsSpecie.remove(specie);
    }

    public void removeAllAnimals() {
        this.animalsSpecie.clear();
    }

    public int getZooSize() { return zooSize;}

    // Generic Animal Requests
   public void printAllAnimals() {
        int i;
        boolean areTailed;
        boolean areFeathered;
       StringBuilder tmpStr = new StringBuilder();
       for (Map.Entry<Class<? extends Animal>, List<? extends Animal>> set : animalsSpecie.entrySet()) {
            areTailed = false;
            areFeathered = false;
           // trying to optimize the search by using the first element of the class
           // instead of iterating each item for checking which type it is
           // We just simply use the first element because the hashmap is ordered
           if (set.getValue().get(0) instanceof Tailed){
               areTailed = true;
           } else if (set.getValue().get(0) instanceof Feathered) {
               areFeathered = true;
           }
           for (i = 0; i < set.getValue().size(); i++){
               tmpStr.append("Name : ").append(set.getValue().get(i).getName()).append("\n")
                       .append("Favorite food : ").append(set.getValue().get(i).getFavoriteFood()).append("\n")
                       .append("Age : ").append(set.getValue().get(i).getAge()).append("\n")
                       .append("Weight : ").append(set.getValue().get(i).getWeight()).append("\n")
                       .append("Height : ").append(set.getValue().get(i).getHeight()).append("\n")
                       .append("Zoo entrance : ").append(set.getValue().get(i).getZooEntrance()).append("\n");
               if (areTailed){
                   tmpStr.append("Tail lenght : ").append(((Tailed) set.getValue().get(i)).getTailLenght());
               }
               if (areFeathered){
                   tmpStr.append("Wing opening : ").append(((Feathered) set.getValue().get(i)).getWingOpening());
               }
               tmpStr.append("\n");

               System.out.println(tmpStr);
               tmpStr.replace(0,tmpStr.capacity(),"");
           }

       }
   }
    public <T extends Animal> T getTheHeaviestAnimal() throws CloneNotSupportedException{
        int heaviest = 0;
        Class<? extends Animal> entryHeaviest = null;
        if (this.animalsSpecie.isEmpty()) {
            return null;
        }
        /*
            Getting the first entry
            And yes I tried to use optional, but I didn't know
            how to use it properly
         */

        while (entryHeaviest == null) {
            for (Class<? extends Animal> entry : this.animalsSpecie.keySet()) {
                entryHeaviest = entry;
            }
        }

        for (Class<? extends Animal> entry : this.animalsSpecie.keySet()) {
            for (int i = 0; i < this.animalsSpecie.get(entry).size(); i++) {
                if (this.animalsSpecie.get(entryHeaviest).get(heaviest).getWeight() < this.animalsSpecie.get(entry).get(i).getWeight()) {
                    heaviest = i;
                    if (entryHeaviest != entry) {
                        entryHeaviest = entry;
                    }
                }
            }
        }
        return (T) this.animalsSpecie.get(entryHeaviest).get(heaviest).cloneAnimal();
    }

    public <T extends Animal> T getTheLightestAnimal() throws CloneNotSupportedException {
        int lightest = 0;

        T animalFound;
        Class<? extends Animal> entryHeaviest = null;

        if (this.animalsSpecie.isEmpty()) {
            return null;
        }
        /*
            Getting the first entry
            And yes I tried to use Optional, but I didn't know
            how to use it properly
         */

        while (entryHeaviest == null) {
            for (Class<? extends Animal> entry : this.animalsSpecie.keySet()) {
                entryHeaviest = entry;
            }
        }

        for (Class<? extends Animal> entry : this.animalsSpecie.keySet()) {
            for (int i = 0; i < this.animalsSpecie.get(entry).size(); i++) {
                if (this.animalsSpecie.get(entryHeaviest).get(lightest).getWeight() > this.animalsSpecie.get(entry).get(i).getWeight()) {
                    lightest = i;
                    if (entryHeaviest != entry) {
                        entryHeaviest = entry;
                    }
                }
            }
        }

        animalFound = (T) this.animalsSpecie.get(entryHeaviest).get(lightest).cloneAnimal();
        return animalFound;
    }

    public <T extends Animal> T getTheTallestAnimal() throws CloneNotSupportedException {
        int tallest = 0;
        Class<? extends Animal> entryHeaviest = null;
        if (this.animalsSpecie.isEmpty()) {
            return null;
        }
        /*
            Getting the first entry
            And yes I tried to use Optional, but I didn't know
            how to use it properly
         */

        while (entryHeaviest == null) {
            for (Class<? extends Animal> entry : this.animalsSpecie.keySet()) {
                entryHeaviest = entry;
            }
        }

        for (Class<? extends Animal> entry : this.animalsSpecie.keySet()) {
            for (int i = 0; i < this.animalsSpecie.get(entry).size(); i++) {
                if (this.animalsSpecie.get(entryHeaviest).get(tallest).getHeight() > this.animalsSpecie.get(entry).get(i).getHeight()) {
                    tallest = i;
                    if (entryHeaviest != entry) {
                        entryHeaviest = entry;
                    }
                }
            }
        }
        return (T) this.animalsSpecie.get(entryHeaviest).get(tallest).cloneAnimal();
    }

    public <T extends Animal> T getTheSmallestAnimal() throws CloneNotSupportedException{
        int smallest = 0;
        Class<? extends Animal> entryHeaviest = null;
        if (this.animalsSpecie.isEmpty()) {
            return null;
        }
        /*
            Getting the first entry
            And yes I tried to use Optional, but I didn't know
            how to use it properly
         */

        while (entryHeaviest == null) {
            for (Class<? extends Animal> entry : this.animalsSpecie.keySet()) {
                entryHeaviest = entry;
            }
        }

        for (Class<? extends Animal> entry : this.animalsSpecie.keySet()) {
            for (int i = 0; i < this.animalsSpecie.get(entry).size(); i++) {
                if (this.animalsSpecie.get(entryHeaviest).get(smallest).getHeight() > this.animalsSpecie.get(entry).get(i).getHeight()) {
                    smallest = i;
                    if (entryHeaviest != entry) {
                        entryHeaviest = entry;
                    }
                }
            }
        }
        return (T) this.animalsSpecie.get(entryHeaviest).get(smallest).cloneAnimal();
    }

    public <T extends Animal> T getTheWidestAnimal() throws CloneNotSupportedException {
        int widest = 0;
        Class<? extends Animal> entryHeaviest = null;
        if (this.animalsSpecie.isEmpty()) {
            return null;
        }
        /*
            Getting the first entry
            And yes I tried to use Optional, but I didn't know
            how to use it properly
         */

        while (entryHeaviest == null) {
            for (Class<? extends Animal> entry : this.animalsSpecie.keySet()) {
                entryHeaviest = entry;
            }
        }

        for (Class<? extends Animal> entry : this.animalsSpecie.keySet()) {
            for (int i = 0; i < this.animalsSpecie.get(entry).size(); i++) {
                if (this.animalsSpecie.get(entryHeaviest).get(widest).getHeight() < this.animalsSpecie.get(entry).get(i).getHeight()) {
                    widest = i;
                    if (entryHeaviest != entry) {
                        entryHeaviest = entry;
                    }
                }
            }
        }
        return (T) this.animalsSpecie.get(entryHeaviest).get(widest).cloneAnimal();
    }

    public <T extends Animal> T getTheTightestAnimal() throws CloneNotSupportedException{
        int tightest = 0;
        Class<? extends Animal> entryHeaviest = null;
        if (this.animalsSpecie.isEmpty()) {
            return null;
        }
        /*
            Getting the first entry
            And yes I tried to use Optional, but I didn't know
            how to use it properly
         */

        while (entryHeaviest == null) {
            for (Class<? extends Animal> entry : this.animalsSpecie.keySet()) {
                entryHeaviest = entry;
            }
        }

        for (Class<? extends Animal> entry : this.animalsSpecie.keySet()) {
            for (int i = 0; i < this.animalsSpecie.get(entry).size(); i++) {
                if (this.animalsSpecie.get(entryHeaviest).get(tightest).getHeight() > this.animalsSpecie.get(entry).get(i).getHeight()) {
                    tightest = i;
                    if (entryHeaviest != entry) {
                        entryHeaviest = entry;
                    }
                }
            }
        }
        return (T) this.animalsSpecie.get(entryHeaviest).get(tightest).cloneAnimal();
    }

    // Specific request

    /*
    TODO : Modificare il codice in modo tale che non debba attaccarsi ai felini
     se vogliamo fare i precisi non tutti gli animali codati sono dei felini (Le scimmie per esempio).
     Per adesso se non vengono introdotte altre razze che sono animali codati non felini questo è decente
     ma non mi soddisfa ma non riesco a trovare un altra soluzione
 */
    public <T extends Animal> T getTheLongestAnimalTail() throws CloneNotSupportedException {

        if (this.animalsSpecie.isEmpty()) {
            return null;
        }
        Map<Class<? extends Animal>, List<? extends Animal>> filteredSpecie = new HashMap<>();
        Class<? extends Animal> filteredSpecieKey = null;
        int filteredSpecieIndex = 0;
        Tailed result;
        Tailed checker;



        // filter the map, for checking only things that they make sense to check
        // n**2
        for (Map.Entry<Class<? extends Animal>, List<? extends Animal>> entry : this.animalsSpecie.entrySet()){
            if ((entry.getValue().get(0) instanceof Tailed)){
                filteredSpecie.put(entry.getKey(), entry.getValue());
                if (filteredSpecieKey == null){
                    filteredSpecieKey =  entry.getKey();
                }
            }
        }


        // find the searched value
        // n**2
        for (Map.Entry<Class<? extends Animal>, List<? extends Animal>> entry : filteredSpecie.entrySet()){
            for (int i = 0; i < entry.getValue().size(); i++){
                result = (Tailed) filteredSpecie.get(filteredSpecieKey).get(filteredSpecieIndex);
                checker = (Tailed) filteredSpecie.get(entry.getKey()).get(i);
                if ( result.getTailLenght() < checker.getTailLenght()){
                    filteredSpecieIndex  = i;
                    filteredSpecieKey = entry.getKey();
                }
            }
         }

        return (T) this.animalsSpecie.get(filteredSpecieKey).get(filteredSpecieIndex).cloneAnimal();
        //return (T) this.animalsSpecie.get(filteredSpecieKey).get(filteredSpecieIndex).cloneAnimal();
    }

}
