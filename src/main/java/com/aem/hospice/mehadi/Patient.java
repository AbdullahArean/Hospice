package com.aem.hospice.mehadi;
import com.aem.hospice.arean.*;
import com.aem.hospice.database;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.Statement;

public class Patient {
    private String Name;
    private int pid;
    private String Gender;
    private  int age;
    private String gmail;
    private String[] MedicalHistory;
    private double PharmacyExpanse;
    private double LabExpanse;
    private double TotalBill;

    //Arean's work
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private String uid;
    private String pass=" ";
    private Encryption e;

    public void databaseinp() throws Exception {
        Connection conn = database.MakeConnection();
        if(conn==null) System.out.println("NULL");
        Statement mysta = conn.createStatement();
        //String sql = "Select * from hospice.login";
//        ResultSet rs = mysta.executeQuery(sql);
//        while(rs.next()){
//            System.out.println(rs.getInt("uid"));
//            System.out.println(rs.getString("password"));
//
//        }

        //add into login database
//        String command = "insert into login values ("+e.Encrypt(uid,publicKey)+","+e.Encrypt(pass,publicKey)+","+publicKey+");";

       String command = "insert into login values ('"+uid+"', '"+pass+"', '"+publicKey+"', '"+privateKey+"');";

       mysta.execute(command);


        //add into patient database


    }
     public Patient() throws Exception {
        e = new Encryption();
        privateKey=e.getPrivateKey();
        publicKey =e.getPublicKey();
        uid = "p"+7878;// uid generate
        pass = "abcd";
        System.out.println("Unique ID: "+uid + "\nOne Time Password, Please change:=> " + pass);
        databaseinp();


    }

    public void setName(String Name)
    {
        this.Name = Name;
    }
    public String getName(){
        return Name;
    }
    public void setGender(String Gender)
    {
        this.Gender = Gender;
    }
    public String getGender()
    {
        return Gender;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public int getAge()
    {
        return age;
    }
    public void setGmail(String gmail)
    {
        this.gmail = gmail;
    }
    public String getGmail()
    {
        return gmail;
    }
    public void setMedicalHistory(String[] MedicalHistory){
        this.MedicalHistory = MedicalHistory;
    }
    public String[] getMedicalHistory(){
        return MedicalHistory;
    }
    public void setPharmacyExpanse(double PharmacyExpanse)
    {
        this.PharmacyExpanse = PharmacyExpanse;
    }
    public double getPharmacyExpanse()
    {
        return PharmacyExpanse;
    }
    public void setLabExpanse(double LabExpanse)
    {
        this.LabExpanse = LabExpanse;
    }
    public double getLabExpanse()
    {
        return LabExpanse;
    }
    public void setTotalBill(double TotalBill)
    {
        this.TotalBill = TotalBill;
    }
    public double getTotalBill() {
        return TotalBill;
    }
}
