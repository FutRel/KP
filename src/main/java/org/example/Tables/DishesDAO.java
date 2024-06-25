package org.example.Tables;

import org.example.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishesDAO {

    public void addDish(Dishes dish) {
        String sql = "INSERT INTO kp.dishes (name_dish) VALUES (?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dish.getNameDish());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDish(int idDish) {
        String sql = "DELETE FROM kp.dishes WHERE id_dish = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDish);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getDish(int idDish) {
        String sql = "SELECT * FROM dishes WHERE id_dish = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDish);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Dishes> getAllDishes() {
        List<Dishes> dishes = new ArrayList<>();
        String sql = "SELECT * FROM kp.dishes";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idDish = rs.getInt("id_dish");
                String nameDish = rs.getString("name_dish");
                dishes.add(new Dishes(idDish, nameDish));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dishes;
    }
}