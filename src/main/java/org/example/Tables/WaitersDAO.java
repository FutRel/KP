package org.example.Tables;

import org.example.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WaitersDAO {

    public String addWaiter(Waiters waiter, String role) {
        if (role.equals("User")) return "Not allowed";

        String sql = "INSERT INTO kp.waiters (name_waiter, addres_waiter, number_waiter) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, waiter.getNameWaiter());
            stmt.setString(2, waiter.getAddressWaiter());
            stmt.setString(3, waiter.getNumberWaiter());
            stmt.executeUpdate();
            return "Done";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public String updateWaiterData(int idWaiter, String nameWaiter, String addressWaiter, String numberWaiter, String role) {
        if (role.equals("User")) return "Not allowed";

        String sql = "UPDATE kp.waiters " +
                "SET name_waiter = ?, " +
                "    addres_waiter = ?, " +
                "    number_waiter = ? " +
                "WHERE id_waiter = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nameWaiter);
            stmt.setString(2, addressWaiter);
            stmt.setString(3, numberWaiter);
            stmt.setInt(4, idWaiter);

            stmt.executeUpdate();

            return "Done";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public String deleteWaiter(int idWaiter, String role) {
        if (role.equals("User")) return "Not allowed";

        String sql = "DELETE FROM kp.waiters WHERE id_waiter = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idWaiter);
            stmt.executeUpdate();
            return "Done";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public List<String> getAllWaiters(String role) {
        List<String> waiters = new ArrayList<>();
        waiters.add("Not allowed");
        if (role.equals("User")) return waiters;

        String sql = "SELECT * FROM kp.waiters";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            waiters.clear();
            while (rs.next()) {
                int idWaiter = rs.getInt("id_waiter");
                String nameWaiter = rs.getString("name_waiter");
                String addressWaiter = rs.getString("addres_waiter");
                String numberWaiter = rs.getString("number_waiter");
                Waiters w = new Waiters(idWaiter, nameWaiter, addressWaiter, numberWaiter);
                waiters.add(w.getIdWaiter() + " " + w.getNameWaiter() + " " + w.getAddressWaiter() + " " + w.getNumberWaiter());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return waiters;
    }
}