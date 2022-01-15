import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private final int PORT = 30024;
    private Scanner sc, sc2;
    private PrintWriter out;
    private volatile String s = "";

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен!");

            socket = serverSocket.accept();
            System.out.println("Клиент подключен!");

            Thread t1 = new Thread(() -> {
                try {
                    sc = new Scanner(socket.getInputStream());
                    while (true) {
                        if (sc.hasNext()) {
                            s = sc.nextLine();
                            if (s.equals("/end")) {
                                break;

                            }
                            System.out.println("Client: " + s);
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
                    try {
                        serverSocket.close();
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