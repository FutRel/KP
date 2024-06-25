package org.example.Tables;

import org.example.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDishesDAO {

    public void addOrderDish(OrderDishes orderDish) {
        String sql = "INSERT INTO kp.order_dishes (id_order, dish_order, dish_amount_order) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderDish.getIdOrder());
            stmt.setInt(2, orderDish.getDishOrder());
            stmt.setInt(3, orderDish.getDishAmountOrder());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrderDish(int idOrder, int dishOrder) {
        String sql = "DELETE FROM kp.order_dishes WHERE id_order = ? AND dish_order = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idOrder);
            stmt.setInt(2, dishOrder);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderDishes> getOrderDishes(int idOrder) {
        List<OrderDishes> orderDishes = new ArrayList<>();
        String sql = "SELECT * FROM kp.order_dishes WHERE id_order = ? AND dish_order = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int dishOrder = rs.getInt("dish_order");
                int dishAmountOrder = rs.getInt("dish_amount_order");
                orderDishes.add(new OrderDishes(idOrder, dishOrder, dishAmountOrder));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDishes;
    }

    public List<OrderDishes> getAllOrderDishes() {
        List<OrderDishes> orderDishes = new ArrayList<>();
        String sql = "SELECT * FROM kp.order_dishes";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idOrder = rs.getInt("id_order");
                int dishOrder = rs.getInt("dish_order");
                int dishAmountOrder = rs.getInt("dish_amount_order");
                orderDishes.add(new OrderDishes(idOrder, dishOrder, dishAmountOrder));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDishes;
    }
}