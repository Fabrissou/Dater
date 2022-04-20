package com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class Controller implements AppUtils {

    @FXML
    private Button closeButton;

    @FXML
    private Button newGameButton;

    @FXML
    private Button rulesButton;

    @FXML
    private Text text;

//    закрытие текущего окна
    @FXML
    void closeCurrentWindow(ActionEvent event) {
        closeCurrentWindow(text);
    }

//   открыть правила
    @FXML
    void openRules(ActionEvent event) {
        try {
            loadDialogWindow("Rules.fxml", "Rules");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//  запустить игру (ControllerNewGame)
    @FXML
    void startNewGame(ActionEvent event) throws IOException {
        newGameButton.getScene().getWindow().hide();
        loadDialogWindow("NewGame.fxml", "Dater");
    }
}