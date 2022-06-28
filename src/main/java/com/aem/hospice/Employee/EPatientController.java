package com.aem.hospice.Employee;

import com.aem.hospice.Classes.*;
import com.aem.hospice.PopUp.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EPatientController extends EmployeepageController implements Initializable {
    @FXML
    private TextField add_mh;

    @FXML
    private TextField add_page;

    @FXML
    private TextField add_pgender;

    @FXML
    private TextField add_pmail;

    @FXML
    private TextField add_pname;

    @FXML
    private TableColumn<Patient, Integer> col_page;

    @FXML
    private TableColumn<Patient, String> col_pgender;

    @FXML
    private TableColumn<Patient, String> col_pmail;

    @FXML
    private TableColumn<Patient, String> col_pmh;

    @FXML
    private TableColumn<Patient, String> col_pname;

    @FXML
    private TableColumn<Patient, String> col_puid;

    @FXML
    private TextField del_puid;

    @FXML
    private TableView<Patient> eptable;

    @FXML
    private TextField upd_mh;

    @FXML
    private TextField upd_page;

    @FXML
    private TextField upd_pgender;

    @FXML
    private TextField upd_pmail;

    @FXML
    private TextField upd_pname;

    @FXML
    private TextField upd_puid;

    private ObservableList<Patient> list = FXCollections.observableArrayList();

    @FXML
    void bt_addpatient_p(ActionEvent event) throws SQLException, IOException {
        if(employee.getType()>4){
            AlertBox.display("Action Not Permitted!","Restricted Entity");
            return;
        }
        try{
            NumberFormatException e= new NumberFormatException();
            if((add_pname.getText().isEmpty() && add_pmail.getText().isEmpty())) throw e;
        Patient p1 = new Patient(add_pname.getText(),add_pmail.getText(),"a12345678A");
        p1.setGender(add_pgender.getText());
        p1.setMedicalhistory(add_mh.getText());
        p1.setAge(Integer.parseInt(add_page.getText()));
        EPatientController epa = new EPatientController();
        epa.epatient(event);}
        catch (NumberFormatException e){
            AlertBox.display("Invalid Input","Fill Up the boxes with correct data");
        }
    }

    @FXML
    void bt_delpatient_p(ActionEvent event) throws SQLException, IOException {
        if(employee.getType()>1){
            AlertBox.display("Action Not Permitted!","Restricted Entity");
            return;
        }
        String givenuid =del_puid.getText();
        if(PatientDBConnectorMySQL.IsPatientAvailable(givenuid)){
        PatientDBConnectorMySQL.Patientdelete(givenuid);
        AlertBox.display("Patient Successfully Deleted","Press Ok to Continue");
        EPatientController epa = new EPatientController();
        epa.epatient(event);}
        else{
                AlertBox.display("Patient Details Unavailable", "Invalid Input");

        }
    }

    @FXML
    void bt_loadpatient_p(ActionEvent event) {
        String givenuid = upd_puid.getText();
        if(PatientDBConnectorMySQL.IsPatientAvailable(givenuid)) {
            Patient p1 = new Patient(givenuid);
            upd_mh.setText("" + p1.getMedicalhistory());
            upd_page.setText("" + p1.getAge());
            upd_pmail.setText("" + p1.getMail());
            upd_pname.setText("" + p1.getName());
            upd_pgender.setText("" + p1.getGender());
        }
        else{
            AlertBox.display("Patient Details Unavailable", "Invalid Input");

        }


    }

    @FXML
    void bt_updatepatient_p(ActionEvent event) throws IOException {
        try{
        Patient p1 = new Patient(upd_puid.getText());
        p1.setGender(upd_pgender.getText());
        p1.setName(upd_pname.getText());
        p1.setMail(upd_pmail.getText());
        p1.setAge(Integer.parseInt(upd_page.getText()));
        p1.setMedicalhistory(upd_mh.getText());
        AlertBox.display("Patient Info Sucessfully Updated", "Press Ok to continue");
        EPatientController epa = new EPatientController();
        epa.epatient(event);} catch (NumberFormatException e){
            AlertBox.display("Invalid Input","Fill Up the boxes with correct data");
        }


    }

    private void CollectFromDatabase() {
        try {
            String sql = "SELECT * from patient WHERE uid>10000";
            ResultSet rs = DBLogInManagerMySQL.MakeConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                list.add(new Patient(rs.getString("uid")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void epatient(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EPatient.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.setTitle("Hospice Employee Patient Mangement Page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CollectFromDatabase();
        col_puid.setCellValueFactory(new PropertyValueFactory<>("uid"));
        col_pname.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_page.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_pmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        col_pgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_pmh.setCellValueFactory(new PropertyValueFactory<>("medicalhistory"));
        eptable.setItems(list);
    }
    @FXML
    void bt_setdefaultpass_p(ActionEvent event) {
        if(employee.getType()>4){
            AlertBox.display("Action Not Permitted!","Restricted Entity");
            return;
        }
        if(PatientDBConnectorMySQL.IsPatientAvailable(upd_puid.getText())) {
            DBLogInManagerMySQL.ChangePasswordAdminPrevilage(upd_puid.getText(),"a12345678A");
            AlertBox.display("Password Changed to Default", "Password is set to a12345678A");

        }
        else{
            AlertBox.display("Patient Details Unavailable", "Invalid Input");

        }

    }
}

