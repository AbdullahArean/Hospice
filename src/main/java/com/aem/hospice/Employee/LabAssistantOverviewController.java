package com.aem.hospice.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LabAssistantOverviewController extends EmployeepageController{
    public void labassistantoverview(ActionEvent actionEvent) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("LabAssistantOverview.fxml"));
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
    void viewpatientinfo_l(ActionEvent actionEvent) {
        try{
            LabAssistantViewPatientInfoController lavpi = new LabAssistantViewPatientInfoController();
            lavpi.laviewpatient(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void ProvideLabReport_b(ActionEvent actionEvent) {
        try{
            LabAssistantProvideLabReportController plr = new LabAssistantProvideLabReportController();
            plr.providelabreport(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void BuyLabEquipment_b(ActionEvent actionEvent) {
        try{
            LabAssistantBuyLabEquipmentController ble = new LabAssistantBuyLabEquipmentController();
            ble.buylabequip(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
