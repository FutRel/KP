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
        String sql = "DELETE FROM kp.users WHERE id_user = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserRole(int idUser, int updatedRole, String role){
        if (role.equals("User")) return;
        if (updatedRole < 1 || updatedRole > 3){
            System.out.println("Ошибка!");
            return;
        }
        String sql = "UPDATE kp.users\n" +
                "SET role_user = "+updatedRole+"\n" +
                "WHERE id_user = ?";

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
        String sql = "SELECT * FROM kp.users WHERE id_user = ?";

        int id = 0;
        String username = "";
        String passwordUser = "";
        String fullNameUser = "";
        int roleUser = 0;
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            stmt.executeUpdate();
            ResultSet rs = stmt.executeQuery();
            id = rs.getInt("id_user");
            username = rs.getString("username");
            passwordUser = rs.getString("password_user");
            fullNameUser = rs.getString("full_name_user");
            roleUser = rs.getInt("role_user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id + " " + username + " " + passwordUser + " " + fullNameUser + " " + roleUser;
    }

    public List<String> getAllUsers(String role) {

        List<String> users = new ArrayList<>();

        if (!role.equals("Admin")) return users;

        String sql = "SELECT * FROM kp.users";
        int idUser = 0;
        String username = "";
        String passwordUser = "";
        String fullNameUser = "";
        int roleUser = 0;

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

    public void updateSelfData(String username_now, String password_now, String username, String passwordUser, String fullNameUser){

        String sql = "UPDATE kp.users\n" +
                "SET username = "+username+"\n" +
                "SET password_user = "+passwordUser+"\n" +
                "SET full_name_user_user = "+fullNameUser+"\n" +
                "WHERE username = ? AND password_user = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username_now);
            stmt.setString(2, password_now);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}