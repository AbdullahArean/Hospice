module com.aem.hospice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.aem.hospice to javafx.fxml;
    exports com.aem.hospice;
}