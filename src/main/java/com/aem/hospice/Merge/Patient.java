package com.aem.hospice.Merge;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Patient {

    public void patient(ActionEvent actionEvent)throws IOException {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Patient.fxml"));
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


    //Overview U should change and then uncomment this
    public void OverviewButton(ActionEvent actionEvent) throws IOException{
        try{
            Overview overview=new Overview();
            overview.overview(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void dpButton(ActionEvent actionEvent) throws IOException{
        try{
            DoctorPrescription dp=new DoctorPrescription();
            dp.doctorprescription(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void lrButton(ActionEvent actionEvent) throws IOException{
        try{
            LabReport lr = new LabReport();
            lr.labreport(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void medicineButton(ActionEvent actionEvent) throws IOException{
        try{
            Medicine medicine = new Medicine();
            medicine.medicine(actionEvent);
        }
        catch (Exception e){
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

//    public  void LoginButton(ActionEvent actionEvent) throws IOException{
//        try{
//            LoginController login=new LoginController();
//            login.login(actionEvent);
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
}
