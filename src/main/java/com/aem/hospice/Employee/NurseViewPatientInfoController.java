package com.aem.hospice.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NurseViewPatientInfoController extends NurseOverviewController{
    public void viewpatient(ActionEvent actionEvent) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("NurseViewPatientInfo.fxml"));
            Parent root=loader.load();
            Stage stage=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

            stage.setTitle("Hospice");
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void Back(ActionEvent actionEvent) {
        try{
            NurseOverviewController back = new NurseOverviewController();
            back.nurseoverview(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
