package org.example;

import java.sql.*;
import java.util.*;

public class UserAccessManager {

    private static Map<String, String> users = new HashMap<>();
    private static Map<String, Integer> users_access = new HashMap<>();

    public UserAccessManager() {
        try {
            Connection conn = DataBaseConnection.getConnection();
            System.out.println("Соединение с базой данных установлено.");
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM KP.users";
            ResultSet result = statement.executeQuery(query);

            while(result.next()){
                String username = result.getString("username");
                String password_user = result.getString("password_user");
                users.put(username, password_user);
                users_access.put(username, Integer.parseInt(result.getString("role_user")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String checkAccess(String username, String password) {
        String storedHash = users.get(username);

        if (storedHash != null && storedHash.equals(password)) {
            if (users_access.get(username).equals(1)) return "User";
            else if (users_access.get(username).equals(2)) return "Manager";
            else if (users_access.get(username).equals(3)) return "Admin";
        }

        return null;
    }

    public static void updateDB(){
        try {
            Connection conn = DataBaseConnection.getConnection();
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM KP.users";
            ResultSet result = statement.executeQuery(query);

            while(result.next()){
                String username = result.getString("username");
                String password_user = result.getString("password_user");
                users.put(username, password_user);
                users_access.put(username, Integer.parseInt(result.getString("role_user")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}