module com.aem.hospice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.aem.hospice to javafx.fxml;
    exports com.aem.hospice;
    exports com.aem.hospice.classes;
    opens com.aem.hospice.classes to javafx.fxml;
    exports com.aem.hospice.pages;
    opens com.aem.hospice.pages to javafx.fxml;
    exports com.aem.hospice.controller;
    opens com.aem.hospice.controller to javafx.fxml;
    exports com.aem.hospice.Merge;
    opens com.aem.hospice.Merge to javafx.fxml;
}