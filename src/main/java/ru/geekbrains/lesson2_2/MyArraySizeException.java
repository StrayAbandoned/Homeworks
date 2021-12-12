package ru.geekbrains.lesson2_2;

public class MyArraySizeException extends IllegalArgumentException{
    private String causeOfException = "передан некорректный массив";

    public String getCauseOfException() {
        return causeOfException;
    }
}