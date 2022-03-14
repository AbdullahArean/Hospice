package com.mehadi.hospice;

public class Patient {
    private String Name;
    private int pid;
    private String Gender;
    private  int age;
    private String gmail;
    private String[] MedicalHistory;
    private double PharmacyExpanse;
    private double LabExpanse;
    private double TotalBill;

    public void setName(String Name)
    {
        this.Name = Name;
    }
    public String getName(){
        return Name;
    }
    public void setGender(String Gender)
    {
        this.Gender = Gender;
    }
    public String getGender()
    {
        return Gender;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public int getAge()
    {
        return age;
    }
    public void setGmail(String gmail)
    {
        this.gmail = gmail;
    }
    public String getGmail()
    {
        return gmail;
    }
    public void setMedicalHistory(String[] MedicalHistory){
        this.MedicalHistory = MedicalHistory;
    }
    public String[] getMedicalHistory(){
        return MedicalHistory;
    }
    public void setPharmacyExpanse(double PharmacyExpanse)
    {
        this.PharmacyExpanse = PharmacyExpanse;
    }
    public double getPharmacyExpanse()
    {
        return PharmacyExpanse;
    }
    public void setLabExpanse(double LabExpanse)
    {
        this.LabExpanse = LabExpanse;
    }
    public double getLabExpanse()
    {
        return LabExpanse;
    }
    public void setTotalBill(double TotalBill)
    {
        this.TotalBill = TotalBill;
    }
    public double getTotalBill() {
        return TotalBill;
    }
}
