package ru.geekbrains.lesson7;

public class Plate {

    private int foodAmount;

    public Plate(int foodAmount) {
        if (foodAmount > 0) this.foodAmount = foodAmount;
        else this.foodAmount = 50;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void decreaseFood(int appetite) {
        if (foodAmount >= appetite) foodAmount -= appetite;

    }

    public void increaseFood(int appetite) {
        if (foodAmount < appetite) {
            System.out.println("Мы добавили ровно столько еды, чтобы котику хватило поесть - " + (appetite - foodAmount));
            foodAmount = appetite;
        }
    }

    public void info() {
        System.out.println("В тарелке " + foodAmount + " шариков корма");
    }
}
