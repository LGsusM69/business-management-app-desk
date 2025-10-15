package com.polibius.businessmanagementappdesk.database;

import org.sqlite.SQLiteException;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    private static final String APP_FOLDER =
            System.getenv("APPDATA") + File.separator + "Polibius";
    private static final String DB_PATH =
            APP_FOLDER + File.separator + "business.db";

    private DBConnection() {

    }

    public static Connection getConnection() {
        if(connection == null) {
            try {
                File directory = new File(APP_FOLDER);
                if(!directory.exists()) {
                    directory.mkdirs();
                }
                String url = "jdbc:sqlite:" + DB_PATH;
                connection = DriverManager.getConnection(url);
                System.out.println("Connected to SQLite at: " + DB_PATH);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
