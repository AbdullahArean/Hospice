package com.aem.hospice.mehadi;

public class Pharmacist {
    public String Name;
    protected String[] medicine;
    public String phn;

    public double MedicineBill(String[] medicine, int quantity)
    {
        return 100.0;//Set condition for calculating the bill according to medicine name and quantity
    }

    public double DrawSalary()
    {
        return 100.0;//Set some logic to find out the salary
    }
}
