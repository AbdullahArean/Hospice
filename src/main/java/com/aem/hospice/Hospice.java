package com.aem.hospice;

import javafx.application.Application;
import javafx.stage.Stage;

public class Hospice extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        new Login().start(stage);
    }
}
