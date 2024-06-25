package org.example.Tables;

import org.example.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WaitersDAO {

    public void addWaiter(Waiters waiter, String role) {
        if (role.equals("User")) return;

        String sql = "INSERT INTO kp.waiters (name_waiter, addres_waiter, number_waiter) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, waiter.getNameWaiter());
            stmt.setString(2, waiter.getAddressWaiter());
            stmt.setString(3, waiter.getNumberWaiter());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateWaiterData(int idWaiter, String nameWaiter, String addresWaiter, String numberWaiter, String role){
        if (role.equals("User")) return;

        String sql = "UPDATE kp.waiters\n" +
                "SET name_waiter = "+nameWaiter+"\n" +
                "SET addres_waiter = "+addresWaiter+"\n" +
                "SET number_waiter = "+numberWaiter+"\n" +
                "WHERE id_waiter = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idWaiter);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWaiter(int idWaiter, String role) {
        if (role.equals("User")) return;

        String sql = "DELETE FROM kp.waiters WHERE id_waiter = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idWaiter);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Waiters> getAllWaiters(String role) {
        List<Waiters> waiters = new ArrayList<>();
        if (role.equals("User")) return waiters;

        String sql = "SELECT * FROM kp.waiters";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idWaiter = rs.getInt("id_waiter");
                String nameWaiter = rs.getString("name_waiter");
                String addressWaiter = rs.getString("address_waiter");
                String numberWaiter = rs.getString("number_waiter");
                waiters.add(new Waiters(idWaiter, nameWaiter, addressWaiter, numberWaiter));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return waiters;
    }
}