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

public class IngridientsView extends Application {

    static IngridientsDAO ingridientsDAO = new IngridientsDAO();

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

        GridPane ingridientGrid = createIngredientsGrid();

        root.add(ingridientGrid, 1, 0);
        root.add(outputTextArea, 0, 0);

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private static GridPane createIngredientsGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button addButton = new Button("Add Ingredient");
        addButton.setOnAction(e -> openAddIngredientDialog());

        Button addNewButton = new Button("Add New Ingredient");
        addNewButton.setOnAction(e -> openAddNewIngredientDialog());

        Button getButton = new Button("Get All Ingredients");
        getButton.setOnAction(e -> {
            List<String> ingredientsList = ingridientsDAO.getAllIngredients(my_role);
            ingredientsList.forEach(ingredient -> outputTextArea.appendText(ingredient + "\n"));
        });

        grid.add(addButton, 0, 0);
        grid.add(addNewButton, 1, 0);
        grid.add(getButton, 2, 0);

        return grid;
    }

    private static void openAddIngredientDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Ingredient");

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
                    ingridientsDAO.addIngredient(
                            new Ingridients(ingredientId, "", "", 0),
                            quantity, my_role));
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

    private static void openAddNewIngredientDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add New Ingredient");

        VBox dialogVBox = new VBox();
        dialogVBox.setSpacing(10);
        dialogVBox.setPadding(new Insets(20));

        TextField measureField = new TextField();
        TextField amountField = new TextField();
        TextField quantityField = new TextField();

        Button addNewConfirmButton = new Button("Add New");
        addNewConfirmButton.setOnAction(event -> {
            String measure = measureField.getText();
            String amount = amountField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            outputTextArea.appendText(
                    ingridientsDAO.addNewIngredient(new Ingridients(0, measure, amount, quantity), my_role));
            dialog.close();
        });

        dialogVBox.getChildren().addAll(
                new Label("Measure:"), measureField,
                new Label("Amount:"), amountField,
                new Label("Quantity:"), quantityField,
                addNewConfirmButton
        );

        Scene dialogScene = new Scene(dialogVBox, 300, 250);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }
}
