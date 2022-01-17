import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NewClient {
    private Socket socket;
    private Server server;
    private DataOutputStream out;
    private DataInputStream in;
    private boolean isAuthenticated;
    private String nickName;

    NewClient(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        String s = in.readUTF();
                        if (s.equals("/end")) {
                            sendMsg("/end");
                            break;
                        }
                        if(s.startsWith("/auth ")){
                            String[] str = s.split(" ", 3);
                            if(str.length<3){
                                continue;
                            }
                            String nick = server.getauthClass().getNickName(str[1],str[2]);
                            if (nick!=null){
                                isAuthenticated=true;
                                nickName = nick;
                                sendMsg("/authok "+nickName);
                                server.connect(this);
                                System.out.println("Client " +nickName+ " connected");
                                break;
                            } else {
                                sendMsg("Wrong login/password");
                            }
                        }
                    }

                    while (isAuthenticated) {
                        String s = in.readUTF();
                        if(s.startsWith("/")){
                            if (s.equals("/end")) {
                                sendMsg("/end");
                                break;
                            }
                            if (s.startsWith("/w")){
                                server.privateMessage(this,s);
                            }
                        } else server.broadcast(this,s);




                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Client " +nickName+ " disconnected");
                    server.disconnect(this);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void sendMsg(String s){
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickName() {
        return nickName;
    }
}
