package com.aem.hospice.LoginRegister;


import com.aem.hospice.Classes.Patient;
import com.aem.hospice.PopUp.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//import java.awt.event.ActionEvent;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Register {
    @FXML
    TextField name, mail;
    @FXML
    PasswordField password1, password2;


    public void register(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setTitle("Hospice Register");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void BackToLogin(ActionEvent actionEvent){
        try {
            new Login().start(Login.returnStage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void SaveChanges(ActionEvent actionEvent) throws IOException {
        try {
            String pname = name.getText();
            String pmail = mail.getText();
            String pass1 = password1.getText();
            String pass2 = password2.getText();
            if(!pname.isEmpty() && !pmail.isEmpty() && !pass1.isEmpty() && pass2.equals(pass1)){
                Patient patient = new Patient(pname, pmail,pass1);
                LoginController loginController = new LoginController();
                loginController.login(actionEvent);
            }
            else
                AlertBox.display("Invalid Input","Try Again");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
