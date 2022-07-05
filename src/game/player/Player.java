package game.player;

import game.bag.Bag;

public class Player {

    private String name;
    private int lifePoints;
    private Bag bag;
    public Player(String name, int health, Bag bag) {
        this.name = name;
        this.lifePoints = health;
        this.bag = bag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }



}
