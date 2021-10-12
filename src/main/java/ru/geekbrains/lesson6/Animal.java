package ru.geekbrains.lesson6;

public abstract class Animal {
    protected String name;
    private int runDistance;
    private int swimDistance;
    private static int animalCount = 0;
    public int getRunDistance() {
        return runDistance;
    }

    public int getSwimDistance() {
        return swimDistance;
    }

    protected Animal(String name, int runDistance, int swimDistance) {
        this.name = name;
        this.runDistance = runDistance;
        this.swimDistance = swimDistance;
        animalCount++;
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public abstract void run(int runDistance);

    public abstract void swim(int swimDistance);
}
