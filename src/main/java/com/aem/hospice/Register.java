package com.aem.hospice;


import com.aem.hospice.Login;
import com.aem.hospice.classes.Patient;
import com.aem.hospice.pages.AlertBox;
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
import org.w3c.dom.Text;

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
    public void BackToLogin(ActionEvent actionEvent) throws IOException {
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
            if(pass2.equals(pass1)){
                Patient patient = new Patient(pname, pmail,pass1);
                new Login().start(Login.returnStage());
            }
            else
                AlertBox.display("Password Didn't matched","Try Again");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
