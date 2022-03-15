package com.aem.hospice.arean;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    private static Connection conn;
    public static Connection MakeConnection() {
        String url = "jdbc:mysql://localhost:3306/hospice?autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "password";

        try {
            conn = DriverManager.getConnection(url,username,password);
            return conn;

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }
    public static void main(String[] args) throws Exception {

    }
}
