package com.aem.hospice.controller;

import com.aem.hospice.EmployeepageController;
import com.aem.hospice.OverviewController;
import com.aem.hospice.PatientpageController;
import com.aem.hospice.Login;
import com.aem.hospice.Register;
import com.aem.hospice.classes.database;
import com.aem.hospice.pages.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class LoginController {
    @FXML
    TextField uid;
    @FXML
    PasswordField password;
    private static Stage stage;

    String uid1, pass1;
    public void login(ActionEvent actionEvent)throws IOException{
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("LoginController.fxml"));
            Parent root=loader.load();

           // System.out.println("second ");
            stage=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

            stage.setTitle("Hospice");
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static Stage returnStage(){
        return stage;

    }

    public void Register(ActionEvent actionEvent) throws IOException{
        try{
            Register register=new Register();
            register.register(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public  void LoginButton(ActionEvent actionEvent) throws Exception {
        uid1=uid.getText();
        pass1 = password.getText();

        if(database.loginvalidate(uid1,pass1))
        {

            Stage s = Login.returnStage();
            if(uid1.charAt(0)=='1')
            {

               PatientpageController pp = new PatientpageController(uid1);
               pp.patient(actionEvent);
            }
            else if(uid1.charAt(0) == '3'){
                EmployeepageController ep = new EmployeepageController(uid1);
                ep.employee(actionEvent);
            }
        }
        else
            AlertBox.display("Wrong ID or Password","Try Again");



    }

}