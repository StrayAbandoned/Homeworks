public class Main {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        firstMethod();
        try {
            secondMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void firstMethod() {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1.0f;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();
        System.out.println("Время выполнение задачи в один поток: " + (b - a) + "ms");
        System.out.println("arr[7000000]: " + arr[7000000]);
    }

    public static void secondMethod() throws InterruptedException {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1.0f;
        }
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        long a = System.currentTimeMillis();


        Thread t1 = new Thread(() -> {
            System.arraycopy(arr, 0, arr1, 0, h);
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(arr1, 0, arr, 0, h);
        });
        Thread t2 = new Thread(() -> {
            System.arraycopy(arr, h, arr2, 0, h);
            for (int i = 0; i < arr2.length; i++) {
                arr2[i] = (float) (arr[i] * Math.sin(0.2f + (i + h) / 5) * Math.cos(0.2f + (i + h) / 5) * Math.cos(0.4f + (i + h) / 2));
            }
            System.arraycopy(arr2, 0, arr, h, h);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long b = System.currentTimeMillis();
        System.out.println("Время выполнение задачи в два потока: " + (b - a) + "ms");
        System.out.println("arr[7000000]: " + arr[7000000]);

    }
}
