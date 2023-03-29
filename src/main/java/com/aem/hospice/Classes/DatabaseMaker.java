package com.aem.hospice.Classes;

import java.sql.*;

public class DatabaseMaker {
    public static void main(String[] args) {
        run();
    }
    public static void run() {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "hospice";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "password";

        try {
            Process changePasswordProcess = Runtime.getRuntime().exec(
                    String.format("sudo mysql -u %s -p%s -e \"ALTER USER '%s'@'localhost' IDENTIFIED BY '%s';\"", password,
                            password, userName, password));
            changePasswordProcess.waitFor();
            System.out.println("Root password has been changed to password.");
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, userName, password);

            Statement stmt = conn.createStatement();

            // Check if database exists
            ResultSet resultSet = conn.getMetaData().getCatalogs();
            boolean databaseExists = false;
            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                if (databaseName.equals(dbName)) {
                    databaseExists = true;
                    break;
                }
            }
            resultSet.close();


            // Create database if it doesn't exist
            if (!databaseExists) {
                //System.out.println("Created");
                stmt.executeUpdate("create database hospice");
                conn = DriverManager.getConnection(url+dbName, userName, password);
            }
            else {
                //System.out.println("Deleted");
                //stmt.executeUpdate("drop database hospice");
                return;
            }

            // Use database
            stmt.executeUpdate("use hospice");

            // Create tables and insert data
            stmt.executeUpdate("create table login(uid varchar(100), password varchar(100))");
            stmt.executeUpdate("insert into login (uid,password) values ('00000','a12345678A')");

            stmt.executeUpdate("create table patient(uid varchar(100), name varchar(1000), type int, gender varchar(20), age int, mail varchar(100), medicalhistory varchar(2000), DoctorExpanse Double(20,6), PharmacyExpanse Double(20,6), LabExpanse Double(20,6), OtherExpanse Double(20,6), TotalBill Double(20,6))");
            stmt.executeUpdate("insert into patient (uid) values ('10000')");

            stmt.executeUpdate("create table employee(uid varchar(100), name varchar(1000), type int, gender varchar(20), age int, mail varchar(100), MonthlySalary Double(20,6))");
            stmt.executeUpdate("insert into employee (uid) values ('00000')");
            stmt.executeUpdate("insert into employee (uid) values ('30000')");

            stmt.executeUpdate("create table service(uid varchar(100), name varchar(1000), type int, description varchar(1000), cost_unit Double(20,6), discount Double(20,6))");
            stmt.executeUpdate("insert into service (uid) values ('10')");

            stmt.executeUpdate("create table providedservice(ps_uid varchar(100), s_uid varchar(100), p_uid varchar(100), e_uid varchar(100), r_uid varchar(100), s_type int, quantity int, payment_status int, bill Double(20,6), paid Double (20,6), s_name varchar(1000))");
            stmt.executeUpdate("insert into providedservice (ps_uid) values ('100000')");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}