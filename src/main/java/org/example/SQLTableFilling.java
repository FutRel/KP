package org.example;

import java.sql.*;

public class SQLTableFilling {

    public static void createTable(String sql) {
        try {
            Connection conn = DataBaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Таблица создана.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertData(String sql) {
        try{
            Connection conn = DataBaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Данные вставлены.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
