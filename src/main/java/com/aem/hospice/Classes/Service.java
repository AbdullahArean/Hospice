package com.aem.hospice.Classes;

public class Service implements RealEntity{
    private String uid;
    private String name;
    private int type;
    private double cost_unit;
    private double discount;
    private String description;
    ServiceDBConnectorMySQL serviceDBConnectorMySQL;

    Service(String name, int type, String description,  double cost_unit, double discount){
        this.name = name;
        this.type = type;
        this.description= description;
        this.cost_unit=cost_unit;
        this.discount=discount;
        this.uid = DBLogInManagerMySQL.GenerateUid("service","uid",0);
        serviceDBConnectorMySQL = new ServiceDBConnectorMySQL();
        serviceDBConnectorMySQL.InsertIntoDatabase(this);
    }

    Service(String uid){
        this.uid = uid;
        serviceDBConnectorMySQL = new ServiceDBConnectorMySQL();
        serviceDBConnectorMySQL.InsertFromDatabase(this);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid)  {
        this.uid = uid;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        serviceDBConnectorMySQL.UpdateIntoDatabase(this);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        serviceDBConnectorMySQL.UpdateIntoDatabase(this);

    }

    public double getCost_unit() {
        return cost_unit;
    }

    public void setCost_unit(double cost_unit){
        this.cost_unit = cost_unit;
        serviceDBConnectorMySQL.UpdateIntoDatabase(this);

    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount){
        this.discount = discount;
        serviceDBConnectorMySQL.UpdateIntoDatabase(this);

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        serviceDBConnectorMySQL.UpdateIntoDatabase(this);

    }

    public static void main(String[] args) {
        Service s1 = new Service("Avonil",1,"A medicine for heart patient",20,0);
    }
}
