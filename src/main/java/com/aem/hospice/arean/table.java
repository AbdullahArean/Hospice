package com.aem.hospice.arean;
import com.aem.hospice.AlertBox;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class table extends Application {
    Stage window;
    TableView<patient> table;
    TextField name;
    TextField exp;
    TextField sn;


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Table");
        //Table colums
        TableColumn<patient, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<patient, String> expColumn = new TableColumn<>("Expanse");
        expColumn.setMinWidth(200);
        expColumn.setCellValueFactory(new PropertyValueFactory<>("Expanse"));

        TableColumn<patient, String> snColumn = new TableColumn<>("Service Number");
        snColumn.setMinWidth(200);
        snColumn.setCellValueFactory(new PropertyValueFactory<>("Service_Number"));

        //Table Creation
        table = new TableView<>();
        table.setItems(getpatient());
        table.getColumns().addAll(nameColumn, expColumn, snColumn);

        //Name input
        name = new TextField();
        name.setPromptText("Enter Patient Name");
        name.setMinWidth(100);
        //Expanse input
        exp = new TextField();
        exp.setPromptText("Enter Patient Expanse");
        exp.setMinWidth(100);
        //Name sn
        sn = new TextField();
        sn.setPromptText("Enter Patient Service Number");
        sn.setMinWidth(100);
        //Button
        Button add = new Button("Add");
        add.setOnAction(e-> addclicked());
        Button clear = new Button("Clear");
        clear.setOnAction(e->clearclicked());
        Button del = new Button("Delete Selected");
        del.setOnAction(e->deleteclicked());


        //Layout and show
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(name, exp, sn,add, clear,del);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);
        Scene scene = new Scene(vBox,720, 300);
        window.setScene(scene);
        window.show();

    }

    private void deleteclicked() {
        ObservableList<patient> all, selected;
        all = table.getItems();
        selected = table.getSelectionModel().getSelectedItems();
        selected.forEach(all::remove);

    }

    public ObservableList<patient> getpatient() {
        ObservableList<patient> patients = FXCollections.observableArrayList();
        patients.add(new patient("arean", 590, 5));
        patients.add(new patient("tahian", 59, 54));
        patients.add(new patient("arrn", 5909, 15));
        patients.add(new patient("an", 590, 5));
        return patients;
    }
    private void addclicked(){
        if(validate(name.getText(), exp.getText(),sn.getText()))
        {
            System.out.println("Input Successful");
            patient newp = new patient(name.getText(), exp.getText(),sn.getText());
            table.getItems().add(newp);
            name.clear();
            exp.clear();
            sn.clear();
        }
        else{
            AlertBox.display("Invalid Input", "Please Try Again");
            name.clear();
            exp.clear();
            sn.clear();
        }

    }
    private void clearclicked(){
        name.clear();
        exp.clear();
        sn.clear();

    }
    private static boolean validate(String a, String b, String c){
        try{
            new patient(a, Double.parseDouble(b), Integer.parseInt(c));
            return true;
        }
        catch (Exception e){
            return false;
        }

    }


}
