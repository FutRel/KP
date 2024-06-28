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

public class OrderDishesView extends Application {

    static OrderDishesDAO orderDishesDAO = new OrderDishesDAO();

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

        GridPane orderDishesGrid = createOrderDishesGrid();

        root.add(orderDishesGrid, 1, 0);
        root.add(outputTextArea, 0, 0);

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private static GridPane createOrderDishesGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button addButton = new Button("Add Order Dish");
        addButton.setOnAction(e -> openAddOrderDishDialog());

        Button deleteButton = new Button("Delete Order Dish");
        deleteButton.setOnAction(e -> openDeleteOrderDishDialog());

        Button getOneButton = new Button("Get Order Dish");
        getOneButton.setOnAction(e -> openGetOrderDishesDialog());

        Button getAllButton = new Button("Get All Order Dishes");
        getAllButton.setOnAction(e -> {
            List<String> orderDishesList = orderDishesDAO.getAllOrderDishes(my_role);
            orderDishesList.forEach(dish -> outputTextArea.appendText(dish + "\n"));
        });

        grid.add(addButton, 0, 0);
        grid.add(deleteButton, 1, 0);
        grid.add(getOneButton, 2, 0);
        grid.add(getAllButton, 3, 0);

        return grid;
    }

    private static void openAddOrderDishDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Order Dish");

        VBox dialogVBox = new VBox();
        dialogVBox.setSpacing(10);
        dialogVBox.setPadding(new Insets(20));

        TextField dishIdField = new TextField();
        TextField amountField = new TextField();

        Button addConfirmButton = new Button("Add");
        addConfirmButton.setOnAction(event -> {
            int dishId = Integer.parseInt(dishIdField.getText());
            int amount = Integer.parseInt(amountField.getText());
            outputTextArea.appendText(orderDishesDAO.addOrderDish(new OrderDishes(0, dishId, amount), my_role));
            dialog.close();
        });

        dialogVBox.getChildren().addAll(
                new Label("Dish ID:"), dishIdField,
                new Label("Amount:"), amountField,
                addConfirmButton
        );

        Scene dialogScene = new Scene(dialogVBox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private static void openDeleteOrderDishDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Delete Order Dish");

        VBox dialogVBox = new VBox();
        dialogVBox.setSpacing(10);
        dialogVBox.setPadding(new Insets(20));

        TextField orderIdField = new TextField();
        TextField dishIdField = new TextField();

        Button deleteConfirmButton = new Button("Delete");
        deleteConfirmButton.setOnAction(event -> {
            int orderId = Integer.parseInt(orderIdField.getText());
            int dishId = Integer.parseInt(dishIdField.getText());
            outputTextArea.appendText(orderDishesDAO.deleteOrderDish(orderId, dishId, my_role));
            dialog.close();
        });

        dialogVBox.getChildren().addAll(
                new Label("Order ID:"), orderIdField,
                new Label("Dish ID:"), dishIdField,
                deleteConfirmButton
        );

        Scene dialogScene = new Scene(dialogVBox, 300, 150);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private static void openGetOrderDishesDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Get Order Dish");

        VBox dialogVBox = new VBox();
        dialogVBox.setSpacing(10);
        dialogVBox.setPadding(new Insets(20));

        TextField orderIdField = new TextField();

        Button getConfirmButton = new Button("Get");
        getConfirmButton.setOnAction(event -> {
            int orderId = Integer.parseInt(orderIdField.getText());
            List<String> orderDishes = orderDishesDAO.getOrderDishes(orderId, my_role);
            orderDishes.forEach(dish -> outputTextArea.appendText(dish + "\n"));
            dialog.close();
        });

        dialogVBox.getChildren().addAll(
                new Label("Order ID:"), orderIdField,
                getConfirmButton
        );

        Scene dialogScene = new Scene(dialogVBox, 300, 150);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }
}
