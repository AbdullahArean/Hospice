package com.aem.hospice.Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDBConnectorMySQL implements ClassDBConnector{
    private PreparedStatement pstmt;
    private PreparedStatement getPreparedStatement(String query, Service s) throws SQLException {
        pstmt = DBLogInManagerMySQL.MakeConnection().prepareStatement(query);
        pstmt.setString(1, s.getUid());
        pstmt.setString(2, s.getName());
        pstmt.setInt(3, s.getType());
        pstmt.setString(4, s.getDescription());
        pstmt.setDouble(5, s.getCost_unit());
        pstmt.setDouble(6, s.getDiscount());
        return pstmt;
    }
    @Override
    public void InsertIntoDatabase(RealEntity entity) {
        String query = "INSERT INTO service(uid, name, type,description,cost_unit,discount)" + "VALUES (?, ?, ?,?, ?, ?)";
        try {
            pstmt = getPreparedStatement(query, (Service)entity);
            pstmt.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void UpdateIntoDatabase(RealEntity entity) {
        String query = "UPDATE service set uid=?, name=?, type=?,description=?,cost_unit=?,discount=? WHERE uid =? ;";
       try {
            pstmt = getPreparedStatement(query,(Service) entity);
            pstmt.setString(7, ((Service)entity).getUid());
            pstmt.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void InsertFromDatabase(RealEntity entity) {

        String sql = "Select * from hospice.service where uid=\""+((Service)entity).getUid()+"\";";
        try{
            ResultSet rs = DBLogInManagerMySQL.MakeConnection().createStatement().executeQuery(sql);
            while(rs.next()){
                ((Service) entity).setName(rs.getString("name"));
                ((Service) entity).setType(rs.getInt("type"));
                ((Service) entity).setDescription( rs.getString("description"));
                ((Service) entity).setCost_unit(rs.getDouble("cost_unit"));
                ((Service) entity).setDiscount(rs.getDouble("discount"));

            }

        }catch (Exception e){
            e.printStackTrace();
        }



    }
    public static void Servicedelete(String uid) throws SQLException {
        String sql = "DELETE from hospice.service where uid=\""+uid+"\";";
        String sql2 = "DELETE from hospice.providedservice WHERE s_uid= '" + uid +"' ;";
        DBLogInManagerMySQL.MakeConnection().createStatement().execute(sql);
        //Comment it out if you dont want it to delete record from provided service
        DBLogInManagerMySQL.MakeConnection().createStatement().execute(sql2);
    }
    public static boolean IsServiceAvailable(String uid) {
        String sql = "Select * from hospice.service where uid=\"" + uid + "\";";
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
