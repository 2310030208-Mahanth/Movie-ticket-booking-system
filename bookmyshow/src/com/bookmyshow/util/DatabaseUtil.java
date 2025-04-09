package com.bookmyshow.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DatabaseUtil {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try {
            // Load database configuration from properties file
            Properties props = new Properties();
            try (InputStream input = DatabaseUtil.class.getClassLoader()
                    .getResourceAsStream("db.properties")) {
                if (input == null) {
                    throw new RuntimeException("db.properties not found");
                }
                props.load(input);
            }

            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("MySQL JDBC Driver not found");
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load db.properties");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try { conn.close(); } catch (SQLException e) { /* ignored */ }
        }
    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try { stmt.close(); } catch (SQLException e) { /* ignored */ }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try { rs.close(); } catch (SQLException e) { /* ignored */ }
        }
    }

    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(conn);
    }
}
