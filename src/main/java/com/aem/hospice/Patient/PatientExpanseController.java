package com.aem.hospice.Patient;

import com.aem.hospice.Classes.ProvidedService;
import com.aem.hospice.Classes.DBLogInManagerMySQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PatientExpanseController extends PatientpageController implements Initializable {
    @FXML
    private TableColumn<ProvidedService, Double>col_paid;

    @FXML
    private TableColumn<ProvidedService, String> col_pstatus;

    @FXML
    private TableColumn<ProvidedService, Double>  col_sexpanse;

    @FXML
    private TableColumn<ProvidedService, String> col_sname;

    @FXML
    private TableColumn<ProvidedService, Integer>  col_squantity;

    @FXML
    private Label due;

    @FXML
    private TableView<ProvidedService> exptable;

    @FXML
    private Label paid;
    @FXML
    private Label total_expanse;
    private double e=0,p=0,d=0;
    private ObservableList<ProvidedService> list = FXCollections.observableArrayList();

    public void patientexpanse(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Patient.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Hospice Patient Total Expanse");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void calculateexpanse(String coln, String givenuid){
        try {
            Connection conn = DBLogInManagerMySQL.MakeConnection();
            Statement mysta = conn.createStatement();
            String sql = "SELECT * from providedservice WHERE " + coln + "= '" + givenuid + "' ;";
            ResultSet rs = mysta.executeQuery(sql);
            while (rs.next()) {
                e+= rs.getDouble("bill");
                p+= rs.getDouble("paid");
                d+=rs.getDouble("bill")-rs.getDouble("paid");
                list.add(new ProvidedService(rs.getString("ps_uid")));

            }
            patient1.setTotalBill(e);
            patient1.setOtherExpanse(e-patient1.getLabExpanse()- patient1.getPharmacyExpanse());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calculateexpanse("p_uid", patient1.getUid());
        total_expanse.setText(""+e);
        paid.setText(""+p);
        due.setText(""+d);
        col_sname.setCellValueFactory(new PropertyValueFactory<>("s_type"));
        col_sexpanse.setCellValueFactory(new PropertyValueFactory<>("bill"));
        col_squantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_pstatus.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
        col_paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        exptable.setItems(list);

    }
}