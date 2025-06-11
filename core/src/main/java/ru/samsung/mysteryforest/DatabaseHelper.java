package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.sql.*;
import java.io.File;

public class DatabaseHelper {
    private Main main;
    private Connection connection;
    private final boolean isAndroid;
    private final String dbName = "mysteryforest.db";

    public DatabaseHelper(Main main) {
        this.main = main;
        this.isAndroid = Gdx.app.getType() == ApplicationType.Android;
        connect();
    }

    private void connect() {
        try {
            if (isAndroid) {
                // Настройка для Android
                Class.forName("org.sqldroid.SQLDroidDriver");
                String dbPath = Gdx.files.getLocalStoragePath() + dbName;

                // Создаем директорию, если не существует
                File dbFile = new File(dbPath);
                if (!dbFile.getParentFile().exists()) {
                    dbFile.getParentFile().mkdirs();
                }

                connection = DriverManager.getConnection("jdbc:sqldroid:" + dbPath);
                Gdx.app.log("Database", "Android DB path: " + dbPath);
            } else {
                // Настройка для Desktop
                Class.forName("org.sqlite.JDBC");
                FileHandle dbFile = Gdx.files.local("data/" + dbName);
                if (!dbFile.parent().exists()) {
                    dbFile.parent().mkdirs();
                }
                connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.file().getAbsolutePath());
                Gdx.app.log("Database", "Desktop DB path: " + dbFile.file().getAbsolutePath());
            }

            createTableIfNotExists();
        } catch (Exception e) {
            throw new GdxRuntimeException("Error connecting to database", e);
        }
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT UNIQUE NOT NULL," +
            "password TEXT NOT NULL," +
            "AttentionLara INTEGER DEFAULT 0," +
            "AttentionAgata INTEGER DEFAULT 0," +
            "AttentionFamily INTEGER DEFAULT 0," +
            "Bank INTEGER DEFAULT 0," +
            "station TEXT DEFAULT 'screenStart')";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            Gdx.app.log("Database", "Table created/checked");
        } catch (SQLException e) {
            Gdx.app.error("Database", "Error creating table", e);
        }
    }

    public long addUser(String username, String password) throws SQLException {
        if (username == null || password == null) {
            Gdx.app.error("Database", "Username or password is null!");
            return -1;
        }

        // Проверка соединения
        if (connection == null || connection.isClosed()) {
            connect(); // Переподключаемся
        }

        String sql = "INSERT INTO users(username, password, AttentionLara, AttentionAgata, " +
            "AttentionFamily, Bank, station) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setInt(3, main.AttentionLara);
            pstmt.setInt(4, main.AttentionAgata);
            pstmt.setInt(5, main.AttentionFamily);
            pstmt.setInt(6, main.Bank);
            pstmt.setString(7, main.Station);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    Gdx.app.log("Database", "User added with ID: " + id);
                    return id;
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            Gdx.app.error("Database", "Error adding user", e);
            throw e;
        }
    }

    public boolean checkUser(String username, String password) {
        String sql = "SELECT id, AttentionLara, AttentionAgata, AttentionFamily, Bank, station " +
            "FROM users WHERE username = ? AND password = ?";

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
                    Gdx.app.log("Database", "User found: " + username);
                    return true;
                }
            }
        } catch (SQLException e) {
            Gdx.app.error("Database", "Error checking user", e);
        }
        return false;
    }

    public boolean userExists(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                boolean exists = rs.next();
                Gdx.app.log("Database", "User exists check for " + username + ": " + exists);
                return exists;
            }
        } catch (SQLException e) {
            Gdx.app.error("Database", "Error checking if user exists", e);
        }
        return false;
    }

    public boolean updateInformation(int id) {
        String sql = "UPDATE users SET AttentionLara=?, AttentionAgata=?, " +
            "AttentionFamily=?, Bank=?, station=? WHERE id=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, main.AttentionLara);
            pstmt.setInt(2, main.AttentionAgata);
            pstmt.setInt(3, main.AttentionFamily);
            pstmt.setInt(4, main.Bank);
            pstmt.setString(5, main.Station);
            pstmt.setInt(6, id);

            int affectedRows = pstmt.executeUpdate();
            boolean success = affectedRows > 0;
            Gdx.app.log("Database", "Update user " + id + ": " + (success ? "success" : "failed"));
            return success;
        } catch (SQLException e) {
            Gdx.app.error("Database", "Error updating user " + id, e);
        }
        return false;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                Gdx.app.log("Database", "Connection closed");
            }
        } catch (SQLException e) {
            Gdx.app.error("Database", "Error closing connection", e);
        }
    }
}
