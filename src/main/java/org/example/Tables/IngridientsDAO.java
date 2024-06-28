package org.example.Tables;

import org.example.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngridientsDAO {

    public String addIngredient(Ingridients ingridient, double ingridientAddedAmount, String role) {
        if (role.equals("User")) return "Not allowed";
        String sql = "UPDATE kp.ingridients\n" +
                "SET ingridient_amount = "+ingridientAddedAmount+" + ingridient_amount\n" +
                "WHERE id_ingridient = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ingridient.getIdIngredient());
            stmt.executeUpdate();
            return "Done";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public String addNewIngredient(Ingridients ingridient, String role) {
        if (role.equals("User")) return "Not allowed";
        String sql = "INSERT INTO kp.ingridients (name_ingridient, ingridient_measure, ingridient_amount) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ingridient.getIngredientName());
            stmt.setString(2, ingridient.getIngredientMeasure());
            stmt.setDouble(3, ingridient.getIngredientAmount());
            stmt.executeUpdate();
            return "Done";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public List<String> getAllIngredients(String role) {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Not allowed");
        if (role.equals("User")) return ingredients;

        String sql = "SELECT * FROM kp.ingridients";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ingredients.clear();
            while (rs.next()) {
                int idIngredient = rs.getInt("id_ingridient");
                String ingredientName = rs.getString("name_ingridient");
                String ingredientMeasure = rs.getString("ingridient_measure");
                double ingredientAmount = rs.getDouble("ingridient_amount");
                Ingridients i = new Ingridients(idIngredient, ingredientName, ingredientMeasure, ingredientAmount);
                ingredients.add(i.getIdIngredient() + " " + i.getIngredientName() +
                        " " + i.getIngredientAmount() + " " + i.getIngredientMeasure());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredients;
    }
}