package org.example.Tables;

import org.example.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsDAO {

    public void addClient(Clients client, String role) {
        if (role.equals("User")) return;

        String sql = "INSERT INTO kp.clients (name_client, address_client) VALUES (?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getNameClient());
            stmt.setString(2, client.getAddressClient());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(int idClient, String role) {
        if (role.equals("User")) return;

        String sql = "DELETE FROM kp.clients WHERE id_client = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idClient);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getClient(int id, String role) {
        String ret = "";
        if (role.equals("User")) return ret;

        String sql = "SELECT * FROM kp.clients WHERE id_client = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idClient = rs.getInt("id_client");
                    String nameClient = rs.getString("name_client");
                    String addressClient = rs.getString("address_client");
                    Clients cl = new Clients(idClient, nameClient, addressClient);
                    ret = cl.getIdClient() + " " + cl.getNameClient() + " " + cl.getAddressClient();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public List<String> getAllClients(String role) {
        List<String> clients = new ArrayList<>();
        if (role.equals("User")) return clients;

        String sql = "SELECT * FROM kp.clients";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idClient = rs.getInt("id_client");
                String nameClient = rs.getString("name_client");
                String addressClient = rs.getString("address_client");
                Clients cl = new Clients(idClient, nameClient, addressClient);
                clients.add(cl.getIdClient() + " " + cl.getNameClient() + " " + cl.getAddressClient());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }
}