package com.aem.hospice;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Hospice extends Application {
@Override
public void start(Stage stage) throws IOException {
    try{
        new Login().start(stage);
    }
    catch(Exception  e)
    {
        System.out.println(e.getMessage());
    }
}

    public static void main(String[] args) {

        launch(args);
    }
}