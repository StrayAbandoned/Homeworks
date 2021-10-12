package ru.geekbrains.lesson6;

public class Dog extends Animal {
    private static int dogCount = 0;

    public Dog(String name, int runDistance, int swimDistance) {

        super(name, runDistance, swimDistance);
        dogCount++;
    }


    @Override
    public void run(int runDistance) {
        if (runDistance >= 0 && runDistance <= 500) {
            System.out.println(name + " пробежал " + runDistance + "м");
        } else System.out.println(name + " не может бежать " + runDistance + "м");
    }


    @Override
    public void swim(int swimDistance) {
        if (swimDistance > 0 && swimDistance < 10) {
            System.out.println(name + " проплыл(а) " + swimDistance + "м");
        } else {
            System.out.println(name + " не может плыть такую дистанцию.");
        }
    }

    public static int getDogCount() {
        return dogCount;
    }
}
