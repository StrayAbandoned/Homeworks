package ru.geekbrains.lesson5;

public class Homework5 {
    public static void main(String[] args) {
        Staff[] staff = new Staff[5];
        staff[0] = new Staff("Иванов Иван Иванович", "Директор", "ivanov@mail.ru", "8-800-123-45-67", 100000, 45);
        staff[1] = new Staff("Александрова Александра Александровна", "Зам.директора", "aleksandrova@mail.ru", "8-800-123-45-68", 90000, 35);
        staff[2] = new Staff("Петров Петр Петрович", "Тех.директор", "petrov@mail.ru", "8-800-123-45-69", 80000, 30);
        staff[3] = new Staff("Викторова Виктория Викторовна", "Бухгалтер", "victorova@mail.ru", "8-800-123-45-70", 85000, 51);
        staff[4] = new Staff("Сергеев Сергей Сергеевич", "Разработчик", "sergeev@mail.ru", "8-800-123-45-71", 90000, 30);

        for (int i = 0; i < staff.length; i++) {
            if (staff[i].getAge()>40){
                staff[i].info();
            }
        }
    }
}
