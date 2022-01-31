

import ru.geekbrains.lesson2_8.ServiceMsg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class NewClient {
    private Socket socket;
    private Server server;
    private DataOutputStream out;
    private DataInputStream in;
    private boolean isAuthenticated;
    private String nickName;
    private String login;

    NewClient(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    socket.setSoTimeout(120_000);
                    while (true) {
                        String s = in.readUTF();
                        if (s.equals(ServiceMsg.END)) {
                            sendMsg(ServiceMsg.END);
                            break;
                        }
                        if (s.startsWith(ServiceMsg.AUTH)) {
                            String[] str = s.split(" ", 3);
                            if (str.length < 3) {
                                continue;
                            }
                            String nick = server.getauthClass().getNickName(str[1], str[2]);
                            if (nick != null) {
                                login = str[1];
                                if (!server.isLoginAuthenticated(login)) {
                                    isAuthenticated = true;
                                    nickName = nick;
                                    sendMsg(ServiceMsg.AUTH_OK+" " + nickName);
                                    server.connect(this);
                                    System.out.println("Client " + nickName + " connected");
                                    break;
                                } else {
                                    sendMsg("Client with this login has already authenticated");
                                }

                            } else {
                                sendMsg("Wrong login/password");
                            }
                        }
                        if (s.startsWith(ServiceMsg.REG)) {
                            String[] str = s.split(" ", 4);
                            if (str.length < 4) {
                                continue;
                            }
                            if (server.getauthClass().registration(str[1], str[2], str[3])) {
                                sendMsg(ServiceMsg.REG_OK);
                            } else sendMsg(ServiceMsg.REG_NO);
                        }
                    }
                    socket.setSoTimeout(0);
                    while (isAuthenticated) {
                        String s = in.readUTF();
                        if (s.startsWith("/")) {
                            if (s.equals(ServiceMsg.END)) {
                                sendMsg(ServiceMsg.END);
                                break;
                            }
                            if (s.startsWith("/ " + nickName)){
                                String[] str = s.split(" ", 3);
                                if(server.getauthClass().changeNickname(str[1], str[2])){
                                   nickName =  str[2];
                                   sendMsg(ServiceMsg.END);
                                }
                            }
                            if (s.startsWith("/w")) {
                                if (s.split(" ").length < 3) {
                                    continue;
                                }
                                server.privateMessage(this, s);
                            }
                        } else server.broadcast(this, s);


                    }

                } catch(SocketTimeoutException e){
                    try {
                        out.writeUTF(ServiceMsg.END);
                    } catch (IOException e1) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Client " + nickName + " disconnected");
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

    public void sendMsg(String s) {
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogin() {
        return login;
    }

    public String getNickName() {
        return nickName;
    }
}
