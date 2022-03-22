package com.aem.hospice.arean;
public class patient {

    private String Name;
    private double Expanse;
    private int Service_Number;
    patient(){
        this.Name = "";
        this.Expanse = 0;
        this.Service_Number =0;
    }
    patient(String name, double expanse, int sn){
        this.Name = name;
        this.Expanse = expanse;
        this.Service_Number =sn;
    }
    patient (String a, String b, String c){
        this.Name = a;
        this.Expanse = Double.parseDouble(b);
        this.Service_Number =Integer.parseInt(c);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getExpanse() {
        return Expanse;
    }

    public void setExpanse(double expanse) {
        Expanse = expanse;
    }

    public int getService_Number() {
        return Service_Number;
    }

    public void setService_Number(int service_Number) {
        Service_Number = service_Number;
    }
}
