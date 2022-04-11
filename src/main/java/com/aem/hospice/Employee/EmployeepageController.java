package com.aem.hospice.Employee;

import com.aem.hospice.LoginRegister.Login;
import com.aem.hospice.Patient.PatientExpanseController;
import com.aem.hospice.Classes.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeepageController {
    public static Employee employee ;

     public EmployeepageController(String uid1) throws SQLException {
        this.employee = new Employee(uid1);
    }
    public EmployeepageController(){
    }

    public void employee(ActionEvent actionEvent)throws IOException {
        try{

            if(employee.getType()==1){
                DoctorOverviewController dover = new DoctorOverviewController();
                dover.doctoroverview(actionEvent);
            }
            else{
                EOverviewController over=new EOverviewController();
                over.eoverview(actionEvent);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void ProfileButton(ActionEvent actionEvent){
        try{
            EProfileController myprofile = new EProfileController();
            myprofile.eprofile(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void SignOutButton() {
        try{
            new Login().start(Login.returnStage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void PatientExpanseButton(ActionEvent actionEvent) {
        try{
            PatientExpanseController pp =new PatientExpanseController();
            pp.patientexpanse(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void OverviewButton(ActionEvent actionEvent) {
        try{
            EOverviewController over=new EOverviewController();
            over.eoverview(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void employee_b(ActionEvent event) {
         EEmployeeController eep = new EEmployeeController();
         eep.eemployee(event);

    }

    @FXML
    void patient_b(ActionEvent event) {
         EPatientController epa = new EPatientController();
         epa.epatient(event);

    }

    @FXML
    void pservice_b(ActionEvent event) throws IOException {
         EPServiceController eps = new EPServiceController();
         eps.epservice(event);

    }

    @FXML
    void service_b(ActionEvent event) throws IOException {
         EServiceController es = new EServiceController();
         es.eservice(event);

    }
}