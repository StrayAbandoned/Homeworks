package ru.geekbrains.lesson3;

import java.util.Arrays;
import java.util.Scanner;

public class Homework3 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Задание 1
        int[] task1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Задание 1.\nПереданный массив: " + Arrays.toString(task1));
        switchMethod(task1);
        System.out.println("Измененный массив после применения switchMethod: " + Arrays.toString(task1));

        //Задание 2
        System.out.println("Задание 2.\nСоздание и заполнение массива значениями с помощью метода createNewArray:\n" + Arrays.toString(createNewArray()));

        //Задание 3
        int[] task3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Задание 3.\nПереданный массив: " + Arrays.toString(task3));
        multiplyValues(task3);
        System.out.println("Измененный массив после применения multiplyValues: " + Arrays.toString(task3));

        //Задание 4: 2 версии
        int[][] task4 = new int[5][5];
        int[][] alternativeTask4 = new int[7][7];
        System.out.println("Задание 4.\nМетод fillOnes заполняет диагонали единицами, а метод printArray его отрисовывает:");
        fillOnes(task4);
        printArray(task4);
        System.out.println("Задание 4. Альтернативная версия\nМетод drawOnes принимает массив в качестве параметра, заполняет диагонали единицами непосредственно во время отрисовки:");
        drawOnes(alternativeTask4);

        //Задание 5
        System.out.println("Задание 5.\nВведите длину массива:");
        int len = scanner.nextInt();
        System.out.println("Введите число для заполнения массива:");
        int initialValue = scanner.nextInt();
        System.out.println("Ваш массив: " + Arrays.toString(customArray(len, initialValue)));

        //Задание 6
        int[] task6 = {-1, 2, -3, 4, -5, 6, -7, 8, -9, 0};
        System.out.println("Задание 6.\nПереданный массив: " + Arrays.toString(task6) + "\nМетод findMinAndMax находит экстремумы переданного ему массива и выводит их в консоль:");
        findMinAndMax(task6);

        //Задание 7
        int[] task7 = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println("Задание 7.\nПереданный массив: " + Arrays.toString(task7));
        System.out.println("В массиве есть место, в котором сумма левой и правой части массива равны: " + findDelimiter(task7));

        //Задание 8
        int[] task8 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println("Задание 8.\nДемонстрация работы метода shift с положительным сдвигом:");
        shift(task8, 3);
        System.out.println("Задание 8.1.\nДемонстрация работы метода shift с отрицательным сдвигом:");
        shift(task8, -3);
    }

    public static void switchMethod(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i] == 0 ? 1 : 0;
        }
    }

    public static int[] createNewArray() {
        int[] task2 = new int[100];
        for (int i = 0; i < task2.length; i++) {
            task2[i] = i + 1;
        }
        return task2;
    }

    public static void multiplyValues(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i] < 6 ? a[i] *= 2 : a[i];
        }
    }

    public static void drawOnes(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (i == j || ((i - 0) == (a[i].length - j - 1))) {
                    a[i][j] = 1;
                    System.out.print(a[i][j] + " ");
                } else System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void fillOnes(int[][] a) {
        for (int k = 0, j = a[k].length - 1; j >= 0 && k < a[k].length; j--, k++) {
            a[k][j] = 1;
            a[k][k] = 1;
        }
    }

    public static void printArray(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] customArray(int len, int initialValue) {
        int[] task5 = new int[len];
        for (int i = 0; i < len; i++) {
            task5[i] = initialValue;
        }
        return task5;
    }

    private static void findMinAndMax(int[] a) {
        int minValue = a[0], maxValue = a[0];
        for (int i = 1; i < a.length; i++) {
            minValue = a[i] < minValue ? a[i] : minValue;
            maxValue = a[i] > maxValue ? a[i] : maxValue;
        }
        System.out.println("Максимальное значение - " + maxValue);
        System.out.println("Минимальное значение - " + minValue);
    }

    public static boolean findDelimiter(int[] a) {
        int sumleft = 0, sumright = 0;
        boolean result = false;
        for (int i = 0; i < a.length - 1; i++) {
            sumleft += a[i];
            for (int j = a.length - 1; j > i; j--) {
                sumright += a[j];
            }
            if (sumleft == sumright) {
                return true;

            } else {
                sumright = 0;
            }
        }
        return false;
    }

    public static void shift(int[] a, int n) {
        System.out.println("Переданный массив: " + Arrays.toString(a));
        System.out.println("Сдвиг: " + n);
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                int temp1 = a[0];
                a[0] = a[a.length - 1];
                for (int j = a.length - 2; j > 0; j--) {
                    a[j + 1] = a[j];
                }
                a[1] = temp1;
            }
        }
        if (n < 0) {
            for (int i = 0; i > n; i--) {
                int temp1 = a[a.length - 1];
                a[a.length - 1] = a[0];
                for (int j = 0; j < a.length - 1; j++) {
                    a[j] = a[j + 1];

                }
                a[a.length - 2] = temp1;
            }
        }
        System.out.println("Новый массив: " + Arrays.toString(a));
    }
}
