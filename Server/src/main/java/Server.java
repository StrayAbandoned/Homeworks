import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private final int PORT = 30027;

    private ServerSocket server;
    private Socket socket;
    private List<NewClient> clients;
    // private AuthenticationClass authClass;
    private DbAuthentication db;




    Server() {
        clients = new CopyOnWriteArrayList<>();
        //authClass = new AuthenticationClass();
        db = new DbAuthentication();
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
                db.disconnectDB();
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
            if(c.getNickName().equals(receiver)&&!c.getNickName().equals(sender.getNickName())){
                String message = "@"+sender.getNickName()+" to @"+c.getNickName()+": "+msg.split(" ", 3)[2];
                c.sendMsg(message);
                sender.sendMsg(message);
            }
        }
    }
    public boolean isLoginAuthenticated(String login){
        for (NewClient c: clients){
            if(c.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }

    public void broadcastClientList (){
        StringBuilder sb = new StringBuilder("/clientlist");
        for (NewClient c: clients){
            sb.append(" ").append(c.getNickName());
        }
        String msg = sb.toString();
        for (NewClient c: clients){
            c.sendMsg(msg);
        }

    }



    public void connect(NewClient client){
        clients.add(client);
        broadcastClientList();
    }

    public void disconnect(NewClient client){
        clients.remove(client);
        broadcastClientList();
    }

//    public AuthenticationClass getauthClass() {
//        return authClass;
//    }


    public DbAuthentication getauthClass() {
        return db;
    }
}
