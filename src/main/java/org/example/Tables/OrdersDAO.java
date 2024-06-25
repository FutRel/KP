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

    public void addOrder(Orders order) {
        String sql = "INSERT INTO kp.orders (date_order, time_start_order, time_end_order, waiter_order, table_order, client_order) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, order.getDateOrder());
            stmt.setTime(2, order.getTimeStartOrder());
            stmt.setTime(3, order.getTimeEndOrder());
            stmt.setInt(4, order.getWaiterOrder());
            stmt.setInt(5, order.getTableOrder());
            stmt.setInt(6, order.getClientOrder());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Orders deleteOrder(int idOrder) {
        Orders res = new Orders(0, new Date(0), new Time(0), new Time(0), 0, 0, 0);
        String sql = "SELECT * FROM kp.orders WHERE id_order = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idOrder);
            stmt.executeUpdate();
            ResultSet rs = stmt.executeQuery();
            Date dateOrder = rs.getDate("date_order");
            Time timeStartOrder = rs.getTime("time_start_order");
            Time timeEndOrder = rs.getTime("time_end_order");
            int waiterOrder = rs.getInt("waiter_order");
            int tableOrder = rs.getInt("table_order");
            int clientOrder = rs.getInt("client_order");

            res = new Orders(idOrder, dateOrder, timeStartOrder, timeEndOrder, waiterOrder, tableOrder, clientOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Orders> getAllOrders() {
        List<Orders> orders = new ArrayList<>();
        String sql = "SELECT * FROM kp.orders";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idOrder = rs.getInt("id_order");
                Date dateOrder = rs.getDate("date_order");
                Time timeStartOrder = rs.getTime("time_start_order");
                Time timeEndOrder = rs.getTime("time_end_order");
                int waiterOrder = rs.getInt("waiter_order");
                int tableOrder = rs.getInt("table_order");
                int clientOrder = rs.getInt("client_order");
                orders.add(new Orders(idOrder, dateOrder, timeStartOrder, timeEndOrder, waiterOrder, tableOrder, clientOrder));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}