import java.util.concurrent.Semaphore;

public class Tunnel extends Stage{
    private Semaphore smp= new Semaphore(2);;
    Tunnel(){
        this.length = 80;
        this.description = "Туннель "+ length+ " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " готовится к этапу: "+ description);
            smp.acquire();
            System.out.println(c.getName() + " начал этап: "+ description);
            Thread.sleep(length/c.getSpeed()+1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(c.getName() + " закончил этап: "+ description);
            smp.release();
        }

    }
}
