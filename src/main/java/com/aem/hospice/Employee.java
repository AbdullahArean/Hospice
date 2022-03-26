package com.aem.hospice;

import java.sql.*;

import static com.aem.hospice.database.generate_uid;

public class Employee {
    private String uid=" ";
    private String name= " ";
    private int type;
    private String gender=" ";
    private int age;
    private String mail=" ";
    private double MonthlySalary=0;
    private Connection conn;
    private Statement mysta;
    private void databaseupdate() throws SQLException {
        conn = database.MakeConnection();
        if(conn==null) {System.out.println("NULL");}
        mysta = conn.createStatement();
        PreparedStatement pstmt = null;
        String query = "UPDATE employee set uid=?, name=?, type=?,gender=?,age=?,mail=?,MonthlySalary=? WHERE uid =? ;";
        try {
            pstmt = getPreparedStatement(query);
            pstmt.setString(8, uid);
            int status = pstmt.executeUpdate();
            if(status > 0) {
                System.out.println("Record is inserted successfully !!!");
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    private PreparedStatement getPreparedStatement(String query) throws SQLException {
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, uid);
        pstmt.setString(2, name);
        pstmt.setInt(3, type);
        pstmt.setString(4, gender);
        pstmt.setInt(5, age);
        pstmt.setString(6, mail);
        pstmt.setDouble(7, MonthlySalary);
        return pstmt;
    }
    private void databaseinp() throws SQLException {
        conn = database.MakeConnection();
        if(conn==null) {System.out.println("NULL");}
        mysta = conn.createStatement();
        PreparedStatement pstmt = null;
        String query = "INSERT INTO employee(uid, name, type,gender,age,mail,MonthlySalary)" + "VALUES (?, ?, ?,?, ?, ?,?)";
        try {
            pstmt = getPreparedStatement(query);
            int status = pstmt.executeUpdate();
            if(status > 0) {
                System.out.println("Record is inserted successfully !!!");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    Employee(String name, int type, String gender, int age, String mail, double monthlySalary) throws SQLException {
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.age = age;
        this.mail = mail;
        this.MonthlySalary = monthlySalary;
        this.uid = generate_uid("employee","uid",1);
        databaseinp();
    }

    Employee(String uid) throws SQLException {
        Connection conn = database.MakeConnection();
        if(conn==null) {System.out.println("NULL");}
        Statement mysta = conn.createStatement();
        String sql = "Select * from hospice.employee where uid=\""+uid+"\";";
        ResultSet rs = mysta.executeQuery(sql);
        while(rs.next()){
            this.uid =rs.getString("uid");
            this.name = rs.getString("name");
            this.type = rs.getInt("type");
            this.gender = rs.getString("gender");
            this.age = rs.getInt("age");
            this.mail = rs.getString("mail");
            this.MonthlySalary = rs.getDouble("MonthlySalary");

        }
    }

    public static void main(String[] args) throws SQLException {
        Employee e1 = new Employee("tahian",2,"male",32,"a@gmail.com",45000);
        Employee e2 = new Employee("2006");
        System.out.println(e2.gender);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) throws SQLException {
        this.uid = uid;

        databaseupdate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws SQLException {
        this.name = name;

        databaseupdate();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) throws SQLException {
        this.type = type;

        databaseupdate();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) throws SQLException {
        this.gender = gender;

        databaseupdate();
    }

    public int getAge() throws SQLException {
        return age;

    }

    public void setAge(int age) throws SQLException {
        this.age = age;
        databaseupdate();

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) throws SQLException {
        this.mail = mail;
        databaseupdate();

    }

    public double getMonthlySalary() {
        return MonthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) throws SQLException {
        MonthlySalary = monthlySalary;
        databaseupdate();

    }
}
