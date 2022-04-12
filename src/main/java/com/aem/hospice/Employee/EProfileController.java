package com.aem.hospice.Employee;

import com.aem.hospice.Classes.DBLogInManagerMySQL;
import com.aem.hospice.Patient.MyProfileController;
import com.aem.hospice.Patient.PatientpageController;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EProfileController extends EmployeepageController implements Initializable {
    @FXML
    private TextField tf_ems;

    @FXML
    private TextField tf_etype;

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
    private TextField tf_name;

    @FXML
    private TextField tf_uid;

    public void eprofile(ActionEvent actionEvent) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("EProfile.fxml"));
            Parent root=loader.load();

            Stage stage=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

            stage.setTitle("Hospice Employee My Profile");
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
        tf_name.setText(employee.getName());
        tf_age.setText(""+employee.getAge());
        tf_gender.setText(""+employee.getGender());
        tf_mail.setText(""+employee.getMail());
        tf_ems.setText(""+employee.getMonthlySalary());
        tf_uid.setText(employee.getUid());
        tf_etype.setText(""+employee.getType());
    }

    @FXML
    void bt_clear_pressed(ActionEvent event) {
        try{
            EProfileController myprofile = new EProfileController();
            myprofile.eprofile(event);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void bt_save_pressed(ActionEvent event) {
        try{
            employee.setName(tf_name.getText());
            employee.setAge(Integer.parseInt(tf_age.getText()));
            employee.setMail(tf_mail.getText());
            employee.setMonthlySalary(Double.parseDouble(tf_ems.getText()));
            employee.setGender(tf_gender.getText());
            employee.setType(Integer.parseInt(tf_etype.getText()));

            if(!pf_oldpass.getText().isEmpty()){

                if( pf_newpass.getText().equals(pf_newpass2.getText())){
                    DBLogInManagerMySQL.ChangePassword(employee.getUid(),pf_oldpass.getText(), pf_newpass.getText());
                    AlertBox.display("Password Changed Successfully","Back to My Profile");
                }
                else{
                    AlertBox.display("Password didn't matched", "Try Again");
                }
            }
            EProfileController myprofile = new EProfileController();
            myprofile.eprofile(event);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
