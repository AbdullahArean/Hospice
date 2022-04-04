package com.aem.hospice.Classes;

import com.aem.hospice.PopUp.AlertBox;

import java.sql.*;
import java.util.Objects;

public class DBLogInManagerMySQL implements LogInManager,DatabaseManager{
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
    public static Boolean LogInValidate(String uid, String pass){
        try{
            String sql = "Select * from hospice.login where uid=\""+uid+"\";";
            ResultSet rs = DBLogInManagerMySQL.MakeConnection().createStatement().executeQuery(sql);
            while(rs.next()){
                if( Objects.equals(rs.getString("password"),pass )) {return true;}
            }
            return false;

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public static String GenerateUid(String table, String uidcolname, int login){
        return GenerateUid(table,uidcolname,login,"1234");
    }
    public static String GenerateUid(String table, String uidcolname, int login, String password){
        try {
            String sql = "Select * from " + table + ";";
            ResultSet rs = DBLogInManagerMySQL.MakeConnection().createStatement().executeQuery(sql);
            String generated_uid = null;
            while (rs.next()) {
                generated_uid = rs.getString(uidcolname);
            }
            //if(generated_uid==null) generated_uid="10100";
            int temp = Integer.parseInt(generated_uid) + 1;
            generated_uid = String.valueOf(temp);
            if (login == 1) {
                sql = "insert into login values ('" + generated_uid + "','"+password+"');";
                DBLogInManagerMySQL.MakeConnection().createStatement().execute(sql);
            }
            return generated_uid;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static void ChangePassword(String uid, String oldpassword , String newpassword){
        if(LogInValidate(uid,oldpassword)){
            String query = "UPDATE login set password=? WHERE uid =? ;";
            try {
                PreparedStatement pstmt =  DBLogInManagerMySQL.MakeConnection().prepareStatement(query);
                pstmt.setString(2, uid);
                pstmt.setString(1, newpassword);
                pstmt.executeUpdate();
            } catch(Exception e){
                e.printStackTrace();
            }

        }
        else{
            AlertBox.display("Wrong Password", "Try Again");
        }


    }

}