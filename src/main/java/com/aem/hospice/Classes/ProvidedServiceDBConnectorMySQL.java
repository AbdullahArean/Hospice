package com.aem.hospice.Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProvidedServiceDBConnectorMySQL implements ClassDBConnector{
    private PreparedStatement PrepareStatement_PS;
    private PreparedStatement getPreparedStatement( String query, ProvidedService ps) {

        try{
            PrepareStatement_PS = DBLogInManagerMySQL.MakeConnection().prepareStatement(query);
            PrepareStatement_PS.setString(1, ps.getPs_uid());
            PrepareStatement_PS.setString(2, ps.getS_uid());
            PrepareStatement_PS.setString(3, ps.getP_uid());
            PrepareStatement_PS.setString(4, ps.getE_uid());
            PrepareStatement_PS.setString(5, ps.getR_uid());
            PrepareStatement_PS.setInt(6, ps.getS_type());
            PrepareStatement_PS.setInt(7, ps.getQuantity());
            PrepareStatement_PS.setInt(8, ps.getPayment_status());
            PrepareStatement_PS.setDouble(9, ps.getBill());
            PrepareStatement_PS.setDouble(10, ps.getPaid());
            return PrepareStatement_PS;}
        catch (Exception E){
            E.printStackTrace();

        }
        return null;
    }
    @Override
    public void InsertIntoDatabase(RealEntity entity) {
        String query = "INSERT INTO providedservice(ps_uid,s_uid ,p_uid, e_uid, r_uid,s_type,quantity,payment_status,bill,paid)" + "VALUES (?, ?, ?,?, ?, ?,?,?,?,?)";
        try {
            PrepareStatement_PS =getPreparedStatement(query, (ProvidedService)entity);
            PrepareStatement_PS.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void UpdateIntoDatabase(RealEntity entity) {
        String query = "UPDATE providedservice set ps_uid=?,s_uid=? ,p_uid=?, e_uid=?, r_uid=?,s_type=?,quantity=?,payment_status=?, bill=?,paid=? WHERE ps_uid =? ;";
        try {
            PrepareStatement_PS =getPreparedStatement(query,(ProvidedService)entity);
            PrepareStatement_PS.setString(11, ((ProvidedService)entity).getPs_uid());
            PrepareStatement_PS.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void InsertFromDatabase(RealEntity entity) {
        String sql = "Select * from hospice.providedservice where ps_uid=\""+((ProvidedService)entity).getPs_uid()+"\";";
        try{
            ResultSet rs = DBLogInManagerMySQL.MakeConnection().createStatement().executeQuery(sql);
            while(rs.next()){
                ((ProvidedService)entity).setPs_uid(rs.getString("ps_uid"));
                ((ProvidedService)entity).setS_uid(rs.getString("s_uid"));
                ((ProvidedService)entity).setP_uid(rs.getString("p_uid"));
                ((ProvidedService)entity).setE_uid(rs.getString("e_uid"));
                ((ProvidedService)entity).setR_uid(rs.getString("r_uid"));
                ((ProvidedService)entity).setQuantity(rs.getInt("quantity"));
                ((ProvidedService)entity).setPayment_status(rs.getInt("payment_status"));
                ((ProvidedService)entity).setS_type(rs.getInt("s_type"));
                ((ProvidedService)entity).setBill( rs.getDouble("bill"));
                ((ProvidedService)entity).setPaid( rs.getDouble("paid"));

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
