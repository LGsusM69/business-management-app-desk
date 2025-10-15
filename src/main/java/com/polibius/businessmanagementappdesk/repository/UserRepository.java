package com.polibius.businessmanagementappdesk.repository;

import com.polibius.businessmanagementappdesk.database.DBConnection;
import com.polibius.businessmanagementappdesk.util.PasswordUtils;

import java.sql.*;

public class UserRepository {

    public static void initializeTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                email TEXT NOT NULL UNIQUE,
                username TEXT NOT NULL UNIQUE,
                password_hash TEXT NOT NULL,
                created_at TEXT DEFAULT CURRENT_TIMESTAMP
            );
        """;

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Users table ready");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDefaultAdmin() {
        String checkQuery = "SELECT COUNT(*) FROM users";
        String insertQuery = "INSERT INTO users (email, username, password_hash) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             Statement checkStmt = conn.createStatement();
             ResultSet rs = checkStmt.executeQuery(checkQuery)) {

            if (rs.next() && rs.getInt(1) == 0) {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, "admin@polibius.com");
                    insertStmt.setString(2, "admin");
                    insertStmt.setString(3, PasswordUtils.hashPassword("admin"));
                    insertStmt.executeUpdate();
                    System.out.println("Default admin user inserted.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
