package com.aem.hospice.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EPatientController extends EmployeepageController{
    public void epatient(ActionEvent actionEvent){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("EPatient.fxml"));
            Parent root=loader.load();

            // System.out.println("second ");
            Stage stage=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

            stage.setTitle("Hospice");
            Scene scene=new Scene(root);
            scene.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
