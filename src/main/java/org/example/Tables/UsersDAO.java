package org.example.Tables;

import org.example.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    public String deleteUser(Users user, String role) {
        if (role.equals("User")) return "Not allowed";
        String sql = "DELETE FROM kp.users WHERE user_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getIdUser());
            stmt.executeUpdate();
            return "Done";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public String updateUserRole(Users user, int updatedRole, String role){
        if (role.trim().equals("User")) return "Not allowed";
        if (updatedRole < 1 || updatedRole > 3){
            System.out.println("Ошибка!");
            return "Role error";
        }
        String sql = "UPDATE kp.users\n" +
                "SET role_user = "+updatedRole+"\n" +
                "WHERE user_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getIdUser());
            stmt.executeUpdate();
            return "Done";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public String updateUserData(Users user, String username, String passwordUser, String fullNameUser, String role){
        if (role.equals("User")) return "Not allowed";

        String sql = "UPDATE kp.users\n" +
                "SET username = "+username+"\n" +
                "SET password_user = "+passwordUser+"\n" +
                "SET full_name_user_user = "+fullNameUser+"\n" +
                "WHERE id_user = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getIdUser());
            stmt.executeUpdate();
            return "Done";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public String getUser(Users user, String role){
        if (role.equals("User")) return "Not allowed";

        String sql = "SELECT * FROM kp.users WHERE user_id = ?";
        String result = "";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getIdUser());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String passwordUser = rs.getString("password_user");
                    String fullNameUser = rs.getString("full_name_user");
                    int roleUser = rs.getInt("role_user");
                    result = id + " " + username + " " + passwordUser + " " + fullNameUser + " " + roleUser;
                }
                return result;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public List<String> getAllUsers(String role) {
        List<String> users = new ArrayList<>();
        users.add("Not allowed");

        if (!role.equals("Admin")) return users;

        String sql = "SELECT * FROM kp.users";
        int idUser;
        String username;
        String passwordUser;
        String fullNameUser;
        int roleUser;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            users.clear();
            while (rs.next()) {
                idUser = rs.getInt("id_user");
                username = rs.getString("username");
                passwordUser = rs.getString("password_user");
                fullNameUser = rs.getString("full_name_user");
                roleUser = rs.getInt("role_user");
                users.add(idUser + " " + username + " " + passwordUser + " " + fullNameUser + " " + roleUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public String updateSelfData(Users me, String newUsername, String newPassword, String fullNameUser) {

        String sql = "UPDATE kp.users " +
                "SET username = ?, " +
                "    password_user = ?, " +
                "    full_name_user = ? " +
                "WHERE username = ? AND password_user = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newUsername);
            stmt.setString(2, newPassword);
            stmt.setString(3, fullNameUser);
            stmt.setString(4, me.getUsername());
            stmt.setString(5, me.getPasswordUser());

            stmt.executeUpdate();
            return "Done";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }
}