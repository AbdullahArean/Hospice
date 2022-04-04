package com.aem.hospice.Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDBConnectorMySQL implements ClassDBConnector{
    private PreparedStatement pstmt;
    private PreparedStatement getPreparedStatement(String query, Patient p) throws SQLException {
        pstmt = DBLogInManagerMySQL.MakeConnection().prepareStatement(query);
        pstmt.setString(1, p.getUid());
        pstmt.setString(2, p.getName());
        pstmt.setInt(3, p.getType());
        pstmt.setString(4, p.getGender());
        pstmt.setInt(5, p.getAge());
        pstmt.setString(6, p.getMail());
        pstmt.setString(7, p.getMedicalhistory());
        pstmt.setDouble(8, p.getDoctorExpanse());
        pstmt.setDouble(9, p.getPharmacyExpanse());
        pstmt.setDouble(10, p.getLabExpanse());
        pstmt.setDouble(11, p.getOtherExpanse());
        pstmt.setDouble(12, p.getTotalBill());
        return pstmt;
    }

    @Override
    public void InsertIntoDatabase(RealEntity person) {
        String query = "INSERT INTO patient(uid, name, type,gender,age,mail,medicalhistory,DoctorExpanse,PharmacyExpanse,LabExpanse,OtherExpanse,TotalBill)" + "VALUES (?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?)";
        try {
            pstmt = getPreparedStatement(query, (Patient) person);
            pstmt.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void UpdateIntoDatabase(RealEntity person) {
        String query = "UPDATE patient set uid=?, name=?, type=?,gender=?,age=?,mail=?,medicalhistory=?,DoctorExpanse=?,PharmacyExpanse=?,LabExpanse=?,OtherExpanse=?,TotalBill=? WHERE uid =? ;";
        try {
            pstmt = getPreparedStatement(query,(Patient) person);
            pstmt.setString(13, ((Patient) person).getUid());
            pstmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void InsertFromDatabase(RealEntity person) {
        String sql = "Select * from patient where uid=\""+((Patient) person).getUid()+"\";";
        try {
            ResultSet rs = DBLogInManagerMySQL.MakeConnection().createStatement().executeQuery(sql);
            while(rs.next()){
                ((Patient) person).setName(rs.getString("name"));
                ((Patient) person).setType(rs.getInt("type"));
                ((Patient) person).setGender(rs.getString("gender"));
                ((Patient) person).setAge(rs.getInt("age"));
                ((Patient) person).setMail(rs.getString("mail"));
                ((Patient) person).setMedicalhistory(rs.getString("medicalhistory"));
                ((Patient) person).setDoctorExpanse(rs.getDouble("DoctorExpanse"));
                ((Patient) person).setPharmacyExpanse(rs.getDouble("PharmacyExpanse"));
                ((Patient) person).setLabExpanse(rs.getDouble("LabExpanse"));
                ((Patient) person).setOtherExpanse(rs.getDouble("OtherExpanse"));
                ((Patient) person).setTotalBill(rs.getDouble("TotalBill"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
