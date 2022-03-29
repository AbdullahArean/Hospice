package com.aem.hospice.classes;

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
}
