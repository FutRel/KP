package org.example.Tables;

import org.example.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TablesDAO {
    public void updateTableEmploy(int idTable, boolean employBool, String role){
        if (role.equals("User")) return;
        int employ = employBool ? 1 : 0;
        String sql = "UPDATE kp.tables\n" +
                "SET employ_table = "+employ+"\n" +
                "WHERE id_table = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTable);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllTables(String role) {
        List<String> tables = new ArrayList<>();
        String sql = "SELECT * FROM kp.tables";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idTable = rs.getInt("id_table");
                int capacityTable = rs.getInt("capacity_table");
                int employTable = rs.getInt("employ_table");
                Tables t = new Tables(idTable, capacityTable, employTable);
                tables.add(t.getIdTable() + " " + t.getCapacityTable());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tables;
    }
}