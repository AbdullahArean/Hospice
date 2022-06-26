package com.aem.hospice.Employee;

import com.aem.hospice.Classes.*;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EEmployeeController extends EmployeepageController implements Initializable {
    @FXML
    private TextField add_eage;

    @FXML
    private TextField add_egender;

    @FXML
    private TextField add_email;

    @FXML
    private TextField add_ems;

    @FXML
    private TextField add_ename;

    @FXML
    private TextField add_etype;

    @FXML
    private TextField del_euid;

    @FXML
    private TextField upd_eage;

    @FXML
    private TextField upd_egender;

    @FXML
    private TextField upd_email;

    @FXML
    private TextField upd_ems;

    @FXML
    private TextField upd_ename;

    @FXML
    private TextField upd_etype;

    @FXML
    private TextField upd_euid;
    @FXML
    private TableColumn<Employee, Integer> col_page;

    @FXML
    private TableColumn<Employee, String> col_pgender;

    @FXML
    private TableColumn<Employee, String> col_pmail;

    @FXML
    private TableColumn<Employee, Double> col_pmh;

    @FXML
    private TableColumn<Employee, String> col_pname;

    @FXML
    private TableColumn<Employee, String> col_puid;


    @FXML
    private TableColumn<Employee, Integer> col_etype;

    @FXML
    private TableView<Employee> eptable;

    private ObservableList<Employee> list = FXCollections.observableArrayList();

    @FXML
    void bt_addemployee_p(ActionEvent event) throws IOException, SQLException {
        Employee e1 = new Employee(add_ename.getText(),Integer.parseInt(add_etype.getText()),add_egender.getText(),Integer.parseInt(add_eage.getText()), add_email.getText(),Double.parseDouble(add_ems.getText()));
        EEmployeeController epa = new EEmployeeController();
        epa.eemployee(event);

    }

    @FXML
    void bt_delemployee_p(ActionEvent event) throws SQLException, IOException {
        EmployeeDBConnectorMySQL.Employeedelete(del_euid.getText());
        AlertBox.display("Employee Successfully Deleted","Press Ok to Continue");
        EEmployeeController epa = new EEmployeeController();
        epa.eemployee(event);

    }

    @FXML
    void bt_loademployee_p(ActionEvent event) throws SQLException {
        String givenuid = upd_euid.getText();
        Employee p1 = new Employee(givenuid);
        upd_ems.setText(""+p1.getMonthlySalary());
        upd_eage.setText(""+p1.getAge());
        upd_email.setText(""+p1.getMail());
        upd_ename.setText(""+p1.getName());
        upd_egender.setText(""+p1.getGender());
        upd_etype.setText(""+p1.getType());

    }

    @FXML
    void bt_setdefaultpass_p(ActionEvent event) {
        DBLogInManagerMySQL.ChangePasswordAdminPrevilage(upd_euid.getText(),"a12345678A");
        AlertBox.display("Password Changed to Default", "Password is set to =>a12345678A");

    }

    @FXML
    void bt_updateemployee_p(ActionEvent event) throws SQLException, IOException {
        Employee p1 = new Employee(upd_euid.getText());
        p1.setGender(upd_egender.getText());
        p1.setName(upd_ename.getText());
        p1.setMail(upd_email.getText());
        p1.setAge(Integer.parseInt(upd_eage.getText()));
        p1.setType(Integer.parseInt(upd_etype.getText()));
        p1.setMonthlySalary(Double.parseDouble(upd_ems.getText()));
        AlertBox.display("Employee Detailes Successfully Updated","Press Ok to Continue");
        EEmployeeController epa = new EEmployeeController();
        epa.eemployee(event);

    }



    public void eemployee(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EEmployee.fxml"));
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
    private void CollectFromDatabase() {
        try {
            String sql = "SELECT * from employee WHERE uid>30000";
            ResultSet rs = DBLogInManagerMySQL.MakeConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                list.add(new Employee(rs.getString("uid")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CollectFromDatabase();
        col_puid.setCellValueFactory(new PropertyValueFactory<>("uid"));
        col_pname.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_page.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_pmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        col_pgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_pmh.setCellValueFactory(new PropertyValueFactory<>("MonthlySalary"));
        col_etype.setCellValueFactory(new PropertyValueFactory<>("type"));
        eptable.setItems(list);
    }


}
