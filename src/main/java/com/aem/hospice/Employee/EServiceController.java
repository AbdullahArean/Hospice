package com.aem.hospice.Employee;

import com.aem.hospice.Classes.DBLogInManagerMySQL;
import com.aem.hospice.Classes.ProvidedService;
import com.aem.hospice.Classes.Service;
import com.aem.hospice.Classes.ServiceDBConnectorMySQL;
import com.aem.hospice.PopUp.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EServiceController extends EmployeepageController implements Initializable {
    @FXML
    private TextField add_cpu;

    @FXML
    private TextArea add_sd;

    @FXML
    private TextField add_sname;

    @FXML
    private TextField add_st;

    @FXML
    private TableColumn<Service, Double> col_cpu;

    @FXML
    private TableColumn<Service, String> col_sdesc;

    @FXML
    private TableColumn<Service, String> col_sname;

    @FXML
    private TableColumn<Service, Integer> col_stype;

    @FXML
    private TableColumn<Service, String> col_suid;

    @FXML
    private TextField del_suid;

    @FXML
    private TableView<Service> estable;

    @FXML
    private TextField upd_cpu;

    @FXML
    private TextArea upd_sd;

    @FXML
    private TextField upd_sname;

    @FXML
    private TextField upd_st;

    @FXML
    private TextField upd_suid;

    private ObservableList<Service> list = FXCollections.observableArrayList();


    @FXML
    void bt_addservice_p(ActionEvent event) throws IOException {
        try{
            Integer st = Integer.parseInt(add_st.getText());
            Double cpu  = Double.parseDouble(add_cpu.getText());
            String sname = add_sname.getText();
            String sd =add_sd.getText();
            Service s1 = new Service(sname,st,sd,cpu,0);
            AlertBox.display("New Service Added","Please Click Ok to continue");
            EServiceController es = new EServiceController();
            es.eservice(event);
        }
        catch (NumberFormatException e){
            AlertBox.display("Invalid Input","Fill Up the boxes with correct data");
        }


    }

    @FXML
    void bt_delservice_p(ActionEvent event) throws SQLException, IOException {
        String givenuid =upd_suid.getText();
        if(ServiceDBConnectorMySQL.IsServiceAvailable(givenuid)) {
            ServiceDBConnectorMySQL.Servicedelete(givenuid);
            AlertBox.display("Service Deleted", "Please Click Ok to continue");
            EServiceController es = new EServiceController();
            es.eservice(event);
        }
        else{
            AlertBox.display("Service Details Not Available", "Please Give Valid Input");
        }
    }

    @FXML
    void bt_loadservice_p(ActionEvent event) {
        String givenuid =upd_suid.getText();
        if(ServiceDBConnectorMySQL.IsServiceAvailable(givenuid)){
            Service s1 = new Service(givenuid);
            upd_sname.setText(""+s1.getName());
            upd_cpu.setText(""+s1.getCost_unit());
            upd_sd.setText(""+s1.getDescription());
            upd_st.setText(""+s1.getType());
        }
        else{
            AlertBox.display("Service Details Not Available", "Please Give Valid Input");
        }

    }

    @FXML
    void bt_update_p(ActionEvent event) throws IOException {
    try{
        Service s1 = new Service(upd_suid.getText());
        s1.setName(upd_sname.getText());
        s1.setCost_unit(Double.parseDouble(upd_cpu.getText()));
        s1.setDescription(upd_sd.getText());
        s1.setType(Integer.parseInt(upd_st.getText()));
        AlertBox.display("Service Updated", "Please Click Ok to continue");
        EServiceController es = new EServiceController();
        es.eservice(event);
    }catch (NumberFormatException e){
            AlertBox.display("Invalid Input","Fill Up the boxes with correct data");
        }


    }
    public void eservice(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EService.fxml"));
            Parent root = loader.load();

            // System.out.println("second ");
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.setTitle("Hospice Employee Service");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void showservices(){
        try {
            Connection conn = DBLogInManagerMySQL.MakeConnection();
            Statement mysta = conn.createStatement();
            String sql = "SELECT * from service WHERE uid>10";
            ResultSet rs = mysta.executeQuery(sql);
            while (rs.next()) {
                list.add(new Service(rs.getString("uid")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showservices();
        col_sname.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_cpu.setCellValueFactory(new PropertyValueFactory<>("cost_unit"));
        col_suid.setCellValueFactory(new PropertyValueFactory<>("uid"));
        col_sdesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_stype.setCellValueFactory(new PropertyValueFactory<>("type"));
        estable.setItems(list);
    }
}