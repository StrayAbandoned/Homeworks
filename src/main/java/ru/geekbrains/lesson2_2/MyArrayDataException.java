package ru.geekbrains.lesson2_2;

public class MyArrayDataException extends NumberFormatException{
    private int i, j;
    MyArrayDataException(int i, int j){
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
