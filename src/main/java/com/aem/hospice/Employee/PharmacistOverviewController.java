package com.aem.hospice.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PharmacistOverviewController extends EmployeepageController{
    public void pharmacistoverview(ActionEvent actionEvent){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("PharmacistOverview.fxml"));
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
    void viewpatientinfo_ph(ActionEvent actionEvent) {
        try{
            PharmacistViewPatientInfoController pvpi = new PharmacistViewPatientInfoController();
            pvpi.phviewpatient(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void SellInfo_b(ActionEvent actionEvent) {
        try{
            PharmacistSellInfoController psi = new PharmacistSellInfoController();
            psi.phsellinfo(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void BuyMedicine_b(ActionEvent actionEvent) {
        try{
            PharmacistBuyMedicineController pbm = new PharmacistBuyMedicineController();
            pbm.pbuymedicine(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
