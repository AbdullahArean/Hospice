package com.aem.hospice.Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDBConnectorMySQL implements ClassDBConnector{
    private PreparedStatement pstmt;
    private PreparedStatement getPreparedStatement(String query, Employee e) throws SQLException {
        pstmt = DBLogInManagerMySQL.MakeConnection().prepareStatement(query);
        pstmt.setString(1, e.getUid());
        pstmt.setString(2, e.getName());
        pstmt.setInt(3, e.getType());
        pstmt.setString(4, e.getGender());
        pstmt.setInt(5, e.getAge());
        pstmt.setString(6, e.getMail());
        pstmt.setDouble(7, e.getMonthlySalary());
        return pstmt;
    }
    @Override
    public void InsertIntoDatabase(RealEntity entity) {
        String query = "INSERT INTO employee(uid, name, type,gender,age,mail,MonthlySalary)" + "VALUES (?, ?, ?,?, ?, ?,?)";
        try {
            pstmt = getPreparedStatement(query, (Employee) entity);
            pstmt.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void UpdateIntoDatabase(RealEntity entity) {
        String query = "UPDATE employee set uid=?, name=?, type=?,gender=?,age=?,mail=?,MonthlySalary=? WHERE uid =? ;";
        try {
            pstmt = getPreparedStatement(query,(Employee)entity );
            pstmt.setString(8, ((Employee)entity).getUid());
            pstmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void InsertFromDatabase(RealEntity entity) {
        String sql = "Select * from hospice.employee where uid=\"" + ((Employee) entity).getUid() + "\";";
        try {
            ResultSet rs = DBLogInManagerMySQL.MakeConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                ((Employee) entity).setName(rs.getString("name"));
                ((Employee) entity).setType(rs.getInt("type"));
                ((Employee) entity).setGender(rs.getString("gender"));
                ((Employee) entity).setAge(rs.getInt("age"));
                ((Employee) entity).setMail(rs.getString("mail"));
                ((Employee) entity).setMonthlySalary(rs.getDouble("MonthlySalary"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public static void Employeedelete(String uid) {
            String sql = "DELETE from hospice.employee where uid=\""+uid+"\";";
            String sql1 = "DELETE from hospice.login where uid=\""+uid+"\";";
            String sql2 = "DELETE from hospice.providedservice WHERE e_uid= '" + uid +"' ;";
            try {
                DBLogInManagerMySQL.MakeConnection().createStatement().execute(sql);
                DBLogInManagerMySQL.MakeConnection().createStatement().execute(sql1);
                //Comment it out if you dont want it to delete record from provided service
                DBLogInManagerMySQL.MakeConnection().createStatement().execute(sql2);
            }
            catch (Exception e){
                e.printStackTrace();

            }

        }
    public static boolean IsEmployeeAvailable(String uid) {
        String sql = "Select * from hospice.employee where uid=\"" + uid + "\";";
        ResultSet rs = null;
        int count=0;
        try {
            rs = DBLogInManagerMySQL.MakeConnection().createStatement().executeQuery(sql);
            while(rs.next()){ count++;}

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count==0) {
            return false;
        } else {
            return true;
        }
    }

}
