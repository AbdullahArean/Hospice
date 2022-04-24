package com.aem.hospice.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NurseOverviewController extends EmployeepageController{
    public void nurseoverview(ActionEvent actionEvent){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("NurseOverview.fxml"));
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
    void viewpatientinfo_n(ActionEvent actionEvent) {
        try{
            NurseViewPatientInfoController nvpi = new NurseViewPatientInfoController();
            nvpi.viewpatient(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void WorkShedule_n(ActionEvent actionEvent) {
        try{
            NurseWorkScheduleController nws = new NurseWorkScheduleController();
            nws.WorkSchedule(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
