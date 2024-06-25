package org.example.Tables;

import org.example.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishesDAO {

    public void addDish(Dishes dish, String role) {
        if (role.equals("User")) return;

        String sql = "INSERT INTO kp.dishes (name_dish) VALUES (?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dish.getNameDish());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDish(int idDish, String role) {
        if (role.equals("User")) return;

        String sql = "DELETE FROM kp.dishes WHERE id_dish = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDish);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getDish(int idDish, String role) {
        String sql = "SELECT * FROM dishes WHERE id_dish = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDish);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id_dish");
                    String name = rs.getString("name_dish");
                    return id + " " + name;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<String> getAllDishes(String role) {
        List<String> dishes = new ArrayList<>();
        String sql = "SELECT * FROM kp.dishes";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idDish = rs.getInt("id_dish");
                String nameDish = rs.getString("name_dish");
                Dishes ds = new Dishes(idDish, nameDish);
                dishes.add(ds.getIdDish() + " " + ds.getNameDish());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dishes;
    }
}