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

public class EPServiceController extends EmployeepageController implements Initializable {

    @FXML
    private TextField add_paid;

    @FXML
    private TextField add_puid;

    @FXML
    private TextField add_q;

    @FXML
    private TextField add_suid;

    @FXML
    private TableColumn<ProvidedService, Double> col_bill;

    @FXML
    private TableColumn<ProvidedService, String> col_euid;

    @FXML
    private TableColumn<ProvidedService, Double> col_paid;

    @FXML
    private TableColumn<ProvidedService, String> col_psuid;

    @FXML
    private TableColumn<ProvidedService, String> col_puid;

    @FXML
    private TableColumn<ProvidedService, String> col_suid;

    @FXML
    private TableColumn<ProvidedService, Integer> col_sq;

    @FXML
    private TextField del_psuid;

    @FXML
    private TableView<ProvidedService> epstable;

    @FXML
    private TextField upd_bill;

    @FXML
    private TextField upd_euid;

    @FXML
    private TextField upd_paid;

    @FXML
    private TextField upd_psuid;

    @FXML
    private TextField upd_puid;

    @FXML
    private TextField upd_q;

    @FXML
    private TextField upd_suid;
    private ObservableList<ProvidedService> list = FXCollections.observableArrayList();

    private void showpservices(){
        try {
            String sql = "SELECT * from providedservice WHERE ps_uid>100000";
            ResultSet rs = DBLogInManagerMySQL.MakeConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                list.add(new ProvidedService(rs.getString("ps_uid")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    void bt_addpservice_p(ActionEvent event) throws SQLException, IOException {
        try{
        String suid = add_suid.getText();
        String puid =add_puid.getText();
        String euid = employee.getUid();
        Integer quantity = Integer.valueOf(add_q.getText());
        ProvidedService ps1 = new ProvidedService(suid,puid,euid,quantity);
        ps1.setQuantity(quantity);
        ps1.setPaid(Double.valueOf(add_paid.getText()));
        AlertBox.display("New Provided Service Created","Please Click Ok to continue");
        EPServiceController eps = new EPServiceController();
        eps.epservice(event);}
        catch (NumberFormatException e){
            AlertBox.display("Invalid Input","Fill Up the boxes with correct data");
        }

    }

    @FXML
    void bt_delpservice_p(ActionEvent event) throws SQLException, IOException {
        String givenuid =del_psuid.getText();
        if(ProvidedServiceDBConnectorMySQL.IsProvidedServiceAvailable(givenuid)){
        ProvidedServiceDBConnectorMySQL.ProvidedServicedelete(del_psuid.getText());
        AlertBox.display("Provided Service Deleted","Please Click Ok to continue");
        EPServiceController eps = new EPServiceController();
        eps.epservice(event);}
        else{
            AlertBox.display("Provided Service Details Unavailable", "Invalid Input");
        }

    }

    @FXML
    void bt_psload_p(ActionEvent event) throws SQLException {
        String givenuid =upd_psuid.getText();
        if(ProvidedServiceDBConnectorMySQL.IsProvidedServiceAvailable(givenuid)){
        ProvidedService ps1 = new ProvidedService(givenuid);
        upd_bill.setText(""+ps1.getBill());
        upd_euid.setText(""+ps1.getE_uid());
        upd_paid.setText(""+ps1.getPaid());
        upd_q.setText(""+ps1.getQuantity());
        upd_suid.setText(""+ps1.getS_uid());
        upd_puid.setText(""+ps1.getP_uid());}
        else{
            AlertBox.display("Provided Service Details Unavailable", "Invalid Input");
        }
    }

    @FXML
    void bt_psupdate_p(ActionEvent event) throws SQLException, IOException {
        try{ProvidedService ps2 = new ProvidedService(upd_psuid.getText());
        ps2.setQuantity(Integer.parseInt(upd_q.getText()));
        ps2.setS_uid(upd_suid.getText());
        ps2.setE_uid(upd_euid.getText());
        ps2.setPaid(Double.parseDouble(upd_paid.getText()));
        ps2.setP_uid(upd_puid.getText());
        AlertBox.display("Provided Service Updated","Please Click Ok to continue");
        EPServiceController eps = new EPServiceController();
        eps.epservice(event);}
        catch (NumberFormatException e){
        AlertBox.display("Invalid Input","Fill Up the boxes with correct data");
    }

    }

    public void epservice(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EPService.fxml"));
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    showpservices();
    col_psuid.setCellValueFactory(new PropertyValueFactory<>("ps_uid"));
    col_suid.setCellValueFactory(new PropertyValueFactory<>("s_uid"));
    col_euid.setCellValueFactory(new PropertyValueFactory<>("e_uid"));
    col_bill.setCellValueFactory(new PropertyValueFactory<>("bill"));
    col_paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
    col_sq.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    col_puid.setCellValueFactory(new PropertyValueFactory<>("p_uid"));
epstable.setItems(list);
    }

}
