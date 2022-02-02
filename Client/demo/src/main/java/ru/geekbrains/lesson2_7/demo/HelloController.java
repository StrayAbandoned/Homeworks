package ru.geekbrains.lesson2_7.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.geekbrains.lesson2_8.ServiceMsg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private final String ADDRESS = "localhost";
    private final int PORT = 30027;

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private boolean isAuthenticated;
    private String nickName;
    private Stage stage, regStage;
    private RegController regController;
    private History history;
    private String loginStr;

    @FXML
    private TextField textField;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private HBox loginbox;
    @FXML
    private HBox msgpanel;
    @FXML
    private ListView<String> clientList;


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
                if (socket != null && !socket.isClosed()) {
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
        clientList.setVisible(isAuthenticated);
        clientList.setManaged(isAuthenticated);

        if (!isAuthenticated) {
            nickName = "";
        }
        setTitle(nickName);
        textArea.clear();
        history = new History(loginStr);
        if (isAuthenticated){
            showHistory();
        }



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
                            if (s.equals(ServiceMsg.END)) {
                                break;
                            }
                            if (s.startsWith(ServiceMsg.REG)) {
                                regController.regInfo(s);
                            }

                            if (s.startsWith(ServiceMsg.AUTH_OK)) {
                                nickName = s.split(" ")[1];
                                loginStr = s.split(" ")[2];
                                setAuthenticated(true);
                                break;
                            }
                        } else {
                            textArea.appendText(s + "\n");
                        }

                    }

                    while (isAuthenticated) {


                        String s = in.readUTF();
                        if (s.startsWith("/")) {
                            if (s.startsWith(ServiceMsg.END)) {
                                setAuthenticated(false);
                                break;
                            }
                            if (s.startsWith("/clientlist")) {
                                String[] list = s.split(" ");
                                Platform.runLater(() -> {
                                    clientList.getItems().clear();
                                    for (int i = 1; i < list.length; i++) {
                                        clientList.getItems().add(list[i]);
                                    }
                                });
                            }
                        } else {
                            textArea.appendText(s + "\n");
                            history.writeOnFile(s);
                        }


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

    public void clickUser(MouseEvent mouseEvent) {
        String reciever = clientList.getSelectionModel().getSelectedItem();
        textField.setText(ServiceMsg.PRIVATE_MSG + " " + reciever + " ");
    }

    public void registration(ActionEvent actionEvent) {
        if (regStage == null) {
            createRegWindow();
        }
        regStage.show();

    }

    private void createRegWindow() {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("registration.fxml"));
            Parent root = fxmlLoader1.load();
            regStage = new Stage();
            regStage.setTitle("Registration");
            regStage.setScene(new Scene(root, 300, 300));
            regStage.initModality(Modality.APPLICATION_MODAL);
            regStage.initStyle(StageStyle.UTILITY);


            regController = fxmlLoader1.getController();
            regController.setController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public TextField getLogin() {
        return login;
    }

    public void tryToReg(String login, String password, String nickName) {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        String msg = ServiceMsg.REG + " " + login + " " + password + " " + nickName;
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHistory (){
        if(history.getList()!=null){
            List<String> list = new ArrayList<>(history.getList());
            if(list.size()<100){
                for (String s:list) {
                    textArea.appendText(s+"\n");
                }
            } else {
                for (int i = list.size()-101; i < list.size()-1 ; i++){
                    textArea.appendText(list.get(i)+"\n");
                }
            }
        }
    }
}