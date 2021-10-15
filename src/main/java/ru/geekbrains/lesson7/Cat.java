package ru.geekbrains.lesson7;

public class Cat {
    private String name;
    private int appetite;
    private boolean isFull;

    public Cat(String name, int appetite) {
        this.name = name;
        if (appetite <= 0) this.appetite = 5;
        else this.appetite = appetite;
        isFull = false;
    }

    public void eat(Plate plate) {
        if (plate.getFoodAmount() >= appetite) {
            plate.decreaseFood(appetite);
            isFull = true;
            System.out.println(name + " съел " + appetite + " шариков. Сытость: " + isFull);
        } else {
            System.out.println(name + " не может покушать, потому что его аппетит - " + appetite + ". Ему не хватает еды :( Сытость: " + isFull);
            plate.increaseFood(appetite);
            plate.decreaseFood(appetite);
            isFull = true;
            System.out.println(name + " съел " + appetite + " шариков. Сытость: " + isFull);

        }

    }
}
