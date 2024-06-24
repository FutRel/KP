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
                createNewTable(conn);
                insertData(conn);
                System.out.println("Операции выполнены успешно.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "    id INT AUTO_INCREMENT PRIMARY KEY,\n"
                + "    name VARCHAR(100) NOT NULL,\n"
                + "    email VARCHAR(100) NOT NULL\n"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица создана.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertData(Connection conn) {
        String sql = "INSERT INTO users (name, email) VALUES ('Alice', 'alice@example.com'), ('Bob', 'bob@example.com');";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Данные вставлены.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}