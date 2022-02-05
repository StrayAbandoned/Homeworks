public class Abc {
    private char letter = 'A';
    private final Object mon = new Object();
    public void printA(){
        synchronized (mon){
            for (int i = 0; i < 5; i++) {
                while (letter!='A'){
                    try {
                        mon.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print('A');
                letter='B';
                mon.notifyAll();

            }
        }
    }
    public void printB(){
        synchronized (mon){
            for (int i = 0; i < 5; i++) {
                while (letter!='B'){
                    try {
                        mon.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print('B');
                letter='C';
                mon.notifyAll();

            }
        }
    }
    public void printC(){
        synchronized (mon){
            for (int i = 0; i < 5; i++) {
                while (letter!='C'){
                    try {
                        mon.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print('C');
                letter='A';
                mon.notifyAll();

            }
        }
    }
}
