package ru.geekbrains.lesson2_1;

public class Human implements Participants{
    private String name;
    private final int MAX_JUMP = 1;
    private final int MAX_RUN = 500;

    Human (String name) {
        this.name = name;
    }

    @Override
    public boolean jump(int distance) {
        if (distance<=MAX_JUMP) {
            System.out.println("Human " + name +" has jumed");
            return true;
        }
        else {
            System.out.println("Human " + name +" has not jumed");
            return false;
        }
    }

    @Override
    public boolean run(int distance) {
        if (distance<=MAX_RUN) {
            System.out.println("Human " + name +" has run");
            return true;
        }
        else {
            System.out.println("Human " + name +" has not run");
            return false;
        }
    }
}