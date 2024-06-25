package org.example.Tables;

import org.example.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishIngridientsDAO {

    public void addDishIngredient(DishIngridients dishIngredient, String role) {
        if (role.equals("User")) return;

        String sql = "INSERT INTO kp.dishes_ingridients (id_dish, id_ingridient, ingridient_amount) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dishIngredient.getIdDish());
            stmt.setInt(2, dishIngredient.getIdIngredient());
            stmt.setInt(3, dishIngredient.getIngredientAmount());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDishIngredient(int idDish, int idIngredient, String role) {
        if (role.equals("User")) return;

        String sql = "DELETE FROM kp.dishes_ingridients WHERE id_dish = ? AND id_ingridient = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDish);
            stmt.setInt(2, idIngredient);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getDishIngredient(int idDish, String role) {
        String sql = "SELECT * FROM kp.dishes_ingridients WHERE id_dish = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDish);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DishIngridients> getAllDishIngredients(String role) {
        List<DishIngridients> dishIngredients = new ArrayList<>();
        String sql = "SELECT * FROM kp.dishes_ingredients";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idDish = rs.getInt("id_dish");
                int idIngredient = rs.getInt("id_ingridient");
                int ingredientAmount = rs.getInt("ingridient_amount");
                dishIngredients.add(new DishIngridients(idDish, idIngredient, ingredientAmount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dishIngredients;
    }
}