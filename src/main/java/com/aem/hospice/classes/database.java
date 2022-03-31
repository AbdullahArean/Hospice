package com.aem.hospice.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Objects;

public class database {
    private static Connection conn;
    public static Connection MakeConnection() {
        String url = "jdbc:mysql://localhost:3306/hospice?autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "password";
        try {
            conn = DriverManager.getConnection(url,username,password);
            return conn;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        ObservableList<ProvidedService> list = GetProvidedService(1,"p_uid","10005");
    }
    public static Boolean loginvalidate(String uid, String pass) throws Exception {
        Connection conn = database.MakeConnection();
        if(conn==null) {System.out.println("NULL"); return false;}
        Statement mysta = conn.createStatement();
        String sql = "Select * from hospice.login where uid=\""+uid+"\";";
        ResultSet rs = mysta.executeQuery(sql);
        while(rs.next()){
            if( Objects.equals(rs.getString("password"),pass )) {return true;}
        }
        return false;

    }
    public static String generate_uid(String table, String pcol, int login) throws SQLException {
        Connection conn = database.MakeConnection();
        if(conn==null) {System.out.println("NULL");}
        Statement mysta = conn.createStatement();
        String sql = "Select * from hospice."+table+";";
        ResultSet rs = mysta.executeQuery(sql);
        while(rs.next()){
            sql = rs.getString(pcol);

        }
        int temp =Integer.parseInt(sql)+1;
        sql= String.valueOf(temp);
        if(login==1) {
            String command = "insert into login values ('" + sql + "','1234');";
            mysta.execute(command);
        }
        return sql;
    }
    public static String generate_uid(String table, String pcol, int login, String password) throws SQLException {
        Connection conn = MakeConnection();
        if(conn==null) {System.out.println("NULL");}
        Statement mysta = conn.createStatement();
        String sql = "Select * from hospice."+table+";";
        ResultSet rs = mysta.executeQuery(sql);
        while(rs.next()){
            sql = rs.getString(pcol);

        }
        int temp =Integer.parseInt(sql)+1;
        sql= String.valueOf(temp);
        if(login==1) {
            String command = "insert into login values ('" + sql + "','"+password+"');";
            mysta.execute(command);
        }
        return sql;
    }
    public static ObservableList<ProvidedService> GetProvidedService(int type, String coln, String uid5){

       int cn=0; ObservableList<ProvidedService> list = FXCollections.observableArrayList();
        try {
            conn = database.MakeConnection();
            Statement mysta = conn.createStatement();
            String sql ="SELECT * from providedservice WHERE s_type= '" + type + "' AND " + coln + "= '" + uid5 + "' ;";
            //System.out.println(sql);
            ResultSet rs = mysta.executeQuery(sql);
            while(rs.next()){
                //cn++;
                list.add(new ProvidedService(rs.getString("ps_uid")));
            }

        }catch (Exception e){
            e.printStackTrace();

        }
       // System.out.println(cn);
        return list;
    }

}
/*
create database hospice;
use hospice;
create table login(
uid varchar(100),
password varchar(100)
);

create table patient(
uid varchar(100),
name varchar(1000),
type int,
gender varchar(20),
age int,
mail varchar(100),
medicalhistory varchar(2000),
DoctorExpanse Double(20,6),
PharmacyExpanse Double(20,6),
LabExpanse Double(20,6),
OtherExpanse Double(20,6),
TotalBill Double(20,6)
);
INSERT INTO patient (uid)
VALUES ("10000");

create table employee(
uid varchar(100),
name varchar(1000),
type int,
gender varchar(20),
age int,
mail varchar(100),
MonthlySalary Double(20,6)
);
INSERT INTO employee (uid)
VALUES ("30000");

create table service(
uid varchar(100),
name varchar(1000),
type int,
description varchar(1000),
cost_unit Double(20,6),
discount Double(20,6)
);
INSERT INTO service (uid)
VALUES ("10");

create table providedservice(
ps_uid varchar(100),
s_uid varchar(100),
p_uid varchar(100),
e_uid varchar(100),
r_uid varchar(100),
s_type int,
quantity int,
payment_status int,
bill Double(20,6),
paid Double (20,6)

);
INSERT INTO providedservice (ps_uid)
VALUES ("100000");
 */