package com.aem.hospice;

import com.aem.hospice.LoginRegister.Login;
import javafx.application.Application;
import javafx.stage.Stage;

public class Hospice extends Application {
@Override
public void start(Stage stage) {
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