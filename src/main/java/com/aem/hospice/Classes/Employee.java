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
    private ClassDBConnector DBConnection;


    public Employee(String name, int type, String gender, int age, String mail, double monthlySalary) throws SQLException {
        DBConnection = new EmployeeDBConnectorMySQL();
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.age = age;
        this.mail = mail;
        this.MonthlySalary = monthlySalary;
        this.uid = DBLogInManagerMySQL.GenerateUid("employee","uid",1,"1234");
        DBConnection.InsertIntoDatabase(this);
        try{
            AlertBox.display("Employee ID Creation Successfull","UID : "+ this.uid);
        }catch (Exception e){
            System.out.println("Employee ID Creation Failed "+" : "+ e);
        }
    }

    public Employee(String uid) throws SQLException {
        this.uid = uid;
        DBConnection = new EmployeeDBConnectorMySQL();
        DBConnection.InsertFromDatabase(this);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) throws SQLException {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws SQLException {
        this.name = name;
        DBConnection.UpdateIntoDatabase(this);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) throws SQLException {
        this.type = type;

        DBConnection.UpdateIntoDatabase(this);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) throws SQLException {
        this.gender = gender;
        DBConnection.UpdateIntoDatabase(this);
    }

    public int getAge() throws SQLException {
        return age;

    }

    public void setAge(int age) throws SQLException {
        this.age = age;
        DBConnection.UpdateIntoDatabase(this);

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) throws SQLException {
        this.mail = mail;
        DBConnection.UpdateIntoDatabase(this);

    }

    public double getMonthlySalary() {
        return MonthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) throws SQLException {
        MonthlySalary = monthlySalary;
        DBConnection.UpdateIntoDatabase(this);

    }
}
