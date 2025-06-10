package ru.samsung.mysteryforest;

import java.io.File;
import java.sql.*;

public class DatabaseHelper {
    Main main;
    private static final String DB_NAME = "assets/data/users1.db";
    private static final String CONNECTION_URL = "jdbc:sqlite:" + DB_NAME;

    private Connection connection;

    public DatabaseHelper(Main main) {
        this.main = main;
        connect();
        createTables();
    }

    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(CONNECTION_URL);
            System.out.println("Connected to SQLite database");
        } catch (Exception e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

    private void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT UNIQUE NOT NULL, " +
            "password TEXT NOT NULL, " +
            "AttentionLara INTEGER DEFAULT 0, " +
            "AttentionAgata INTEGER DEFAULT 0, " +
            "AttentionFamily INTEGER DEFAULT 0, " +
            "Bank INTEGER DEFAULT 0, " +
            "station TEXT DEFAULT '')";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'users' created or already exists with all columns");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public long addUser(String username, String password) {
        String sql = "INSERT INTO users(username, password, AttentionLara, AttentionAgata, AttentionFamily, Bank, station) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, String.valueOf(main.AttentionLara));
            pstmt.setString(4, String.valueOf(main.AttentionAgata));
            pstmt.setString(5, String.valueOf(main.AttentionFamily));
            pstmt.setString(6, String.valueOf(main.Bank));
            pstmt.setString(7, String.valueOf(main.Station));

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
        return -1;
    }

    public boolean checkUser(String username, String password) {
        String sql = "SELECT id, AttentionLara, AttentionAgata, AttentionFamily, Bank, station FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    main.Id = rs.getInt("id");
                    main.Station = rs.getString("station");
                    main.AttentionLara = rs.getInt("AttentionLara");
                    main.AttentionAgata = rs.getInt("AttentionAgata");
                    main.AttentionFamily = rs.getInt("AttentionFamily");
                    main.Bank = rs.getInt("Bank");
                    main.Username = username;
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking user: " + e.getMessage());
        }
        return false;
    }

    public boolean userExists(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error checking if user exists: " + e.getMessage());
        }
        return false;
    }

    public long updateInformation(int id) {
        String sql = "UPDATE users SET AttentionLara=?, AttentionAgata=?, AttentionFamily=?, Bank=?, station=? WHERE id=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(main.AttentionLara));
            pstmt.setString(2, String.valueOf(main.AttentionAgata));
            pstmt.setString(3, String.valueOf(main.AttentionFamily));
            pstmt.setString(4, String.valueOf(main.Bank));
            pstmt.setString(5, String.valueOf(main.Station));
            pstmt.setInt(6, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return id;  // Возвращаем ID, если обновление прошло
            }
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
        }
        return -1;  // Если обновление не удалось
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
