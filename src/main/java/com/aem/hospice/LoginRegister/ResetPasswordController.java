package com.aem.hospice.LoginRegister;


import com.aem.hospice.Classes.DBLogInManagerMySQL;
import com.aem.hospice.PopUp.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.aem.hospice.Classes.ClassDBConnector;
import java.io.IOException;




public class ResetPasswordController {
    @FXML
    private TextField mail;

    @FXML
    private PasswordField password1;

    @FXML
    private PasswordField password2;

    @FXML
    private TextField uid;

    @FXML
    void BackToLogin(ActionEvent event) {
        try {
            new Login().start(Login.returnStage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void SaveChanges(ActionEvent event) {
        try {
            String pmail = mail.getText();
            String puid = uid.getText();
            String pass1 = password1.getText();
            String pass2 = password2.getText();
            if(! puid.isEmpty() && !pmail.isEmpty() && !pass1.isEmpty() && pass2.equals(pass1)){
               DBLogInManagerMySQL.ResetPassword(puid,pmail,pass1);
            }
            else
                AlertBox.display("Invalid Input","Try Again");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


    public void resetpassword(ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RPass.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setTitle("Hospice Reset Password");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

