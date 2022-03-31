package com.aem.hospice.Patient;

import com.aem.hospice.classes.ProvidedService;
import com.aem.hospice.classes.database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;


public class LabReportController extends PatientpageController implements Initializable {

    @FXML
    private TableColumn<ProvidedService, Double> col_expanse;

    @FXML
    private TableColumn<ProvidedService, String> col_lreport;

    @FXML
    private TableColumn<ProvidedService, String> col_ntest;

    @FXML
    private TableColumn<ProvidedService, Double> col_paid;

    @FXML
    private TableColumn<ProvidedService, Boolean> col_pstatus;

    @FXML
    private TableColumn<ProvidedService, Integer> col_quantity;

    @FXML
    private TableView<ProvidedService> labtable;

    @FXML
    private Label due;
    private double e=0,p=0,d=0;

    @FXML
    private Label paid;

    @FXML
    private Label total_expanse;

    public void labreport(ActionEvent actionEvent)throws IOException {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("LabReport.fxml"));
            Parent root=loader.load();

            // System.out.println("second ");
            Stage stage=(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

            stage.setTitle("Hospice");
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void calculateexpanse(int type, String coln, String uid5){
        try {
            Connection conn = database.MakeConnection();
            Statement mysta = conn.createStatement();
            String sql = "SELECT * from providedservice WHERE s_type= '" + type + "' AND " + coln + "= '" + uid5 + "' ;";
            //System.out.println(sql);
            ResultSet rs = mysta.executeQuery(sql);
            while (rs.next()) {
                e+= rs.getDouble("bill");
                p+= rs.getDouble("paid");
                d+=rs.getDouble("bill")-rs.getDouble("paid");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calculateexpanse(2,"p_uid", patient1.getUid());
        total_expanse.setText(""+e);
        paid.setText(""+p);
        due.setText(""+d);
        col_ntest.setCellValueFactory(new PropertyValueFactory<>("s_uid"));
        col_expanse.setCellValueFactory(new PropertyValueFactory<>("bill"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_pstatus.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
        col_paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        labtable.setItems(database.GetProvidedService(2,"p_uid",patient1.getUid()));

    }
}
