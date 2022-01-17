import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private final int PORT = 30027;

    private ServerSocket server;
    private Socket socket;
    private List<NewClient> clients;
    private AuthenticationClass authClass;


    Server() {
        clients = new CopyOnWriteArrayList<>();
        authClass = new AuthenticationClass();
        try {
            server = new ServerSocket(PORT);
            System.out.println("Server started");

            while (true){
                socket = server.accept();
                new NewClient(this, socket);
            }



        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    public void broadcast(NewClient client, String str){
        String message = "@"+client.getNickName()+": "+str;
        for (NewClient c: clients){
            c.sendMsg(message);
        }

    }
    public void privateMessage(NewClient sender, String msg){
        String receiver = msg.split(" ", 3)[1];
        for (NewClient c: clients){
            if(c.getNickName().equals(receiver)){
                String message = "@"+sender.getNickName()+" to @"+c.getNickName()+": "+msg.split(" ", 3)[2];
                c.sendMsg(message);
                sender.sendMsg(message);
            }
        }
    }

    public void connect(NewClient client){
        clients.add(client);
    }

    public void disconnect(NewClient client){
        clients.remove(client);
    }

    public AuthenticationClass getauthClass() {
        return authClass;
    }
}
