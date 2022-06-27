package com.aem.hospice.Classes;

import com.aem.hospice.PopUp.AlertBox;

import java.sql.*;

import static com.aem.hospice.Classes.DBLogInManagerMySQL.GenerateUid;

public class Employee implements RealEntity{
    private String uid=" ";
    private String name= " ";
    private int type;
    private String gender=" ";
    private int age;
    private String mail=" ";
    private double MonthlySalary=0;
    private final ClassDBConnector DBConnection;


    public Employee(String name, int type, String gender, int age, String mail, double monthlySalary){
        DBConnection = new EmployeeDBConnectorMySQL();
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.age = age;
        this.mail = mail;
        this.MonthlySalary = monthlySalary;
        this.uid = DBLogInManagerMySQL.GenerateUid("employee","uid",1,"a12345678A");
        DBConnection.InsertIntoDatabase(this);
        if(this.uid==null)
            System.out.println("Employee ID Creation Failed");
    }

    public Employee(String uid){
        this.uid = uid;
        DBConnection = new EmployeeDBConnectorMySQL();
        DBConnection.InsertFromDatabase(this);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid){
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        DBConnection.UpdateIntoDatabase(this);
    }

    public int getType() {
        return type;
    }

    public void setType(int type){
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

    public void setMail(String mail){
        this.mail = mail;
        DBConnection.UpdateIntoDatabase(this);

    }

    public double getMonthlySalary() {
        return MonthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        MonthlySalary = monthlySalary;
        DBConnection.UpdateIntoDatabase(this);

    }
}
