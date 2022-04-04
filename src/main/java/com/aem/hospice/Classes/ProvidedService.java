package com.aem.hospice.Classes;

import java.sql.*;

import static com.aem.hospice.Classes.DBLogInManagerMySQL.GenerateUid;


public class ProvidedService {
    private String ps_uid;//Provided Service uid
    private String s_uid;//Service
    private String p_uid ; //patient
    private String e_uid;//Service Provider
    private String r_uid;//Receiver
    private String s_name;
    private int s_type;
    private int quantity;
    private int payment_status=0;
    private double bill=0;
    private double paid=0;
    private void databaseupdate(){
        String query = "UPDATE providedservice set ps_uid=?,s_uid=? ,p_uid=?, e_uid=?, r_uid=?,s_type=?,quantity=?,payment_status=?, bill=?,paid=? WHERE ps_uid =? ;";
        try {
            getPreparedStatement(query).setString(11, ps_uid);
            getPreparedStatement(query).executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    private PreparedStatement getPreparedStatement(String query) {

        try{PreparedStatement PrepareStatement_PS;
        PrepareStatement_PS = DBLogInManagerMySQL.MakeConnection().prepareStatement(query);
        PrepareStatement_PS.setString(1, ps_uid);
        PrepareStatement_PS.setString(2, s_uid);
        PrepareStatement_PS.setString(3, p_uid);
        PrepareStatement_PS.setString(4, e_uid);
        PrepareStatement_PS.setString(5, r_uid);
        PrepareStatement_PS.setInt(6, s_type);
        PrepareStatement_PS.setInt(7, quantity);
        PrepareStatement_PS.setInt(8, payment_status);
        PrepareStatement_PS.setDouble(9, bill);
        PrepareStatement_PS.setDouble(10, paid);
        return PrepareStatement_PS;}
        catch (Exception E){
            E.printStackTrace();

        }
        return null;
    }
    private void Databaseinput() {
        String query = "INSERT INTO providedservice(ps_uid,s_uid ,p_uid, e_uid, r_uid,s_type,quantity,payment_status,bill,paid)" + "VALUES (?, ?, ?,?, ?, ?,?,?,?,?)";
        try {
            getPreparedStatement(query).executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    ProvidedService(String s_uid,String p_uid, String e_uid, int quantity) throws SQLException {
        this.s_uid = s_uid;
        this.p_uid = p_uid;
        this.e_uid= e_uid;
        this.quantity=quantity;
        this.ps_uid = DBLogInManagerMySQL.GenerateUid("providedservice","ps_uid",0);
        this.bill = generate_bill_type_name(quantity);
        Databaseinput();
    }

    private double generate_bill_type_name(int quantity) throws SQLException {
        Service s1 = new Service(this.s_uid);
        this.s_type = s1.getType();
        this.s_name = s1.getName();
        return s1.getCost_unit()*quantity;

    }

    public ProvidedService(String uid){
        String sql = "Select * from hospice.providedservice where ps_uid=\""+uid+"\";";
        try{
            ResultSet rs = DBLogInManagerMySQL.MakeConnection().createStatement().executeQuery(sql);
            while(rs.next()){
                this.ps_uid =rs.getString("ps_uid");
                this.s_uid =rs.getString("s_uid");
                this.p_uid =rs.getString("p_uid");
                this.e_uid =rs.getString("e_uid");
                this.r_uid =rs.getString("r_uid");
                this.quantity = rs.getInt("quantity");
                this.payment_status = rs.getInt("payment_status");
                this.s_type = rs.getInt("s_type");
                this.bill= rs.getDouble("bill");
                this.paid =rs.getDouble("paid");

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public String getPs_uid() {
        return ps_uid;
    }

    public void setPs_uid(String ps_uid) throws SQLException {
        this.ps_uid = ps_uid;         databaseupdate();

    }

    public String getS_uid() {
        return s_uid;
    }

    public void setS_uid(String s_uid) throws SQLException {
        this.s_uid = s_uid;         databaseupdate();

    }

    public String getP_uid() {
        return p_uid;
    }

    public void setP_uid(String p_uid) throws SQLException {
        this.p_uid = p_uid;       databaseupdate();

    }

    public String getE_uid() {
        return e_uid;
    }

    public void setE_uid(String e_uid) throws SQLException {
        this.e_uid = e_uid;        databaseupdate();

    }

    public String getR_uid() {
        return r_uid;
    }

    public void setR_uid(String r_uid) throws SQLException {
        this.r_uid = r_uid;  databaseupdate();
    }

    public int getS_type() {
        return s_type;
    }

    public void setS_type(int s_type) throws SQLException {
        this.s_type = s_type; databaseupdate();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws SQLException {
        this.quantity = quantity; databaseupdate();
    }

    public int getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(int payment_status) throws SQLException {
        this.payment_status = payment_status; databaseupdate();
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) throws SQLException {
        this.bill = bill; databaseupdate();
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) throws SQLException {
        this.paid +=paid;
        if(this.paid>=this.bill) this.payment_status=1;
        databaseupdate();
    }

    public void setS_name(String name){
        this.s_name = name;
    }
    public String getS_name(){
        return s_name;
    }
}

