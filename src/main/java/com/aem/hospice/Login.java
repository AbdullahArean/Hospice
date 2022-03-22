package com.aem.hospice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {
    static  Stage  window;
    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        window.setTitle("hospiceLogin!");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public static Stage returnStage(){
        return window;
    }
}