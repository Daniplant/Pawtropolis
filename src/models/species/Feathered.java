package models.species;

import models.Animal;

import java.time.LocalDate;

public abstract class Feathered extends Animal {

    private float wingOpening;

    /**
     *
     * @param name
     * @param favoriteFood
     * @param age
     * @param weight
     * @param height
     * @param zooEntrance
     * @param wingOpening
     */
    public Feathered(String name, String favoriteFood, int age, float weight, float height, LocalDate zooEntrance, float wingOpening) {
        super(name, favoriteFood, age, weight, height, zooEntrance);
        this.wingOpening = wingOpening;
    }

    public float getWingOpening() {
        return wingOpening;
    }

    public void setWingOpening(float wingOpening) {
        this.wingOpening = wingOpening;
    }
}
