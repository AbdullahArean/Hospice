package com.aem.hospice.Patient;

import com.aem.hospice.Classes.DBLogInManagerMySQL;
import com.aem.hospice.PopUp.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MyProfileController extends PatientpageController implements Initializable {

    @FXML
    private Button bt_clear;

    @FXML
    private Button bt_save;

    @FXML
    private PasswordField pf_newpass;

    @FXML
    private PasswordField pf_newpass2;

    @FXML
    private PasswordField pf_oldpass;

    @FXML
    private TextField tf_age;

    @FXML
    private TextField tf_gender;

    @FXML
    private TextField tf_mail;

    @FXML
    private TextField tf_medicalhistory;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_uid;

    public void myprofile(ActionEvent actionEvent) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("MyProfile.fxml"));
            Parent root=loader.load();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    tf_name.setText(patient1.getName());
    tf_age.setText(""+patient1.getAge());
    tf_gender.setText(patient1.getGender());
    tf_mail.setText(patient1.getMail());
    tf_medicalhistory.setText(patient1.getMedicalhistory());
    tf_uid.setText(patient1.getUid());
    }

    @FXML
    void bt_clear_pressed(ActionEvent event) {
        try{
            MyProfileController myprofile = new MyProfileController();
            myprofile.myprofile(event);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void bt_save_pressed(ActionEvent event) {
        try{
            patient1.setName(tf_name.getText());
            patient1.setAge(Integer.parseInt(tf_age.getText()));
            patient1.setMail(tf_mail.getText());
            patient1.setMedicalhistory(tf_medicalhistory.getText());
            patient1.setGender(tf_gender.getText());
//            if(pf_newpass.getText().equals(pf_newpass2.getText())){
//                DBLogInManagerMySQL.ChangePassword(patient1.getUid(),pf_oldpass.getText(), pf_newpass.getText());
//            }
//            else{
//                AlertBox.display("Password didn't matched", "Try Again");
//            }
//            MyProfileController myprofile = new MyProfileController();
//            myprofile.myprofile(event);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
