package com.aem.hospice.arean;

import com.aem.hospice.mehadi.Patient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class login {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println("(L)ogIn or (S)ignUp");

        String s = sc.nextLine();
        switch (s.charAt(0)){
            case 'L':
                login();
                break;
            case 'S':
                signup();
                break;
            default:
                break;
        }

    }
    private static void login() throws SQLException {
        Connection conn = database.MakeConnection();
        if(conn==null) System.out.println("NULL");
        Statement mysta = conn.createStatement();
        while(true){
            System.out.println("UID");
            String uid = sc.nextLine();

            System.out.println("Password");
            String pass = sc.nextLine();

            System.out.println("Login Data Received");

            String sql = "Select * from hospice.login where uid=\""+uid+"\";";
            ResultSet rs = mysta.executeQuery(sql);
            while(rs.next()){
                //System.out.println(rs.getString("password"));
                if( Objects.equals(rs.getString("password"), pass)) {System.out.println("Login Successful"); return;}

            }
            System.out.println("Wrong UID or Password");


        }





    }
    private static void signup() throws Exception {
        Patient p1 = new Patient();
        System.out.println("Signup Successful");
    }
}
