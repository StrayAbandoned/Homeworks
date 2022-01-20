package ru.geekbrains.lesson2_7.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.geekbrains.lesson2_8.ServiceMsg;

public class RegController {
    @FXML
    public PasswordField password;
    @FXML
    public TextField login;
    @FXML
    public TextField nick;
    @FXML
    public TextArea textArea;

    private HelloController controller;

    public void setController(HelloController controller) {
        this.controller = controller;
    }

    public void registration(ActionEvent actionEvent) {
        String login = this.login.getText().trim();
        String password = this.password.getText();
        String nickname = nick.getText().trim();
        controller.tryToReg(login,password,nickname);

    }
    public void regInfo(String s){
        if(s.equals(ServiceMsg.REG_OK)){
            textArea.appendText("Registration successful!"+"\n");
        }else {
            textArea.appendText("User with this login/password already exists"+"\n");
        }
    }

}
