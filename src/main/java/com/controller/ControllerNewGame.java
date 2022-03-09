package com.controller;

import com.core.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ControllerNewGame implements AppUtils {

    private int day, month = 1;

    String getStringDate() {
        return String.format("%02d", this.day) + "." + String.format("%02d", this.month);
    }

    boolean checkDate() {
        boolean check = true;
        SimpleDateFormat newFormat = new SimpleDateFormat("dd.MM.yyyy");
        newFormat.setLenient(false);
        try {
            newFormat.parse(getStringDate() + "." + "2022");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    void increaseDay(int num) {
        this.day += num;
        if (checkDate()) {
            gameDate.setText(getStringDate());
        } else {
            this.day -= num;
        }
    }

    void increaseMonth(int num) {
        this.month += num;
        if (checkDate()) {
            gameDate.setText(getStringDate());
        } else {
            this.month -= num;
        }
    }

    @FXML
    private Button dayPlusOne;

    @FXML
    private Button dayPlusTwo;

    @FXML
    private Text gameDate;

    @FXML
    private Text infoText;

    @FXML
    private Button menu;

    @FXML
    private Button monthPlusOne;

    @FXML
    private Button monthPlusTwo;

    @FXML
    void dayPlusOne(ActionEvent event) {
        increaseDay(1);
    }

    @FXML
    void dayPlusTwo(ActionEvent event) {
        increaseDay(2);
    }

    @FXML
    void menu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuOpen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ControllerMenuOpen controllerMenuOpen = fxmlLoader.getController();
        controllerMenuOpen.setControllerNewGame(this);
        Stage stage = new Stage();
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    void monthPlusOne(ActionEvent event) {
        increaseMonth(1);
    }

    @FXML
    void monthPlusTwo(ActionEvent event) {
        increaseMonth(2);
    }

    @FXML
    void initialize() {
        increaseDay((int) Math.round(Math.random() * (27)) + 1);
        increaseMonth((int) Math.round(Math.random() * (10)) + 1);
    }

    void close() {
        menu.getScene().getWindow().hide();
    }

}
