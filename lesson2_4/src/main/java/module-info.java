module ru.geekbrains.lesson2_4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.geekbrains.lesson2_4 to javafx.fxml;
    exports ru.geekbrains.lesson2_4;
}