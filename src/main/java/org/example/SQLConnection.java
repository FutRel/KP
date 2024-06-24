package org.example;

import java.sql.*;

public class SQLConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kp";
    private static final String USER = "root";
    private static final String PASS = "SQL1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                System.out.println("Соединение с базой данных установлено.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}