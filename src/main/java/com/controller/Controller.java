package com.controller;

import com.core.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    @FXML
    void closeCurrentWindow(ActionEvent event) {
        closeCurrentWindow(text);
    }

    @FXML
    void openRules(ActionEvent event) {
        try {
            loadDialogWindow("Rules.fxml", "Rules");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void startNewGame(ActionEvent event) throws IOException {
        newGameButton.getScene().getWindow().hide();
        loadDialogWindow("NewGame.fxml", "Dater");
    }
}