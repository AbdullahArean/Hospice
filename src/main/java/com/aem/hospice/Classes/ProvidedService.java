package com.aem.hospice.Classes;

import java.sql.*;

public class ProvidedService implements RealEntity{
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
    private ProvidedServiceDBConnectorMySQL providedServiceDBConnectorMySQL;

    public ProvidedService(String s_uid, String p_uid, String e_uid, int quantity) throws SQLException {
        this.s_uid = s_uid;
        this.p_uid = p_uid;
        this.e_uid= e_uid;
        this.quantity=quantity;
        this.ps_uid = DBLogInManagerMySQL.GenerateUid("providedservice","ps_uid",0);
        this.bill = generate_bill_type_name(quantity);
        providedServiceDBConnectorMySQL = new ProvidedServiceDBConnectorMySQL();
        providedServiceDBConnectorMySQL.InsertIntoDatabase(this);
    }

    private double generate_bill_type_name(int quantity) throws SQLException {
        Service s1 = new Service(this.s_uid);
        this.s_type = s1.getType();
        this.s_name = s1.getName();
        return s1.getCost_unit()*quantity;

    }

    public ProvidedService(String uid) throws SQLException {
        providedServiceDBConnectorMySQL = new ProvidedServiceDBConnectorMySQL();
        this.ps_uid = uid;
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
                this.s_name = rs.getString("s_name");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //providedServiceDBConnectorMySQL.InsertFromDatabase(this);
        this.bill=generate_bill_type_name(this.quantity);

    }


    public static void main(String[] args) throws SQLException {
        ProvidedService p = new ProvidedService("13","10005","30001",3);
    }
    public String getPs_uid() {
        return ps_uid;
    }

    public void setPs_uid(String ps_uid) throws SQLException {
        this.ps_uid = ps_uid;
        providedServiceDBConnectorMySQL.UpdateIntoDatabase(this);

    }

    public String getS_uid() {
        return s_uid;
    }

    public void setS_uid(String s_uid) throws SQLException {
        this.s_uid = s_uid;
        this.bill = generate_bill_type_name(this.quantity);
        providedServiceDBConnectorMySQL.UpdateIntoDatabase(this);

    }

    public String getP_uid() {
        return p_uid;
    }

    public void setP_uid(String p_uid) throws SQLException {
        this.p_uid = p_uid;
        providedServiceDBConnectorMySQL.UpdateIntoDatabase(this);

    }

    public String getE_uid() {
        return e_uid;
    }

    public void setE_uid(String e_uid) throws SQLException {
        this.e_uid = e_uid;
        providedServiceDBConnectorMySQL.UpdateIntoDatabase(this);

    }

    public String getR_uid() {
        return r_uid;
    }

    public void setR_uid(String r_uid) throws SQLException {
        this.r_uid = r_uid;
        providedServiceDBConnectorMySQL.UpdateIntoDatabase(this);
    }

    public int getS_type() {
        return s_type;
    }

    public void setS_type(int s_type) throws SQLException {
        this.s_type = s_type;
        providedServiceDBConnectorMySQL.UpdateIntoDatabase(this);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws SQLException {
        this.quantity = quantity;
        this.bill=generate_bill_type_name(this.quantity);
        providedServiceDBConnectorMySQL.UpdateIntoDatabase(this);
    }

    public int getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(int payment_status) throws SQLException {
        this.payment_status = payment_status;
        providedServiceDBConnectorMySQL.UpdateIntoDatabase(this);
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) throws SQLException {
        this.bill = bill;
        providedServiceDBConnectorMySQL.UpdateIntoDatabase(this);
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) throws SQLException {
        this.paid +=paid;
        if(this.paid>=this.bill) this.payment_status=1;
        providedServiceDBConnectorMySQL.UpdateIntoDatabase(this);
    }

    public void setS_name(String name){
        this.s_name = name;
        providedServiceDBConnectorMySQL.UpdateIntoDatabase(this);
    }
    public String getS_name(){
        return s_name;
    }


}

