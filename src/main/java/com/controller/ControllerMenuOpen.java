package com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.io.IOException;

public class ControllerMenuOpen implements AppUtils {
    ControllerNewGame controllerNewGame;

    void setControllerNewGame(ControllerNewGame controllerNewGame) {
        this.controllerNewGame = controllerNewGame;
    }

    @FXML
    private Button noButton;

    @FXML
    private Button yesButton;

    @FXML
    void closeWindow(ActionEvent event) {
        closeCurrentWindow(noButton);
    }

    @FXML
    void openMenu(ActionEvent event) throws IOException {
        loadDialogWindow("StartWindow.fxml", "Dater");
        closeCurrentWindow(noButton);
        controllerNewGame.close();
    }

}
