package ru.geekbrains.lesson2_2;

public class Main {
    static final int ARR_SIZE = 4;
    public static void main(String[] args) {
        String arr[][] = {
                {"1", "2", "3", "4"},
                {"2", "3", "4", "5"},
                {"3", "4", "5", "6"},
                {"4", "5", "6", "7"},
        };
        String arr1[][] = {
                {"1", "2", "3", "4"},
                {"2", "3f", "4", "5"},
                {"3", "4", "5", "6"},
                {"4", "5", "6", "7"},
        };
        String arr2[][] = {
                {"1", "2", "3", "4"},
                {"2", "3", "4", "5"},
                {"3", "4", "5", "6", "7"},
                {"4", "5", "6", "7"},
        };

        try {
            System.out.println("Результат: " + getSumOfArrayElements(arr));
        } catch (MyArraySizeException e){
            System.out.println("Exception: " + e.getClass().getName() +" - "+ e.getCauseOfException());
        } catch (MyArrayDataException e){
            System.out.printf("Exception: %s - неверное значение в элементе массива с индексами: [%d][%d]",e.getClass().getName(), e.getI(), e.getJ());
            System.out.println();
        }

        try {
            System.out.println("Результат: " + getSumOfArrayElements(arr1));
        } catch (MyArraySizeException e){
            System.out.println("Exception: " + e.getClass().getName() +" - "+ e.getCauseOfException());
        } catch (MyArrayDataException e){
            System.out.printf("Exception: %s - неверное значение в элементе массива с индексами: [%d][%d]",e.getClass().getName(), e.getI(), e.getJ());
            System.out.println();
        }

        try {
            System.out.println("Результат: " + getSumOfArrayElements(arr2));
        } catch (MyArraySizeException e){
            System.out.println("Exception: " + e.getClass().getName() +" - "+ e.getCauseOfException());
        } catch (MyArrayDataException e){
            System.out.printf("Exception: %s - неверное значение в элементе массива с индексами: [%d][%d]",e.getClass().getName(), e.getI(), e.getJ());
            System.out.println();
        }


    }
    public static int getSumOfArrayElements(String arr[][]) throws MyArraySizeException, MyArrayDataException{
        int result = 0;
        int number;
        for (int i = 0; i < arr.length; i++) {
            if (arr.length!=ARR_SIZE||arr[i].length!=ARR_SIZE){
                throw new MyArraySizeException();
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try{
                    number = Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e){
                    throw new MyArrayDataException(i,j);
                }
                result+=number;
            }

        }
        return result;
    }

}
