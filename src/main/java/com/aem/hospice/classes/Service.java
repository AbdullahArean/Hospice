package com.aem.hospice.classes;

import java.sql.*;

import static com.aem.hospice.classes.database.generate_uid;

public class Service {
    private String uid;
    private String name;
    private int type;
    private double cost_unit;
    private double discount;
    private String description;
    private Connection conn;
    private Statement mysta;
    private void databaseupdate() throws SQLException {
        conn = database.MakeConnection();
        if(conn==null) {System.out.println("NULL");}
        mysta = conn.createStatement();
        PreparedStatement pstmt = null;
        String query = "UPDATE service set uid=?, name=?, type=?,description=?,cost_unit=?,discount=? WHERE uid =? ;";
        try {
            pstmt = getPreparedStatement(query);
            pstmt.setString(7, uid);
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
        pstmt.setString(4, description);
        pstmt.setDouble(5, cost_unit);
        pstmt.setDouble(6, discount);
        return pstmt;
    }
    private void databaseinp() throws SQLException {
        conn = database.MakeConnection();
        if(conn==null) {System.out.println("NULL");}
        mysta = conn.createStatement();
        PreparedStatement pstmt = null;
        String query = "INSERT INTO service(uid, name, type,description,cost_unit,discount)" + "VALUES (?, ?, ?,?, ?, ?)";
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
    Service(String name, int type, String description,  double cost_unit, double discount) throws SQLException {
        this.name = name;
        this.type = type;
        this.description= description;
        this.cost_unit=cost_unit;
        this.discount=discount;
        this.uid = generate_uid("service","uid",0);
        databaseinp();
    }

    Service(String uid) throws SQLException {
        Connection conn = database.MakeConnection();
        if(conn==null) {System.out.println("NULL");}
        Statement mysta = conn.createStatement();
        String sql = "Select * from hospice.service where uid=\""+uid+"\";";
        ResultSet rs = mysta.executeQuery(sql);
        while(rs.next()){
            this.uid =rs.getString("uid");
            this.name = rs.getString("name");
            this.type = rs.getInt("type");
            this.description = rs.getString("description");
            this.cost_unit= rs.getDouble("cost_unit");
            this.discount =rs.getDouble("discount");

        }
    }

    public static void main(String[] args) throws SQLException {
       Service s1 = new Service("ECG",2,"A Test",1000,0);
        Service s2 = new Service("Ecocardiogram",2,"A Tablet",1400,0);
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
        this.name = name;        databaseupdate();

    }

    public int getType() {
        return type;
    }

    public void setType(int type) throws SQLException {
        this.type = type;        databaseupdate();

    }

    public double getCost_unit() {
        return cost_unit;
    }

    public void setCost_unit(double cost_unit) throws SQLException {
        this.cost_unit = cost_unit;         databaseupdate();

    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) throws SQLException {
        this.discount = discount;         databaseupdate();

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws SQLException {
        this.description = description;         databaseupdate();

    }
}
