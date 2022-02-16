package ru.geekbrains.lesson3_7;

public class Main {
    public static void main(String[] args)  {
        Class<TestClass> test = TestClass.class;
        try {
            MyTest.start(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}