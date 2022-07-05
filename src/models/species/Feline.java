package models.species;

import models.Animal;

import java.time.LocalDate;

public abstract class Feline extends Animal {


    /**
     *
     * @param name
     * @param favoriteFood
     * @param age
     * @param weight
     * @param height
     * @param zooEntrance
     * @param tailLenght
     */
    public Feline(String name, String favoriteFood, int age, float weight, float height, LocalDate zooEntrance) {
        super(name, favoriteFood, age, weight, height, zooEntrance);
    }
}
