package com.aem.hospice.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EServiceController extends EmployeepageController {

    public void eservice(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EService.fxml"));
            Parent root = loader.load();

            // System.out.println("second ");
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.setTitle("Hospice Employee Service");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}