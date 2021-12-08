package ru.geekbrains.lesson2_1;

public class Cat implements Participants{
    private String name;
    private final int MAX_JUMP = 2;
    private final int MAX_RUN = 1000;

    Cat (String name) {
        this.name = name;
    }

    @Override
    public boolean jump(int distance) {
        if (distance<=MAX_JUMP) {
            System.out.println("Cat " + name +" has jumed");
            return true;
        }
        else {
            System.out.println("Cat " + name +" has not jumed");
            return false;
        }
    }

    @Override
    public boolean run(int distance) {
        if (distance<=MAX_RUN) {
            System.out.println("Cat " + name +" has run");
            return true;
        }
        else {
            System.out.println("Cat " + name +" has not run");
            return false;
        }
    }
}
