package org.example.Tables;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.GraphicalInterface;
import java.util.List;

public class DishIngridientsView extends Application {

    static DishIngridientsDAO dishIngridientsDAO = new DishIngridientsDAO();

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

        GridPane dishIngridientsGrid = createDishIngredientsGrid();

        root.add(dishIngridientsGrid, 1, 0);
        root.add(outputTextArea, 0, 0);

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private static GridPane createDishIngredientsGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button addButton = new Button("Add Dish Ingredient");
        addButton.setOnAction(e -> openAddDishIngredientDialog());

        Button deleteButton = new Button("Delete Dish Ingredient");
        deleteButton.setOnAction(e -> openDeleteDishIngredientDialog());

        Button getOneButton = new Button("Get Dish Ingredient");
        getOneButton.setOnAction(e -> openGetDishIngredientDialog());

        Button getAllButton = new Button("Get All Dish Ingredients");
        getAllButton.setOnAction(e -> {
            List<String> dishIngridientsList = dishIngridientsDAO.getAllDishIngredients(my_role);
            dishIngridientsList.forEach(ingredient -> outputTextArea.appendText(ingredient + "\n"));
        });

        grid.add(addButton, 0, 0);
        grid.add(deleteButton, 1, 0);
        grid.add(getOneButton, 2, 0);
        grid.add(getAllButton, 3, 0);

        return grid;
    }

    private static void openAddDishIngredientDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Dish Ingredient");

        VBox dialogVBox = new VBox();
        dialogVBox.setSpacing(10);
        dialogVBox.setPadding(new Insets(20));

        TextField ingredientIdField = new TextField();
        TextField quantityField = new TextField();

        Button addConfirmButton = new Button("Add");
        addConfirmButton.setOnAction(event -> {
            int ingredientId = Integer.parseInt(ingredientIdField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            outputTextArea.appendText(
                    dishIngridientsDAO.addDishIngredient(
                            new DishIngridients(0, ingredientId, quantity), my_role));
            dialog.close();
        });

        dialogVBox.getChildren().addAll(
                new Label("Ingredient ID:"), ingredientIdField,
                new Label("Quantity:"), quantityField,
                addConfirmButton
        );

        Scene dialogScene = new Scene(dialogVBox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private static void openDeleteDishIngredientDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Delete Dish Ingredient");

        VBox dialogVBox = new VBox();
        dialogVBox.setSpacing(10);
        dialogVBox.setPadding(new Insets(20));

        TextField dishIdField = new TextField();
        TextField ingredientIdField = new TextField();

        Button deleteConfirmButton = new Button("Delete");
        deleteConfirmButton.setOnAction(event -> {
            int dishId = Integer.parseInt(dishIdField.getText());
            int ingredientId = Integer.parseInt(ingredientIdField.getText());
            outputTextArea.appendText(dishIngridientsDAO.deleteDishIngredient(dishId, ingredientId, my_role));
            dialog.close();
        });

        dialogVBox.getChildren().addAll(
                new Label("Dish ID:"), dishIdField,
                new Label("Ingredient ID:"), ingredientIdField,
                deleteConfirmButton
        );

        Scene dialogScene = new Scene(dialogVBox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private static void openGetDishIngredientDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Get Dish Ingredient");

        VBox dialogVBox = new VBox();
        dialogVBox.setSpacing(10);
        dialogVBox.setPadding(new Insets(20));

        TextField dishIdField = new TextField();

        Button getConfirmButton = new Button("Get");
        getConfirmButton.setOnAction(event -> {
            int dishId = Integer.parseInt(dishIdField.getText());
            List<String> dishIngridientsList = dishIngridientsDAO.getDishIngredient(dishId, my_role);
            dishIngridientsList.forEach(ingredient -> outputTextArea.appendText(ingredient + "\n"));
            dialog.close();
        });

        dialogVBox.getChildren().addAll(
                new Label("Dish ID:"), dishIdField,
                getConfirmButton
        );

        Scene dialogScene = new Scene(dialogVBox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

}
