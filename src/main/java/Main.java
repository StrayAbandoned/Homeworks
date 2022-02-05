public class Main {
    public static void main(String[] args) {
        Abc print = new Abc();
        new Thread(() -> {
            print.printA();
        }).start();
        new Thread(() -> {
            print.printB();
        }).start();
        new Thread(() -> {
            print.printC();
        }).start();
    }
}
