module com.example.paintdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;


    opens com.example.paintdemo to javafx.fxml;
    exports com.example.paintdemo;
}