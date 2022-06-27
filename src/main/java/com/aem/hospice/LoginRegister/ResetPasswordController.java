package com.aem.hospice.LoginRegister;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;




public class ResetPasswordController {
    @FXML
    TextField mail,uid;
    @FXML
    PasswordField password1,password2;


    public void resetpassword(ActionEvent actionEvent) throws IOException {
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

    @FXML
    void SaveandBack(ActionEvent actionEvent) {
        try{
            LoginController Back = new LoginController();
            Back.login(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}

