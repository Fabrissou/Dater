package com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerRules implements AppUtils {
    @FXML
    private Button closeButton;
//закрыть окно
    @FXML
    void closeWindow(ActionEvent event) {
        closeCurrentWindow(closeButton);
    }



}
