package com.aem.hospice;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;
        import java.io.IOException;

public class EPServiceController extends EmployeepageController{


    public void epservice(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EPService.fxml"));

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

}
