import java.util.concurrent.*;

public class Main {
    private static final int CARS_COUNT = 4;
    private static CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);
    private static CountDownLatch cd = new CountDownLatch(CARS_COUNT);
    private static CountDownLatch cd2 = new CountDownLatch(CARS_COUNT);

    public static CyclicBarrier getCb() {
        return cb;
    }

    public static CountDownLatch getCd() {
        return cd;
    }

    public static CountDownLatch getCd2() {
        return cd2;
    }

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));

        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            cd.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            cd2.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
