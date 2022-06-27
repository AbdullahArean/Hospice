package com.aem.hospice.LoginRegister;
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

import java.io.IOException;




    public class RecoverUIDcontroller {
        @FXML
        private TextField mail;

        @FXML
        private PasswordField pass;

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
            AlertBox.display("Your UID","0000");

        }


        public void recovery(ActionEvent actionEvent) throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("RUID.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                Scene scene = new Scene(root);
                stage.setTitle("Hospice RecoverUID");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

//        @FXML
//        void recoveruid(ActionEvent actionEvent) {
//            try{
//                LoginController Back = new LoginController();
//                Back.login(actionEvent);
//            }
//            catch (Exception e){
//                System.out.println(e.getMessage());
//            }
//        }
    }