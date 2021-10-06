package ru.geekbrains.lesson5;

public class Staff {
    private String name;
    private String position;
    private String mail;
    private String phoneNumber;
    private int salary;
    private int age;

    public Staff(String name, String position, String mail, String phoneNumber, int salary, int age) {
        this.name = name;
        this.position = position;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public Staff() {
        name = "Неизвестный сотрудник";
        position = "Неизвестно";
        mail = "Неизвестно";
        phoneNumber = "8-000-000-00-00";
        salary = 0;
        age = 0;

    }

    public void info() {
        System.out.println("Имя: " + name);
        System.out.println("Должность: " + position);
        System.out.println("E-mail: " + mail);
        System.out.println("Телефон: " + phoneNumber);
        System.out.println("Зарплата: " + salary);
        System.out.println("Возраст: " + age);
        System.out.println();
    }

    public int getAge() {
        return age;
    }

}
