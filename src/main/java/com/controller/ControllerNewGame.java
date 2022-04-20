package com.controller;

import com.core.Bot;
import com.core.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ControllerNewGame implements AppUtils {

    private int day, month = 1;
    private Bot bot;

//    получение даты в формате день.месяц
    String getStringDate() {
        return String.format("%02d", this.day) + "." + String.format("%02d", this.month);
    }

//проверка существования даты в 2022 году
    boolean checkDate(int day, int month) {
        boolean check = true;
        SimpleDateFormat newFormat = new SimpleDateFormat("dd.MM.yyyy");
        newFormat.setLenient(false);
        try {
            newFormat.parse( day + "." + month + "." + "2022");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @FXML
    private Button dayPlusOne;

    @FXML
    private Button dayPlusTwo;

    @FXML
    private AnchorPane left;

    @FXML
    private AnchorPane right;

    @FXML
    private Text monthhh;

    @FXML
    private Text dayyy;

    @FXML
    private Button again;

    @FXML
    private Button botStepButton;

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

//    выход в главное меню
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

//    при инициализации создатеся бот и устанавливается случайная дата
    @FXML
    void initialize() {
        again.setVisible(false);
        botStepButton.setVisible(false);
        bot = new Bot();
        setRandomDate();
    }

//    при нажатии на кнопку бот делает ход
    @FXML
    void doBotStep(ActionEvent event) {
        int newDay = this.day, newMonth = this.month;
        int step = bot.findOptimalMovement(day, month);

        if (step == 0) {
            newDay += 1;
        } else if (step == 1) {
            newDay += 2;
        } else if (step == 2) {
            newMonth += 1;
        } else {
            newMonth += 2;
        }

        setNewDate(newDay, newMonth);
        botStepButton.setVisible(false);
        if (checkPlayerLose()) {
            infoText.setText("ВЫ ПОБЕДИЛИ");
            System.out.println("ВЫ ПОБЕДИЛИ");
        } else {
            show();
        }
    }

//    выход из игры
    void close() {
        menu.getScene().getWindow().hide();
    }
//    проверка проигрыша
    boolean checkPlayerLose() {
        return day == 31 && month == 12;
    }
//    установка новой даты
    void setNewDate(int day, int month) {
        this.day = day;
        this.month = month;
        gameDate.setText(getStringDate());
        if (checkPlayerLose()) {
            botStepButton.setVisible(false);
            again.setVisible(true);
            infoText.setText("ВЫ ПРОИГРАЛИ");
            System.out.println("ВЫ ПРОИГРАЛИ");
        }
    }
//увеличить день на один
    @FXML
    void dayPlusOne(ActionEvent event) {
        int newDay = this.day + 1;

        if (!checkPlayerLose() && checkDate(newDay, this.month)) {
            botStepButton.setVisible(true);
            setNewDate(newDay, this.month);
            hide();
        }

    }
    //увеличить день на два
    @FXML
    void dayPlusTwo(ActionEvent event) {
        int newDay = this.day + 2;

        if (!checkPlayerLose() && checkDate(newDay, this.month)) {
            botStepButton.setVisible(true);
            setNewDate(newDay, this.month);
            hide();
        }

    }
//увеличить месяц на один
    @FXML
    void monthPlusOne(ActionEvent event) {
        int newMonth = this.month + 1;

        if (!checkPlayerLose() && checkDate(this.day, newMonth)) {
            botStepButton.setVisible(true);
            setNewDate(this.day, newMonth);
            hide();
        }
    }
//увеличить месяц на два
    @FXML
    void monthPlusTwo(ActionEvent event) {
        int newMonth = this.month + 2;

        if (!checkPlayerLose() && checkDate(this.day, newMonth)) {
            botStepButton.setVisible(true);
            setNewDate(this.day, newMonth);
            hide();
        }
    }
//    начать заново
    @FXML
    void startAgain(ActionEvent event) {
        infoText.setText("ВАШ ХОД");
        again.setVisible(false);
        show();
        setRandomDate();
    }
//установка случайной даты
    void setRandomDate() {
        int newDay = (int) Math.round(Math.random() * (27)) + 1;
        int newMonth = (int) Math.round(Math.random() * (10)) + 1;
        setNewDate(newDay, newMonth);
    }
//скрывает интерфейс игрока
    void hide() {
        left.setVisible(false);
        dayyy.setVisible(false);
        right.setVisible(false);
        monthhh.setVisible(false);
    }
//показывает интерфейс игрока
    void show() {
        left.setVisible(true);
        dayyy.setVisible(true);
        right.setVisible(true);
        monthhh.setVisible(true);
    }
}
