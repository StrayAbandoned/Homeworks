package ru.geekbrains.lesson6;

public class Homework6 {
    public static void main(String[] args) {
        int countCat = 0;
        int countDog = 0;
        int countAnimals = 0;

        Animal[] animal = {
                new Cat("Барсик", 100, 10),
                new Cat("Пушок", 250, 5),
                new Dog("Бобик", 100, 5),
                new Dog("Шарик", 200, 8),
                new Dog("Барбос", 600, 12)
        };

        for (Animal i: animal){
            i.run(i.getRunDistance());
            i.swim(i.getSwimDistance());
            countAnimals++;
            if (i instanceof Cat) countCat++;
            if (i instanceof Dog) countDog++;
        }

        printCount(countAnimals, countCat, countDog);

        printCount(Animal.getAnimalCount(),Cat.getCatCount(), Dog.getDogCount());

    }

    public static void printCount (int animal, int cat, int dog){
        System.out.println("Количество животных: "+animal+", из них:\n- коты - "+cat+ "\n- собаки - "+dog);
    }
}
