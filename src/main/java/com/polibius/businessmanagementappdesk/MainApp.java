package com.polibius.businessmanagementappdesk;

import com.polibius.businessmanagementappdesk.database.DBConnection;
import com.polibius.businessmanagementappdesk.repository.UserRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DBConnection.getConnection();
        UserRepository.initializeTable();
        UserRepository.insertDefaultAdmin();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("fxml/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        URL css = getClass().getResource("css/application.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        } else {
            System.err.println("WARNING: application.css not found. Running without styles.");
        }

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        //splash screen should launch while app sets up
        //app setup

        //end app setup
        //remove splash screen
        launch();
    }
}