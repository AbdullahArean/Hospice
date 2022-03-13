module com.aem.hospice {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.aem.hospice to javafx.fxml;
    exports com.aem.hospice;
}