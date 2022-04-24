package com.aem.hospice.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PharmacistViewPatientInfoController extends PharmacistOverviewController{
    public void phviewpatient(ActionEvent actionEvent) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("PharmacistViewPatientInfo.fxml"));
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
    void Back(ActionEvent actionEvent)
    {
        try{
            PharmacistOverviewController back = new PharmacistOverviewController();
            back.pharmacistoverview(actionEvent);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
