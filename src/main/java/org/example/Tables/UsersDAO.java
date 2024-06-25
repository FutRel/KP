package org.example.Tables;

import org.example.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    public void deleteUser(int idUser, String role) {
        if (role.equals("User")) return;
        String sql = "DELETE FROM kp.users WHERE user_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserRole(int idUser, int updatedRole, String role){
        if (role.trim().equals("User")) return;
        if (updatedRole < 1 || updatedRole > 3){
            System.out.println("Ошибка!");
            return;
        }
        String sql = "UPDATE kp.users\n" +
                "SET role_user = "+updatedRole+"\n" +
                "WHERE user_id = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserData(int idUser, String username, String passwordUser, String fullNameUser, String role){
        if (role.equals("User")) return;

        String sql = "UPDATE kp.users\n" +
                "SET username = "+username+"\n" +
                "SET password_user = "+passwordUser+"\n" +
                "SET full_name_user_user = "+fullNameUser+"\n" +
                "WHERE id_user = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUser(int idUser, String role){
        if (role.equals("User")) return "";

        String sql = "SELECT * FROM kp.users WHERE user_id = ?";
        String result = "";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String passwordUser = rs.getString("password_user");
                    String fullNameUser = rs.getString("full_name_user");
                    int roleUser = rs.getInt("role_user");
                    result = id + " " + username + " " + passwordUser + " " + fullNameUser + " " + roleUser;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<String> getAllUsers(String role) {
        List<String> users = new ArrayList<>();

        if (!role.equals("Admin")) return users;

        String sql = "SELECT * FROM kp.users";
        int idUser;
        String username = "";
        String passwordUser = "";
        String fullNameUser = "";
        int roleUser;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
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

    public void updateSelfData(String username_now, String password_now, String newUsername, String newPassword, String fullNameUser) {

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
            stmt.setString(4, username_now);
            stmt.setString(5, password_now);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}