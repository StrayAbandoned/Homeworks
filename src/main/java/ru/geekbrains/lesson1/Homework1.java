package ru.geekbrains.lesson1;

public class Homework1 {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();

    }
    public static void printThreeWords(){
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }
    public static void checkSumSign(){
        int a = -20;
        int b = 30;
        System.out.println((a+b)>=0?"Сумма положительная": "Сумма отрицательная");
    }
    public static void printColor(){
        int value = 50;
        if (value<=0) {
            System.out.println("Красный");
        } else {
            System.out.println(value>100?"Зеленый":"Желтый");
        }
    }
    public static void compareNumbers(){
        int a =  45;
        int b = 35;
        System.out.println(a>=b?"a>=b":"a<b");
    }
}
