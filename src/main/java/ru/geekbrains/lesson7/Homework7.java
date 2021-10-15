package ru.geekbrains.lesson7;

public class Homework7 {
    public static void main(String[] args) {
        Plate plate = new Plate(-50);

        Cat[] cats = {
                new Cat("Барсик", -5),
                new Cat("Мурзик", 10),
                new Cat("Пушок", 15),
                new Cat("Снежок", 21),
                new Cat("Дымок", 25)};

        plate.info();

        for (Cat i : cats) {
            i.eat(plate);
            plate.info();
        }
    }
}
