package org.example;

import java.sql.*;

public class SQLTableFilling {

    public static void createTable(Connection conn, String sql) {
        /*sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "    id INT AUTO_INCREMENT PRIMARY KEY,\n"
                + "    name VARCHAR(100) NOT NULL,\n"
                + "    email VARCHAR(100) NOT NULL\n"
                + ");";*/

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица создана.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertData(Connection conn, String sql) {
        /*sql = "INSERT INTO users (name, email) VALUES ('Alice', 'alice@example.com'), ('Bob', 'bob@example.com');";*/

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Данные вставлены.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
