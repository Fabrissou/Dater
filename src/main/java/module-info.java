module com.mdada {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.core to javafx.fxml;
    exports com.core;
    exports com.controller;
    opens com.controller to javafx.fxml;
}