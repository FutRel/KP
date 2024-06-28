package org.example.Tables;

import org.example.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDishesDAO {

    public String addOrderDish(OrderDishes orderDish, String role) {
        if (role.equals("User")) return "Not allowed";

        String sql = "INSERT INTO kp.order_dishes (id_order, dish_order, dish_amount_order) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderDish.getIdOrder());
            stmt.setInt(2, orderDish.getDishOrder());
            stmt.setInt(3, orderDish.getDishAmountOrder());
            stmt.executeUpdate();
            return "Done";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public String deleteOrderDish(int idOrder, int dishOrder, String role) {
        if (role.equals("User")) return "Not allowed";

        String sql = "DELETE FROM kp.order_dishes WHERE id_order = ? AND dish_order = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idOrder);
            stmt.setInt(2, dishOrder);
            stmt.executeUpdate();
            return "Done";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public List<String> getOrderDishes(int idOrder, String role) {
        List<String> orderDishes = new ArrayList<>();
        orderDishes.add("Not allowed");
        if (role.equals("User")) return orderDishes;

        String sql = "SELECT * FROM kp.order_dishes WHERE id_order = "+idOrder;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);){
            orderDishes.clear();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int dishOrder = rs.getInt("dish_order");
                int dishAmountOrder = rs.getInt("dish_amount_order");
                OrderDishes o = new OrderDishes(idOrder, dishOrder, dishAmountOrder);
                orderDishes.add(o.getIdOrder() + " " + o.getDishOrder() + " " + o.getDishAmountOrder());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDishes;
    }

    public List<String> getAllOrderDishes(String role) {
        List<String> orderDishes = new ArrayList<>();
        orderDishes.add("Not allowed");
        if (!role.equals("Admin")) return orderDishes;

        String sql = "SELECT * FROM kp.order_dishes";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            orderDishes.clear();
            while (rs.next()) {
                int idOrder = rs.getInt("id_order");
                int dishOrder = rs.getInt("dish_order");
                int dishAmountOrder = rs.getInt("dish_amount_order");
                OrderDishes o = new OrderDishes(idOrder, dishOrder, dishAmountOrder);
                orderDishes.add(o.getIdOrder() + " " + o.getDishOrder() + " " + o.getDishAmountOrder());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDishes;
    }
}