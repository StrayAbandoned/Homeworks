package ru.geekbrains.lesson2;

public class Homework2 {
    public static void main(String[] args) {

    }

    public static boolean checkSum(int a, int b) {
        return (a + b) >= 10 && (a + b) <= 20;
    }

    public static void printSign(int a) {
        System.out.println(a < 0 ? "Число отрицательное" : "Число положительное");
    }

    public static boolean isNegative(int a) {
        return a < 0;
    }

    public static void printString(String s, int a) {
        for (int i = 0; i < a; i++) {
            System.out.println(s);
        }
    }

    public static boolean isLeapYear(int a) {
        return (a % 4 == 0 && a % 100 != 0) || a % 400 == 0;
    }
}
