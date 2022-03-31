module com.aem.hospice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.aem.hospice to javafx.fxml;
    exports com.aem.hospice;
    exports com.aem.hospice.classes;
    opens com.aem.hospice.classes to javafx.fxml;
    exports com.aem.hospice.PopUp;
    opens com.aem.hospice.PopUp to javafx.fxml;
    exports com.aem.hospice.Employee;
    opens com.aem.hospice.Employee to javafx.fxml;
    exports com.aem.hospice.Patient;
    opens com.aem.hospice.Patient to javafx.fxml;
    exports com.aem.hospice.LoginRegister;
    opens com.aem.hospice.LoginRegister to javafx.fxml;
}