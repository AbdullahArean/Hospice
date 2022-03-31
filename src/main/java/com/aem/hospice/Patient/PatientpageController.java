package com.aem.hospice.Patient;
import com.aem.hospice.LoginRegister.Login;
import com.aem.hospice.classes.Patient;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;

public class PatientpageController {
    public static Patient patient1 ;

    public PatientpageController(String uid1) throws SQLException {
        this.patient1 = new Patient(uid1);
    }
    public PatientpageController(){
    }

    public void patient(ActionEvent actionEvent)throws IOException {
        try{
//            FXMLLoader loader=new FXMLLoader(getClass().getResource("Patient.fxml"));
//            Parent root=loader.load();
//            Stage stage=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
//            stage.setTitle("Hospice");
//            Scene scene=new Scene(root);
//            stage.setScene(scene);
//            stage.show();
            OverviewController over=new OverviewController();
            over.overview(actionEvent);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    //Overview U should change and then uncomment this
    public void OverviewButton(ActionEvent actionEvent) {
        try{
            OverviewController over=new OverviewController();
            over.overview(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void dpButton(ActionEvent actionEvent){
        try{
            DoctorPrescriptionController dp=new DoctorPrescriptionController();
            dp.doctorprescription(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void lrButton(ActionEvent actionEvent) {
        try{
            LabReportController lr = new LabReportController();
            lr.labreport(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void medicineButton(ActionEvent actionEvent) {
        try{
            MedicineController medicine = new MedicineController();
            medicine.medicine(actionEvent);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void ProfileButton(ActionEvent actionEvent){
        try{
            MyProfileController myprofile = new MyProfileController();
            myprofile.myprofile(actionEvent);
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
}