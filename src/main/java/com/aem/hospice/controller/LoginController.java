package com.aem.hospice.controller;

import com.aem.hospice.Login;
import com.aem.hospice.pages.AlertBox;
import com.aem.hospice.classes.database;
import com.aem.hospice.pages.Patientpage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    String uid1, pass1;

    @FXML
    private TextField uid,password;
    @FXML
    protected void onLoginButtonClick() throws Exception {

       uid1=uid.getText();
       pass1 = password.getText();

       if(database.loginvalidate(uid1,pass1))
       {

           Stage s = Login.returnStage();
           if(uid1.charAt(0)=='1')
           new Patientpage().start(s);
       }
       else
           AlertBox.display("Wrong ID or Password","Try Again");

    }

    @FXML
    protected void onRegisterButtonClick(){
        System.out.println("register");
    }

}
