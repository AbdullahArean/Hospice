package com.aem.hospice;

import com.aem.hospice.classes.ProvidedService;
import com.aem.hospice.classes.database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;

public class MedicineController extends PatientpageController implements Initializable{
    @FXML
    private TableColumn<ProvidedService, Double> col_medexpase;

    @FXML
    private TableColumn<ProvidedService, String> col_medname;

    @FXML
    private TableColumn<ProvidedService, Integer> col_medquantity;

    @FXML
    private TableColumn<ProvidedService, Double> col_paid;

    @FXML
    private TableColumn<ProvidedService, Boolean> col_pstatus;

    @FXML
    private TableView<ProvidedService> medtable;


    public void medicine(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Medicine.fxml"));

            Parent root = loader.load();

            // System.out.println("second ");

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Hospice");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_medname.setCellValueFactory(new PropertyValueFactory<>("s_uid"));
        col_medexpase.setCellValueFactory(new PropertyValueFactory<>("bill"));
        col_medquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_pstatus.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
       medtable.setItems(database.GetProvidedService(1,"p_uid",patient1.getUid()));

    }
}
