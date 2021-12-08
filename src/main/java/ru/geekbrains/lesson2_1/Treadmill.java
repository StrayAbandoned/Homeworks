package ru.geekbrains.lesson2_1;

public class Treadmill implements Challenge {
    private int parametr;
    Treadmill(int parametr){
        this.parametr = parametr;
    }
    @Override
    public boolean overcome(Participants a, int parametr) {
        return a.run(parametr);
    }

    @Override
    public int getParametr() {
        return parametr;
    }
}