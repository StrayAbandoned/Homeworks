module ru.geekbrains.lesson2_7.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires Protocol;


    opens ru.geekbrains.lesson2_7.demo to javafx.fxml;
    exports ru.geekbrains.lesson2_7.demo;
}