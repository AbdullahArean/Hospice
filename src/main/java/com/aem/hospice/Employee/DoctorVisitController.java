package com.aem.hospice.Employee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class DoctorVisitController extends DoctorOverviewController{
    public void doctorvisit(ActionEvent actionEvent){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("DoctorVisit.fxml"));
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
    void Back(ActionEvent event) {

        try{
            DoctorOverviewController back = new DoctorOverviewController();
            back.doctoroverview(event);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
