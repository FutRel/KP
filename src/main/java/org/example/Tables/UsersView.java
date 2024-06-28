package org.example.Tables;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.GraphicalInterface;
import java.util.List;

public class UsersView extends Application {

    static UsersDAO usersDAO = new UsersDAO();

    private static TextArea outputTextArea;

    static String my_role;
    static String my_login;
    static String my_password;

    @Override
    public void start(Stage stage) {
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

        GridPane usersGrid = createUsersGrid();

        root.add(usersGrid, 1, 0);
        root.add(outputTextArea, 0, 0);

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private static GridPane createUsersGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button deleteButton = new Button("Delete User");
        Button updateRoleButton = new Button("Update User Role");
        Button updateDataButton = new Button("Update User Data");
        Button getButton = new Button("Get User");
        Button getAllButton = new Button("Get All Users");
        Button updateSelfDataButton = new Button("Update Self Data");

        deleteButton.setOnAction(e -> openDeleteUserDialog());
        updateRoleButton.setOnAction(e -> openUpdateUserRoleDialog());
        updateDataButton.setOnAction(e -> openUpdateUserDataDialog());
        getButton.setOnAction(e -> openGetUserDialog());
        getAllButton.setOnAction(e -> {
            List<String> users = usersDAO.getAllUsers(my_role);
            users.forEach(c -> outputTextArea.appendText(c + "\n"));
        });
        updateSelfDataButton.setOnAction(e -> openUpdateSelfDataDialog());

        grid.add(deleteButton, 0, 0);
        grid.add(updateRoleButton, 1, 0);
        grid.add(updateDataButton, 2, 0);
        grid.add(getButton, 3, 0);
        grid.add(getAllButton, 4, 0);
        grid.add(updateSelfDataButton, 5, 0);

        return grid;
    }

    private static void openDeleteUserDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Delete User");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        TextField idField = new TextField();
        idField.setPromptText("User ID");

        Button submitButton = new Button("Delete");
        submitButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            outputTextArea.appendText(
                    usersDAO.deleteUser(
                            new Users(id, "", "", "", ""), my_role));
            dialog.close();
        });

        vbox.getChildren().addAll(idField, submitButton);

        Scene dialogScene = new Scene(vbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private static void openUpdateUserRoleDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Update User Role");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        TextField idField = new TextField();
        idField.setPromptText("User ID");
        TextField roleField = new TextField();
        roleField.setPromptText("New Role");

        Button submitButton = new Button("Update");
        submitButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            int role = Integer.parseInt(roleField.getText());
            outputTextArea.setText(usersDAO.updateUserRole(
                    new Users(id, "", "", "", ""), role, my_role));
            dialog.close();
        });

        vbox.getChildren().addAll(idField, roleField, submitButton);

        Scene dialogScene = new Scene(vbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private static void openUpdateUserDataDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Update User Data");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        TextField idField = new TextField();
        idField.setPromptText("User ID");
        TextField usernameField = new TextField();
        usernameField.setPromptText("New Username");
        TextField passwordField = new TextField();
        passwordField.setPromptText("New Password");
        TextField fullNameField = new TextField();
        fullNameField.setPromptText("New Full Name");

        Button submitButton = new Button("Update");
        submitButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            String username = usernameField.getText();
            String password = passwordField.getText();
            String fullName = fullNameField.getText();
            outputTextArea.setText(
                    usersDAO.updateUserData(
                            new Users(id, "", "", "", ""),
                            username, password, fullName, my_role));
            dialog.close();
        });

        vbox.getChildren().addAll(idField, usernameField, passwordField, fullNameField, submitButton);

        Scene dialogScene = new Scene(vbox, 300, 300);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private static void openGetUserDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Get User");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        TextField idField = new TextField();
        idField.setPromptText("User ID");

        Button submitButton = new Button("Get");
        submitButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            outputTextArea.appendText(
                    usersDAO.getUser(new Users(id, "", "", "", ""), my_role));
            dialog.close();
        });

        vbox.getChildren().addAll(idField, submitButton);

        Scene dialogScene = new Scene(vbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private static void openUpdateSelfDataDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Update Self Data");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        TextField usernameField = new TextField();
        usernameField.setPromptText("New Username");
        TextField passwordField = new TextField();
        passwordField.setPromptText("New Password");
        TextField fullNameField = new TextField();
        fullNameField.setPromptText("New Full Name");

        Button submitButton = new Button("Update");
        submitButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String fullName = fullNameField.getText();
            outputTextArea.setText(usersDAO.updateSelfData(
                    new Users(0, my_login, my_password, "", my_role), username, password, fullName));
            dialog.close();
        });

        vbox.getChildren().addAll(usernameField, passwordField, fullNameField, submitButton);

        Scene dialogScene = new Scene(vbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }
}
