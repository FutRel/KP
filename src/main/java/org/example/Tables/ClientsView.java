package org.example.Tables;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.GraphicalInterface;
import java.util.*;

public class ClientsView extends Application {

    static ClientsDAO clientsDAO = new ClientsDAO();

    private static TextArea outputTextArea;

    static String my_role;
    static String my_login;
    static String my_password;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Client Management");

        my_role = GraphicalInterface.getRole();
        my_login = GraphicalInterface.getLogin();
        my_password = GraphicalInterface.getPasswordLogin();

        outputTextArea = new TextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setPrefSize(1000, 1000);

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        GridPane clientsGrid = createClientsGrid();

        root.add(clientsGrid, 1, 0);
        root.add(outputTextArea, 0, 0);

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private static GridPane createClientsGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        Button addButton = new Button("Add Client");
        addButton.setOnAction(e -> {
            openAddClientDialog();
        });

        Button deleteButton = new Button("Delete Client");
        deleteButton.setOnAction(e -> {
            openDeleteClientDialog();
        });

        Button getOneButton = new Button("Get Client");
        getOneButton.setOnAction(e -> {
            openGetClientDialog();
        });

        Button getAllButton = new Button("Get All Clients");
        getAllButton.setOnAction(e -> {
            List<String> clients = clientsDAO.getAllClients(my_role);
            clients.forEach(c -> outputTextArea.appendText(c + "\n"));
        });

        grid.add(addButton, 0, 0);
        grid.add(deleteButton, 1, 0);
        grid.add(getOneButton, 2, 0);
        grid.add(getAllButton, 3, 0);

        return grid;
    }

    private static void openAddClientDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Client");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        TextField nameField = new TextField();
        TextField addressField = new TextField();

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Address:"), 0, 1);
        grid.add(addressField, 1, 1);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            outputTextArea.setText(clientsDAO.addClient(new Clients(0, name, address), my_role));
            dialog.close();
        });

        grid.add(addButton, 1, 2);

        Scene dialogScene = new Scene(grid, 300, 150);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private static void openDeleteClientDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Delete Client");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        TextField idField = new TextField();

        grid.add(new Label("Client ID:"), 0, 0);
        grid.add(idField, 1, 0);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            int clientId = Integer.parseInt(idField.getText());
            outputTextArea.setText(
                    clientsDAO.deleteClient(new Clients(clientId, "", ""), my_role));
        });

        grid.add(deleteButton, 1, 1);

        Scene dialogScene = new Scene(grid, 300, 100);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private static void openGetClientDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Get Client");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        TextField idField = new TextField();

        grid.add(new Label("Client ID:"), 0, 0);
        grid.add(idField, 1, 0);

        Button getButton = new Button("Get");
        getButton.setOnAction(e -> {
            int clientId = Integer.parseInt(idField.getText());
            outputTextArea.setText(clientsDAO.getClient(new Clients(clientId, " ", " "), my_role));
        });

        grid.add(getButton, 1, 1);

        Scene dialogScene = new Scene(grid, 300, 100);
        dialog.setScene(dialogScene);
        dialog.show();
    }

}