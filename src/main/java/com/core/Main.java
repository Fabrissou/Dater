package com.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("StartWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Dater");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
//запуск программы. При запуске открывается StartWindow.fxml, который управляется с помощью Controller
    public static void main(String[] args) {
        launch();
    }
}