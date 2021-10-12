package ru.geekbrains.lesson6;

public class Cat extends Animal {
    private static int catCount = 0;

    public Cat(String name, int runDistance, int swimDistance) {
        super(name, runDistance, swimDistance);
        catCount++;

    }

    @Override
    public void run(int runDistance) {
        if (runDistance >= 0 && runDistance <= 200) {
            System.out.println(name + " пробежал " + runDistance + "м");
        } else System.out.println(name + " не может бежать " + runDistance + "м");
    }

    @Override
    public void swim(int swimDistance) {
        System.out.println(name + " не умеет плавать");
    }

    public static int getCatCount() {
        return catCount;
    }
}
