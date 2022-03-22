package com.aem.hospice;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PatientpageController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onByeButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}