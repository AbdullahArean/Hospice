package com.aem.hospice.pages;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Patient Database");
        window.setMinWidth(400);

        Label label1 = new Label();
        label1.setText(message);

        Button closebutton = new Button("Ok");
        closebutton.setOnAction(e->window.close());

        VBox layout = new VBox(100);
        layout.getChildren().addAll(label1, closebutton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setTitle(title);
        window.setScene(scene);
        window.showAndWait();
    }
}