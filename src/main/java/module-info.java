module com.polibius.businessmanagementappdesk {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;


    opens com.polibius.businessmanagementappdesk to javafx.fxml;
    exports com.polibius.businessmanagementappdesk;
    exports com.polibius.businessmanagementappdesk.controller;
    opens com.polibius.businessmanagementappdesk.controller to javafx.fxml;
}