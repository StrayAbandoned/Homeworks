import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {

        /// Задание 1:
        Integer[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println("Массив до изменения:");
        for (Integer i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Массив после изменения:");
        swap(arr, 2, 5);
        for (Integer i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        /// Задание 2:
        String[] arr2 = {"one", "two", "three", "four", "five"};
        System.out.println("Переданный массив :");
        for (String s : arr2) {
            System.out.print(s + " ");
        }
        System.out.println();
        ArrayList<String> al = toArrayList(arr2);
        System.out.println("После метода toArrayList: " + al);

        /// Задание 3:
        Box<Apple> box1 = new Box<>();
        Box<Apple> box2 = new Box<>();
        Box<Orange> box3 = new Box<>();


        box1.addFruit(new Apple());
        box1.addFruit(new Apple());
        box1.addFruit(new Apple());

        box2.addFruit(new Apple());
        box2.addFruit(new Apple());
        box2.addFruit(new Apple());
        box2.addFruit(new Apple());

        box3.addFruit(new Orange());
        box3.addFruit(new Orange());


        System.out.println("Вес 1 коробки: " + box1.getWeight());
        System.out.println("Вес 2 коробки: " + box2.getWeight());
        System.out.println("Вес 3 коробки: " + box3.getWeight());



        System.out.println("Вес 1 коробки равен весу 2 коробки: " + box1.compare(box2));
        System.out.println("Вес 1 коробки равен весу 3 коробки: " + box1.compare(box3));
        System.out.println("Вес 2 коробки равен весу 3 коробки: " + box2.compare(box3));

        box1.moveFruitsFrom(box2);


        System.out.println("Вес 1 коробки после пересыпания: " + box1.getWeight());
        System.out.println("Вес 2 коробки после пересыпания: " + box2.getWeight());


    }

    public static <T> T[] swap(T[] arr, int one, int two) {
        if (one < arr.length && one >= 0 && two < arr.length && two >= 0) {
            T temp = arr[one];
            arr[one] = arr[two];
            arr[two] = temp;
        } else {
            System.out.println("Введены некорректные индексы. Массив не изменен!");
        }
        return arr;
    }

    public static <T> ArrayList<T> toArrayList(T[] arr) {
        return new ArrayList<T>(Arrays.asList(arr));

    }
}

