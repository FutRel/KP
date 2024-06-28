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

public class WaitersView extends Application {

    static WaitersDAO waitersDAO = new WaitersDAO();

    private static TextArea outputTextArea;

    static String my_role;
    static String my_login;
    static String my_password;

    @Override
    public void start(Stage stage){
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

        GridPane waitersGrid = createWaitersGrid();

        root.add(waitersGrid, 1, 0);
        root.add(outputTextArea, 0, 0);

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private static GridPane createWaitersGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button addButton = new Button("Add Waiter");
        Button deleteButton = new Button("Delete Waiter");
        Button updateButton = new Button("Update Data Waiter");
        Button getButton = new Button("Get All Waiters");

        addButton.setOnAction(e -> openAddWaiterDialog());
        deleteButton.setOnAction(e -> openDeleteWaiterDialog());
        updateButton.setOnAction(e -> openUpdateWaiterDataDialog());
        getButton.setOnAction(e -> {
            List<String> waiters = waitersDAO.getAllWaiters(my_role);
            waiters.forEach(w -> outputTextArea.appendText(w + "\n"));
        });

        grid.add(addButton, 0, 0);
        grid.add(deleteButton, 1, 0);
        grid.add(updateButton, 2, 0);
        grid.add(getButton, 3, 0);

        return grid;
    }

    private static void openAddWaiterDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Add Waiter");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");

        Button submitButton = new Button("Add");
        submitButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String phoneNumber = phoneNumberField.getText();
            outputTextArea.setText(
                    waitersDAO.addWaiter(new Waiters(0, firstName, lastName, phoneNumber), my_role));
            dialog.close();
        });

        vbox.getChildren().addAll(firstNameField, lastNameField, phoneNumberField, submitButton);

        Scene dialogScene = new Scene(vbox, 300, 300);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private static void openDeleteWaiterDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Delete Waiter");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        TextField idField = new TextField();
        idField.setPromptText("Waiter ID");

        Button submitButton = new Button("Delete");
        submitButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            outputTextArea.setText(waitersDAO.deleteWaiter(id, my_role));
            dialog.close();
        });

        vbox.getChildren().addAll(idField, submitButton);

        Scene dialogScene = new Scene(vbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private static void openUpdateWaiterDataDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Update Data Waiter");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        TextField idField = new TextField();
        idField.setPromptText("Waiter ID");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");

        Button submitButton = new Button("Update");
        submitButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String phoneNumber = phoneNumberField.getText();
            outputTextArea.setText(waitersDAO.updateWaiterData(id, firstName, lastName, phoneNumber, my_role));
            dialog.close();
        });

        vbox.getChildren().addAll(idField, firstNameField, lastNameField, phoneNumberField, submitButton);

        Scene dialogScene = new Scene(vbox, 300, 300);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }
}
