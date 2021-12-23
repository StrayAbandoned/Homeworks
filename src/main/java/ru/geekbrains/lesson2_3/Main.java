package ru.geekbrains.lesson2_3;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        //задание 1:

        String[] arr = {"один", "два", "три", "четыре", "пять", "пять", "шесть",
                "семь", "восемь", "восемь", "восемь", "девять",
                "десять", "одиннадцать", "двенадцать"};
        Map<String, Integer> map = new LinkedHashMap<>();
        Set <String> set = new LinkedHashSet<>(Arrays.asList(arr));

        System.out.print("Переданный массив: ");
        for (String s: arr) {
            System.out.print(s + " ");
        }
        System.out.println("\nУникальные слова: "+ set);
        System.out.print("Количество повторений слова: ");

        for (int i = 0; i < arr.length; i++) {
            Integer count = map.getOrDefault(arr[i], 0);
            map.put((arr[i]), ++count);
        }
        System.out.println(map);
        System.out.println("Уникальных слов: " + map.size());

        //Задание 2:

        Phonebook phonebook = new Phonebook();
        phonebook.add("Иванов", Arrays.asList("8-999-123-45-67"));
        phonebook.add("Петров", Arrays.asList ("8-999-625-12-13","8-910-987-65-43"));
        phonebook.add("Сидоров", Arrays.asList ("8-999-198-56-74", "8-913-654-87-96","8-965-654-98-23"));
        phonebook.add("Иванов", Arrays.asList("8-999-123-45-99","8-915-654-85-96"));
        phonebook.get("Иванов");
        phonebook.get("Петров");
        phonebook.get("Ковалев");
        phonebook.getVersionTwo("Иванов");
    }
}
