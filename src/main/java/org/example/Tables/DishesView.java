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

import java.util.List;

public class DishesView extends Application {

    static DishesDAO dishesDAO = new DishesDAO();

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

        GridPane dishesGrid = createDishesGrid();

        root.add(dishesGrid, 1, 0);
        root.add(outputTextArea, 0, 0);

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private static GridPane createDishesGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button addButton = new Button("Add Dish");
        addButton.setOnAction(e -> {
            openAddDishDialog();
        });
        Button deleteButton = new Button("Delete Dish");
        deleteButton.setOnAction(e -> {
            openDeleteDishDialog();
        });
        Button getOneButton = new Button("Get Dish");
        getOneButton.setOnAction(e -> {
            openGetDishDialog();
        });
        Button getButton = new Button("Get Dishes");
        getButton.setOnAction(e -> {
            List<String> dishes = dishesDAO.getAllDishes(my_role);
            dishes.forEach(c -> outputTextArea.appendText(c + "\n"));
        });

        grid.add(addButton, 0, 0);
        grid.add(deleteButton, 1, 0);
        grid.add(getOneButton, 2, 0);
        grid.add(getButton, 3, 0);

        return grid;
    }

    private static void openAddDishDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Dish");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        TextField nameField = new TextField();

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            outputTextArea.setText(dishesDAO.addDish(new Dishes(0, name), my_role));
            dialog.close();
        });

        grid.add(addButton, 1, 2);

        Scene dialogScene = new Scene(grid, 300, 150);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private static void openDeleteDishDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Delete Dish");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        TextField idField = new TextField();

        grid.add(new Label("Dish ID:"), 0, 0);
        grid.add(idField, 1, 0);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            int dishId = Integer.parseInt(idField.getText());
            outputTextArea.setText(dishesDAO.deleteDish(new Dishes(dishId, ""), my_role));
            dialog.close();
        });

        grid.add(deleteButton, 1, 1);

        Scene dialogScene = new Scene(grid, 300, 100);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private static void openGetDishDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Get Dish");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        TextField idField = new TextField();

        grid.add(new Label("Dish ID:"), 0, 0);
        grid.add(idField, 1, 0);

        Button getButton = new Button("Get");
        getButton.setOnAction(e -> {
            int dishId = Integer.parseInt(idField.getText());
            outputTextArea.appendText(dishesDAO.getDish(new Dishes(dishId, ""), my_role));
            dialog.close();
        });

        grid.add(getButton, 1, 1);

        Scene dialogScene = new Scene(grid, 300, 100);
        dialog.setScene(dialogScene);
        dialog.show();
    }

}
