package com.mehadi.hospice;

public class Doctor {
    private String Name;
    private int age;
    private String phn;
    private String dept;
    private String email;
    private String[] EducationalBg;
    private double visit;

    public void setName(String Name)
    {
        this.Name = Name;
    }
    public String getName(){
        return Name;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public int getAge()
    {
        return age;
    }
    public void setPhn(String phn)
    {
        this.phn = phn;
    }
    public String getPhn()
    {
        return phn;
    }
    public void setDept(String dept)
    {
        this.dept = dept;
    }
    public String getDept(){
        return dept;
    }
    public void setEducationalBg(String[] EducationalBg)
    {
        this.EducationalBg = EducationalBg;
    }
    public String[] getEducationalBg()
    {
        return EducationalBg;
    }
    public void setVisit(double visit)
    {
        this.visit = visit;
    }
    public double getVisit(){
        return visit;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return email;
    }
//    public String Decision()
//    {
//
//    }
    public void Prescription()
    {

    }
    public void Tests()
    {

    }
}
