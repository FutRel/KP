package org.example.Tables;

import org.example.DataBaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO {

    public String addOrder(Orders order, String role) {
        if (role.equals("User")) return "Not allowed";

        String sql = "INSERT INTO kp.orders (date_order, time_start_order," +
                " time_end_order, waiter_order, table_order, client_order) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, order.getDateOrder());
            stmt.setTime(2, order.getTimeStartOrder());
            stmt.setTime(3, order.getTimeEndOrder());
            stmt.setInt(4, order.getWaiterOrder());
            stmt.setInt(5, order.getTableOrder());
            stmt.setInt(6, order.getClientOrder());
            stmt.executeUpdate();
            return "Done";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public List<String> getAllOrders(String role) {
        List<String> orders = new ArrayList<>();
        orders.add("Not allowed");
        if (!role.equals("Admin")) return orders;

        String sql = "SELECT * FROM kp.orders";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            orders.clear();
            while (rs.next()) {
                int idOrder = rs.getInt("id_order");
                Date dateOrder = rs.getDate("date_order");
                Time timeStartOrder = rs.getTime("time_start_order");
                Time timeEndOrder = rs.getTime("time_end_order");
                int waiterOrder = rs.getInt("waiter_order");
                int tableOrder = rs.getInt("table_order");
                int clientOrder = rs.getInt("client_order");
                Orders o = new Orders(idOrder, dateOrder, timeStartOrder, timeEndOrder, waiterOrder, tableOrder, clientOrder);
                orders.add(o.getIdOrder() + " " + o.getDateOrder().toString() + " "
                        + o.getTimeStartOrder().getTime() + " " + o.getTimeEndOrder().getTime() + " "
                        + o.getWaiterOrder() + " " + o.getTableOrder() + " " + o.getClientOrder());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}