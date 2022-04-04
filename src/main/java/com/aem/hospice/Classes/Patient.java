package com.aem.hospice.Classes;

import com.aem.hospice.PopUp.AlertBox;

import java.sql.*;

import static com.aem.hospice.Classes.DBLogInManagerMySQL.GenerateUid;

public class Patient implements RealEntity {
    private String name="";
    private String  uid;
    private int type = 1;
    private String gender="";
    private  int age=0;
    private String mail="";
    private String medicalhistory=" ";
    private double DoctorExpanse =0;
    private double PharmacyExpanse =0;
    private double LabExpanse=0;
    private double OtherExpanse =0;
    private double TotalBill=0;
    private ClassDBConnector DBConnection;

    public Patient(String name, String mail, String password) throws SQLException {
        DBConnection = new PatientDBConnectorMySQL();
        this.name = name;
        this.mail = mail;
        this.uid= GenerateUid("patient","uid",1,password);
        DBConnection.InsertIntoDatabase(this);
        try{
            AlertBox.display("Patient ID Creation Successfull","UID : "+ this.uid);
        }catch (Exception e){
            System.out.println("Patient ID Creation Successfull "+"\nUID : "+ this.uid);
        }


    }
    public Patient(String uid){
        this.uid = uid;
        DBConnection = new PatientDBConnectorMySQL();
        DBConnection.InsertIntoDatabase(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        DBConnection.UpdateIntoDatabase(this);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
        DBConnection.UpdateIntoDatabase(this);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        DBConnection.UpdateIntoDatabase(this);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        DBConnection.UpdateIntoDatabase(this);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        DBConnection.UpdateIntoDatabase(this);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
        DBConnection.UpdateIntoDatabase(this);
    }

    public String getMedicalhistory() {
        return medicalhistory;
    }

    public void setMedicalhistory(String medicalhistory) {
        this.medicalhistory = medicalhistory;
        DBConnection.UpdateIntoDatabase(this);
    }

    public double getDoctorExpanse() {
        return DoctorExpanse;
    }

    public void setDoctorExpanse(double doctorExpanse) {
        DoctorExpanse = doctorExpanse;
        DBConnection.UpdateIntoDatabase(this);
    }

    public double getPharmacyExpanse() {
        return PharmacyExpanse;
    }

    public void setPharmacyExpanse(double pharmacyExpanse) {
        PharmacyExpanse = pharmacyExpanse;
        DBConnection.UpdateIntoDatabase(this);
    }

    public double getLabExpanse() {
        return LabExpanse;
    }

    public void setLabExpanse(double labExpanse) {
        LabExpanse = labExpanse;
        DBConnection.UpdateIntoDatabase(this);
    }

    public double getOtherExpanse() {
        return OtherExpanse;
    }

    public void setOtherExpanse(double otherExpanse) {
        OtherExpanse = otherExpanse;
        DBConnection.UpdateIntoDatabase(this);
    }

    public double getTotalBill() {
        return TotalBill;
    }

    public void setTotalBill(double totalBill) {
        TotalBill = totalBill;
        DBConnection.UpdateIntoDatabase(this);
    }

    //Feth doctor bill
    //fetch pharma bill
    //fetch lab bill
    //fetch other bill
    //generate total bill
   




}
