package com.aem.hospice.Classes;

import com.aem.hospice.PopUp.AlertBox;
import java.sql.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static boolean isValidPassword(String str)
    {
        String Regex_combination_of_letters_and_numbers = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$";
        String Regex_just_letters = "^(?=.*[a-zA-Z])[a-zA-Z]+$";
        String Regex_just_numbers = "^(?=.*[0-9])[0-9]+$";
        String Regex_just_specialcharachters = "^(?=.*[@#$%^&+=])[@#$%^&+=]+$";
        String Regex_combination_of_letters_and_specialcharachters = "^(?=.*[a-zA-Z])(?=.*[@#$%^&+=])[a-zA-Z@#$%^&+=]+$";
        String Regex_combination_of_numbers_and_specialcharachters = "^(?=.*[0-9])(?=.*[@#$%^&+=])[0-9@#$%^&+=]+$";
        String Regex_combination_of_letters_and_numbers_and_specialcharachters = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@#$%^&+=])[a-zA-Z0-9@#$%^&+=]+$";

        if(str.length()<8) {
            AlertBox.display("Password is Not strong Enough, Try Again","1. Password Length must be greater than or equal 8\n" +
                "2. Password Should include atleast one letter or special character(&,#,! etc)");return false;}
        if(str.matches(Regex_combination_of_letters_and_numbers))
            return true;
        if(str.matches(Regex_combination_of_letters_and_specialcharachters))
            return true;
        if(str.matches(Regex_combination_of_numbers_and_specialcharachters))
            return true;
        if(str.matches(Regex_combination_of_letters_and_numbers_and_specialcharachters))
            return true;
        AlertBox.display("Password is Not strong Enough, Try Again","1. Password Length must be greater than or equal 8\n" +
                "2. Password Should include atleast one letter or special character(&,#,! etc)");
        return false;
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
        if(!isValidPassword(password)) {
            return null;

        }
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
            if(!isValidPassword(newpassword)) return;
            String query = "UPDATE login set password=? WHERE uid =? ;";
            try {
                PreparedStatement pstmt =  DBLogInManagerMySQL.MakeConnection().prepareStatement(query);
                pstmt.setString(2, uid);
                pstmt.setString(1, newpassword);
                pstmt.executeUpdate();
                AlertBox.display("Password Changed Successfully","Back to My Profile");
            } catch(Exception e){
                e.printStackTrace();
            }

        }
        else {
            AlertBox.display("Wrong Password", "Try Again");
        }


    }
    public static void ChangePasswordAdminPrevilage(String uid,String newpassword){
            if(!isValidPassword(newpassword)) return;
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

    public static void main(String[] args) {
        DBLogInManagerMySQL a = new DBLogInManagerMySQL();
        System.out.println(a.isValidPassword("##%^1%#####"));
    }

}