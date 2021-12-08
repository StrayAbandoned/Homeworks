package ru.geekbrains.lesson2_1;

public class Main {
    public static void main(String[] args) {
        Participants[] participants = {
                new Human("Kevin"),
                new Cat("Barsik"),
                new Robot("Android")
        };

        Challenge[] challenges = {
                new Treadmill(1000),
                new Wall(1),
                new Treadmill(1500),
                new Wall(2)
        };

        for (Participants p : participants) {
            for (Challenge ch : challenges) {
                int parametr = ch.getParametr();
                if (ch.overcome(p, parametr)){
                } else break;
            }

        }

    }

}
