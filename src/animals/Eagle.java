package animals;

import models.species.Feathered;

import java.time.LocalDate;

public class Eagle extends Feathered {
    public Eagle(String name, String favoriteFood, int age, float weight, float height, LocalDate zooEntrance, float wingOpening) {
        super(name, favoriteFood, age, weight, height, zooEntrance, wingOpening);
    }
}
