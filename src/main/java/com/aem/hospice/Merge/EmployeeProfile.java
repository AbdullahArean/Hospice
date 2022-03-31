package com.aem.hospice.Merge;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeProfile {
    public void employeeprofile(ActionEvent actionEvent)throws IOException {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("EmployeeProfile.fxml"));
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

    public void APButton(ActionEvent actionEvent) throws IOException{
        try{
            AllottedPatient allottedPatient = new AllottedPatient();
            allottedPatient.allottedpatient(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void SetExpanseButton(ActionEvent actionEvent) throws IOException{
        try{
            SetExpanse setExpanse = new SetExpanse();
            setExpanse.setexpanse(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void SalaryButton (ActionEvent actionEvent) throws IOException{
        try{
            Salary salary = new Salary();
            salary.salary(actionEvent);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void WHButton(ActionEvent actionEvent) throws IOException{
        try{
            WorkingHours workingHours = new WorkingHours();
            workingHours.workinghours(actionEvent);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
