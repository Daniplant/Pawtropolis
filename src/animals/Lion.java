package animals;

import models.Tailed;
import models.species.Feline;

import java.time.LocalDate;

public class Lion extends Feline implements Tailed {
    private float tailLenght;

    public Lion(String name, String favoriteFood, int age, float weight, float height, LocalDate zooEntrance, float tailLength) {
        super(name, favoriteFood, age, weight, height, zooEntrance);
        this.tailLenght = tailLength;
    }

    @Override
    public float getTailLenght() {
        return tailLenght;
    }

    @Override
    public void setTailLenght(float tailLenght) {
        this.tailLenght = tailLenght;
    }
}
