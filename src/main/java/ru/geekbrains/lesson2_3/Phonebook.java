package ru.geekbrains.lesson2_3;

import java.util.*;

public class Phonebook {

    private Map<List<String>, String> map = new HashMap<>();

    public void add(String s, List<String> arr) {
        map.put(arr, s);
    }

    public void get(String s) {

        List<String> k = new ArrayList<>();
        if (map.containsValue(s)) {
            for (List<String> ls : map.keySet()) {
                if (s.equals(map.get(ls))) {
                    k.addAll(ls);
                }
            }
            System.out.println("Номера телефонов абонента по фамилии \"" + s + "\": " + k);
        } else {
            System.out.println("Абонента по фамилии \"" + s + "\" не найдено ");
        }
    }

    public void getVersionTwo(String s) {
        List<List<String>> phone = new ArrayList<>();
        if (map.containsValue(s)) {
            for (Map.Entry<List<String>, String> a : map.entrySet()) {
                if (s.equals(a.getValue())) {
                    phone.add(a.getKey());
                }
            }
            System.out.println("Номера телефонов абонента по фамилии \"" + s + "\": " + phone);
        } else {
            System.out.println("Абонента по фамилии \"" + s + "\" не найдено ");
        }
    }
}