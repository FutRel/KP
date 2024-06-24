package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class GraphicalInterface extends Application {
    private final UserAccessManager accessManager = new UserAccessManager();

    String role = "";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kp";
    private static final String USER = "root";
    private static final String PASS = "SQL1234";

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Stage stage = new Stage();
        stage.setTitle("Restaurant");
        stage.setFullScreen(true);
        stage.show();


        //user access and registration step
        primaryStage.setTitle("User Access Application");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Enter your username");
        GridPane.setConstraints(usernameInput, 0, 0);

        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Enter your password");
        GridPane.setConstraints(passwordInput, 0, 1);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 0, 2);

        Button registrationButton = new Button("Registration");
        GridPane.setConstraints(registrationButton, 1, 2);


        loginButton.setOnAction(e -> {
            String username = usernameInput.getText().trim();
            String password = passwordInput.getText();

            role = accessManager.checkAccess(username, password);

            if (role != null) {
                System.out.println("Logged in as: " + role);
                primaryStage.close();
            }
            else System.out.println("Login failed. Please check your credentials.");
        });

        registrationButton.setOnAction(e -> {
            Stage reg = new Stage();
            reg.setTitle("User Registration Application");
            GridPane grid2 = new GridPane();
            grid2.setVgap(10);
            grid2.setHgap(10);
            grid2.setPadding(new Insets(20, 20, 20, 20));

            TextField usernameInput2 = new TextField();
            usernameInput2.setPromptText("Enter your username");
            GridPane.setConstraints(usernameInput2, 0, 0);

            PasswordField passwordInput2 = new PasswordField();
            passwordInput2.setPromptText("Enter your password");
            GridPane.setConstraints(passwordInput2, 0, 1);

            TextField fullnameInput = new TextField();
            fullnameInput.setPromptText("Enter your full name");
            GridPane.setConstraints(fullnameInput, 0, 2);

            Button registrationButton2 = new Button("Registration");
            GridPane.setConstraints(registrationButton2, 1, 2);

            grid2.getChildren().addAll(usernameInput2, passwordInput2,
                    fullnameInput, registrationButton2);
            Scene regSc = new Scene(grid2, 300, 200);
            reg.setScene(regSc);
            reg.show();

            registrationButton2.setOnAction(r -> {

                String usernameReg = usernameInput2.getText().trim();
                String passwordReg = passwordInput2.getText().trim();
                String fullnameReg = fullnameInput.getText().trim();

                String query = "INSERT INTO kp.users (username, password_user, full_name_user, role_user) VALUES" +
                        " ('"+usernameReg+"', '"+passwordReg+"', '"+fullnameReg+"', 1);";
                try {
                    insertData(query);
                    System.out.println("Successfully");
                    UserAccessManager.updateDB();
                    reg.close();
                } catch (Exception er) {
                    System.out.println("Error!");
                }
            });
        });

        grid.getChildren().addAll(usernameInput, passwordInput, loginButton, registrationButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void insertData(String sql) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}