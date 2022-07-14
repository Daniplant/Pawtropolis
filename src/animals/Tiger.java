package animals;

import models.features.Tailed;
import models.species.Feline;

import java.time.LocalDate;

public class Tiger extends Feline implements Tailed {

    private float tailLenght;
    public Tiger(String name, String favoriteFood, int age, float weight, float height, LocalDate zooEntrance, float tailLength) {
        super(name, favoriteFood, age, weight, height, zooEntrance);
        this.tailLenght = tailLength;
    }

    @Override
    public float getTailLenght() {
        return this.tailLenght;
    }

    @Override
    public void setTailLenght(float tailLenght) {
        this.tailLenght = tailLenght;
    }
}
