package ru.geekbrains.lesson3_6;

public class ArrayMethods {
    public int[] methodOne(int[] arr) throws RuntimeException{
        int[] finalArr;
        int index = arr.length-1;
        int size = 0;
        boolean b = true;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==4){
                b=false;
            }
        }
        if(b){
            throw new RuntimeException();
        }
        for (int i = arr.length-1; i>-1; i--){
            if(arr[i]==4){
                index = i;
                break;
            }
        }
        size = arr.length-1-index;
        finalArr = new int[size];
        System.arraycopy(arr,index+1, finalArr,0, size);
        return finalArr;
    }
    public boolean methodTwo(int[] arr){
        boolean a = false, b = false, c = true;
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i]==1){
                a = true;
            }
            if(arr[i]==4){
                b = true;
            }
            if(arr[i]!=1&&arr[i]!=4){
                c = false;
            }
        }
        return a&&b&&c;
    }
}
