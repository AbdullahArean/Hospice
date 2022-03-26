package com.aem.hospice;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
    static Stage window;
    static Button b1 = new Button("Yes"), b2= new Button("No");
    static Boolean answer;
    static Label label1 = new Label();
    static  VBox layout;

    public static Boolean display(String title, String message){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setOnCloseRequest(e->{closeprogram();});
        window.setTitle("Deleting Patient Record");
        window.setMinWidth(250);


        label1.setText(message);
        b1.setOnAction(e->{answer=false; window.close();});
        b2.setOnAction(e->{answer=true; window.close();});

        layout = new VBox(10);
        layout.getChildren().addAll(label1, b1, b2);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setTitle(title);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

    public static  Boolean closeprogram() {
        System.out.println("Closed");
        window.close();
        return false;

    }
}