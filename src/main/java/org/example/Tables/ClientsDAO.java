package org.example.Tables;

import org.example.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsDAO {

    public void addClient(Clients client) {
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

    public void deleteClient(int idClient) {
        String sql = "DELETE FROM kp.clients WHERE id_client = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idClient);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Clients getClient(int id) {
        String sql = "SELECT * FROM kp.clients WHERE id_client = ?";
        Clients ret = new Clients(0, "", "");
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            ResultSet rs = stmt.executeQuery();
            int idClient = rs.getInt("id_client");
            String nameClient = rs.getString("name_client");
            String addressClient = rs.getString("address_client");
            ret = new Clients(idClient, nameClient, addressClient);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public List<Clients> getAllClients() {
        List<Clients> clients = new ArrayList<>();
        String sql = "SELECT * FROM kp.clients";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idClient = rs.getInt("id_client");
                String nameClient = rs.getString("name_client");
                String addressClient = rs.getString("address_client");
                clients.add(new Clients(idClient, nameClient, addressClient));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }
}