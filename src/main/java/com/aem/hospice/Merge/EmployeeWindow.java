package com.aem.hospice.Merge;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeWindow{
    public void employeewindow(ActionEvent actionEvent)throws IOException {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("EmployeeWindow.fxml"));
            Parent root=loader.load();
          // System.out.println("second ");
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
    public void ProfileButton(ActionEvent actionEvent) throws IOException{
        try{
            MyProfile myprofile = new MyProfile();
            myprofile.myprofile(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
