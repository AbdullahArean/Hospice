package com.aem.hospice.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.Objects;

public class database {
    public static Connection MakeConnection() {
        String url = "jdbc:mysql://localhost:3306/hospice?autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "password";
        try {
            return DriverManager.getConnection(url,username,password);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static Boolean loginvalidate(String uid, String pass){
        try{
            String sql = "Select * from hospice.login where uid=\""+uid+"\";";
            ResultSet rs = database.MakeConnection().createStatement().executeQuery(sql);
            while(rs.next()){
                if( Objects.equals(rs.getString("password"),pass )) {return true;}
            }
            return false;

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public static String generate_uid(String table, String pcol, int login){
        return generate_uid(table,pcol,login,"1234");
    }
    public static String generate_uid(String table, String pcol, int login, String password){
        try {
            String sql = "Select * from hospice." + table + ";";
            ResultSet rs = database.MakeConnection().createStatement().executeQuery(sql);
            String generated_uid = null;
            while (rs.next()) {
                generated_uid = rs.getString(pcol);

            }
            int temp = Integer.parseInt(sql) + 1;
            generated_uid = String.valueOf(temp);
            if (login == 1) {
                sql = "insert into login values ('" + sql + "','"+password+"');";
                database.MakeConnection().createStatement().execute(sql);
            }
            return generated_uid;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static ObservableList<ProvidedService> GetProvidedService(int type, String coln, String uid5){

       ObservableList<ProvidedService> list = FXCollections.observableArrayList();
        try {
            String sql ="SELECT * from providedservice WHERE s_type= '" + type + "' AND " + coln + "= '" + uid5 + "' ;";
            ResultSet rs = database.MakeConnection().createStatement().executeQuery(sql);
            while(rs.next()){
                list.add(new ProvidedService(rs.getString("ps_uid")));
            }

        }catch (Exception e){
            e.printStackTrace();

        }
        return list;
    }
    public static void ChangePassword(String uid, String password){
        String query = "UPDATE login set password=? WHERE uid =? ;";
        try {
            PreparedStatement pstmt =  database.MakeConnection().prepareStatement(query);
            pstmt.setString(2, uid);
            pstmt.setString(1, password);
            pstmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }

    }

}