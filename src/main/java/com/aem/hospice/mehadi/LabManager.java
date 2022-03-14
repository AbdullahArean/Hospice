package com.aem.hospice.mehadi;

public class LabManager {
    protected String Name;
    protected String phn;
    private String[] LabReport;

    public void setLabReport(String[] LabReport)
    {
        this.LabReport = LabReport;
    }

    public String[] getLabReport()
    {
        return LabReport;
    }
    public double LabReportBill()
    {
        return 59.0877;//Calculate Bill
    }

    void DrawSalary()
    {

    }

}
