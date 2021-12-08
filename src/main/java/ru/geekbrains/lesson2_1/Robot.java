package ru.geekbrains.lesson2_1;

public class Robot implements Participants{
    private String name;
    private final int MAX_JUMP = 1;
    private final int MAX_RUN = 2000;

    Robot (String name) {
        this.name = name;
    }

    @Override
    public boolean jump(int distance) {
        if (distance<=MAX_JUMP) {
            System.out.println("Robot " + name +" has jumed");
            return true;
        }
        else {
            System.out.println("Robot " + name +" has not jumed");
            return false;
        }

    }

    @Override
    public boolean run(int distance) {
        if (distance<=MAX_RUN) {
            System.out.println("Robot " + name +" has run");
            return true;
        }
        else {
            System.out.println("Robot " + name +" has not run");
            return false;
        }
    }
}
