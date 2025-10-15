module com.polibius.businessmanagementappdesk {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.polibius.businessmanagementappdesk to javafx.fxml;
    exports com.polibius.businessmanagementappdesk;
}