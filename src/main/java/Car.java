import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable{
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private static volatile AtomicInteger winCount = new AtomicInteger(1);
    private Lock lock = new ReentrantLock();


    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }
    Car(Race race, int speed){
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник № " + CARS_COUNT;

    }



    @Override
    public void run() {

        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500+(int)(Math.random()*800));
            System.out.println(this.name + " готов!");
            Main.getCd().countDown();
            Main.getCb().await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);

        }
        if (winCount.get()==1){
            lock.lock();
            winCount.decrementAndGet();

            System.out.println(this.getName() + " ПОБЕДИИИИИЛ!!!!");
            lock.unlock();
        }
        Main.getCd2().countDown();


    }
}