package ru.geekbrains.lesson2_7.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private final String ADDRESS = "localhost";
    private final int PORT = 30027;

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private boolean isAuthenticated;
    private String nickName;
    private Stage stage;

    @FXML
    public TextField textField;
    @FXML
    public TextArea textArea;
    @FXML
    public TextField login;
    @FXML
    public PasswordField password;
    @FXML
    public HBox loginbox;
    @FXML
    public HBox msgpanel;


    @FXML
    protected void sendText(ActionEvent actionEvent) {
        if (textField.getText().trim().length() > 0) {
            try {
                out.writeUTF(textField.getText());
                textField.clear();
                textField.requestFocus();
                ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {
            stage = (Stage) textField.getScene().getWindow();
            stage.setOnCloseRequest(windowEvent -> {
                if(socket !=null&&!socket.isClosed()){
                    try {
                        out.writeUTF("/end");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        });
        setAuthenticated(false);
    }

    public void login(ActionEvent actionEvent) {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        try {
            String msg = String.format("/auth %s %s", login.getText().trim(), password.getText());
            out.writeUTF(msg);
            password.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
        loginbox.setVisible(!isAuthenticated);
        loginbox.setManaged(!isAuthenticated);
        msgpanel.setVisible(isAuthenticated);
        msgpanel.setManaged(isAuthenticated);

        if (!isAuthenticated) {
            nickName = "";
        }
        setTitle(nickName);

    }

    public void connect() {
        try {
            socket = new Socket(ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String s = in.readUTF();
                        if (s.startsWith("/")) {
                            if (s.equals("/end")) {
                                break;
                            }
                            if (s.startsWith("/authok")) {
                                nickName = s.split(" ")[1];
                                setAuthenticated(true);
                                break;
                            }
                        } else {
                            textArea.appendText(s+"\n");
                        }

                    }

                    while (isAuthenticated) {
                        String s = in.readUTF();
                        if (s.startsWith("/end")) {
                            setAuthenticated(false);
                            break;
                        }
                        textArea.appendText(s+"\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
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

    public void setTitle(String nick) {
        String title;
        if (nick.equals("")) {
            title = "ChatRoom";
        } else {
            title = "ChatRoom: " + nick;
        }
        Platform.runLater(() -> {
            stage.setTitle(title);
        });
    }
}