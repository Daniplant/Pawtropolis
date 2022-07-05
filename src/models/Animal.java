package models;

import java.time.LocalDate;

public abstract class Animal implements Cloneable {

    private String name;
    private String favoriteFood;
    private int age;
    private float weight;
    private float height;
    private LocalDate zooEntrance;

    public Object cloneAnimal() throws CloneNotSupportedException{
        return super.clone();
    }

    /**
     *
     * @param name
     * @param favoriteFood
     * @param age
     * @param weight
     * @param height
     * @param zooEntrance
     */
    public Animal(String name, String favoriteFood, int age, float weight, float height, LocalDate zooEntrance) {
        this.name = name;
        this.favoriteFood = favoriteFood;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.zooEntrance = zooEntrance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favorite_food) {
        this.favoriteFood = favorite_food;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public LocalDate getZooEntrance() {
        return zooEntrance;
    }

    public void setZooEntrance(LocalDate zooEntrance) {
        this.zooEntrance = zooEntrance;
    }


}
