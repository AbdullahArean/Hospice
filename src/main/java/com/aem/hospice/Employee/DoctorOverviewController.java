
package com.aem.hospice.Employee;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

        import java.io.IOException;

public class DoctorOverviewController extends EmployeepageController{


    @FXML
    void appointments_p(ActionEvent event) {
    }

    @FXML
    void newpres_p(ActionEvent event) {

    }


    @FXML
    void viewpatientinfo_p(ActionEvent event) {

    }

    @FXML
    void visit_p(ActionEvent event) {

    }

    public void doctoroverview(ActionEvent actionEvent)throws IOException {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("DoctorOverview.fxml"));
            Parent root=loader.load();
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
}
