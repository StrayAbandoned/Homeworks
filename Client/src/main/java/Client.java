import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private final String ADDRESS = "localhost";
    private final int PORT = 30024;
    private Scanner sc;
    private Scanner sc2;
    private PrintWriter out;
    public volatile String s = "";

    Client() {
        try {
            socket = new Socket(ADDRESS, PORT);
            System.out.println("Подключение установлено!");

            Thread t1 = new Thread(() -> {
                try {
                    sc = new Scanner(socket.getInputStream());

                    while (true) {
                        if (sc.hasNext()) {
                            s = sc.nextLine();
                            if (s.equals("/end")) {
                                break;
                            }

                            System.out.println("Server: " + s);
                        }
                    }
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread t2 = new Thread(() -> {
                try {
                    out = new PrintWriter(socket.getOutputStream(), true);
                    sc2 = new Scanner(System.in);

                    while (true) {
                        s = sc2.nextLine();
                        out.println(s);
                        if (s.equals("/end")) {
                            break;
                        }
                    }
                    sc2.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            t1.setDaemon(true);
            t2.setDaemon(true);
            t1.start();
            t2.start();

            while (true) {
                if (s.equals("/end")) {
                    System.out.println("Сеанс завершен!");
                    try {
                        socket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
