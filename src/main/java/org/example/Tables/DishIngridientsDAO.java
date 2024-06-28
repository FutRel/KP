package org.example.Tables;

import org.example.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishIngridientsDAO {

    public String addDishIngredient(DishIngridients dishIngredient, String role) {
        if (role.equals("User")) return "Not allowed";

        String sql = "INSERT INTO kp.dishes_ingridients (id_dish, id_ingridient, ingridient_amount) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dishIngredient.getIdDish());
            stmt.setInt(2, dishIngredient.getIdIngredient());
            stmt.setInt(3, dishIngredient.getIngredientAmount());
            stmt.executeUpdate();
            return "Done";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public String deleteDishIngredient(int idDish, int idIngredient, String role) {
        if (role.equals("User")) return "Not allowed";

        String sql = "DELETE FROM kp.dishes_ingridients WHERE id_dish = ? AND id_ingridient = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDish);
            stmt.setInt(2, idIngredient);
            stmt.executeUpdate();
            return "Done";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public List<String> getDishIngredient(int idDish, String role) {
        List<String> dishIngredients = new ArrayList<>();
        String sql = "SELECT * FROM kp.dishes_ingridients WHERE id_dish = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDish);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idIngredient = rs.getInt("id_ingridient");
                    int ingredientAmount = rs.getInt("ingridient_amount");
                    DishIngridients di = new DishIngridients(idDish, idIngredient, ingredientAmount);
                    dishIngredients.add(di.getIdDish() + " " + di.getIdIngredient() + " " + di.getIngredientAmount());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dishIngredients;
    }

    public List<String> getAllDishIngredients(String role) {
        List<String> dishIngredients = new ArrayList<>();
        String sql = "SELECT * FROM kp.dishes_ingridients";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idDish = rs.getInt("id_dish");
                int idIngredient = rs.getInt("id_ingridient");
                int ingredientAmount = rs.getInt("ingridient_amount");
                DishIngridients d = new DishIngridients(idDish, idIngredient, ingredientAmount);
                dishIngredients.add(d.getIdIngredient() + " " + d.getIdIngredient() + " " + d.getIngredientAmount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dishIngredients;
    }
}