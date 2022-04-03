package com.aem.hospice.Classes;

import com.aem.hospice.PopUp.AlertBox;
import java.sql.*;
import static com.aem.hospice.Classes.database.generate_uid;

public class Patient {
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
    private PreparedStatement pstmt;


    private void databaseinp(){
        String query = "INSERT INTO patient(uid, name, type,gender,age,mail,medicalhistory,DoctorExpanse,PharmacyExpanse,LabExpanse,OtherExpanse,TotalBill)" + "VALUES (?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?)";
        try {
            pstmt = getPreparedStatement(query);
            pstmt.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    private void databaseupdate(){
        String query = "UPDATE patient set uid=?, name=?, type=?,gender=?,age=?,mail=?,medicalhistory=?,DoctorExpanse=?,PharmacyExpanse=?,LabExpanse=?,OtherExpanse=?,TotalBill=? WHERE uid =? ;";
        try {
            pstmt = getPreparedStatement(query);
            pstmt.setString(13, uid);
            pstmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }

    }
    private PreparedStatement getPreparedStatement(String query) throws SQLException {
        pstmt = database.MakeConnection().prepareStatement(query);
        pstmt.setString(1, uid);
        pstmt.setString(2, name);
        pstmt.setInt(3, type);
        pstmt.setString(4, gender);
        pstmt.setInt(5, age);
        pstmt.setString(6, mail);
        pstmt.setString(7, medicalhistory);
        pstmt.setDouble(8, DoctorExpanse);
        pstmt.setDouble(9, PharmacyExpanse);
        pstmt.setDouble(10, LabExpanse);
        pstmt.setDouble(11, OtherExpanse);
        pstmt.setDouble(12, TotalBill);
        return pstmt;
    }
    public Patient(String name, String mail, String password) throws SQLException {
        this.name = name;
        this.mail = mail;
        this.uid=generate_uid("patient","uid",1,password);
        System.out.println(this.uid);
        databaseinp();
        AlertBox.display("Patient ID Creation Successfull","UID : "+ this.uid);


    }
    Patient(String name, String gender, int age, String mail, String medicalhistory) throws SQLException {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.mail = mail;
        this.medicalhistory = medicalhistory;
        this.uid=generate_uid("patient","uid",1);
        databaseinp();

    }
    public Patient(String uid){
        String sql = "Select * from patient where uid=\""+uid+"\";";
        try {
            ResultSet rs = database.MakeConnection().createStatement().executeQuery(sql);
            while(rs.next()){
                this.uid =rs.getString("uid");
                this.name = rs.getString("name");
                this.type = rs.getInt("type");
                this.gender = rs.getString("gender");
                this.age = rs.getInt("age");
                this.mail = rs.getString("mail");
                this.medicalhistory = rs.getString("medicalhistory");
                this.DoctorExpanse = rs.getDouble("DoctorExpanse");
                this.PharmacyExpanse = rs.getDouble("PharmacyExpanse");
                this.LabExpanse = rs.getDouble("LabExpanse");
                this.OtherExpanse = rs.getDouble("OtherExpanse");
                this.TotalBill = rs.getDouble("TotalBill");

            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }
    public void changepassword(){
        //password change
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        databaseupdate();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;

        databaseupdate();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;

        databaseupdate();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;

        databaseupdate();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;

        databaseupdate();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;

        databaseupdate();
    }

    public String getMedicalhistory() {
        return medicalhistory;
    }

    public void setMedicalhistory(String medicalhistory) {
        this.medicalhistory = medicalhistory;

        databaseupdate();
    }

    public double getDoctorExpanse() {
        return DoctorExpanse;
    }

    public void setDoctorExpanse(double doctorExpanse) {
        DoctorExpanse = doctorExpanse;

        databaseupdate();
    }

    public double getPharmacyExpanse() {
        return PharmacyExpanse;
    }

    public void setPharmacyExpanse(double pharmacyExpanse) {
        PharmacyExpanse = pharmacyExpanse;

        databaseupdate();
    }

    public double getLabExpanse() {
        return LabExpanse;
    }

    public void setLabExpanse(double labExpanse) {
        LabExpanse = labExpanse;

        databaseupdate();
    }

    public double getOtherExpanse() {
        return OtherExpanse;
    }

    public void setOtherExpanse(double otherExpanse) {
        OtherExpanse = otherExpanse;

        databaseupdate();
    }

    public double getTotalBill() {
        return TotalBill;
    }

    public void setTotalBill(double totalBill) {
        TotalBill = totalBill;

        databaseupdate();
    }

    //Feth doctor bill
    //fetch pharma bill
    //fetch lab bill
    //fetch other bill
    //generate total bill
    public static void main(String[] args) throws SQLException {
        Patient p1 = new Patient("arean","ab","as");

    }



}
