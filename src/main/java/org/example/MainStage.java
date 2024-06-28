package org.example;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.Tables.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.*;

public class MainStage {

    static String my_role;
    static String my_login;
    static String my_password;

    public static void startStage(Stage stage) {
        stage.setTitle("Restaurant");
        stage.setFullScreen(true);
        stage.show();
    }

    public static void stageWorking(Stage stage) {
        my_role = GraphicalInterface.getRole();
        my_login = GraphicalInterface.getLogin();
        my_password = GraphicalInterface.getPasswordLogin();

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        // Создание сеток для различных операций
        Button clients = new Button();
        Button dishes = new Button();
        Button dishIngridients = new Button();
        Button ingridients = new Button();
        Button orderDishes = new Button();
        Button orders = new Button();
        Button tables = new Button();
        Button users = new Button();
        Button waiters = new Button();

        clients.setText("Clients");
        dishes.setText("Dishes");
        dishIngridients.setText("Dish Ingredients");
        ingridients.setText("Ingredients");
        orderDishes.setText("Order Dishes");
        orders.setText("Orders");
        tables.setText("Tables");
        users.setText("Users");
        waiters.setText("Waiters");

        clients.setOnAction(e -> {
            Stage sc = new Stage();
            ClientsView clientsView = new ClientsView();
            clientsView.start(sc);
        });
        dishes.setOnAction(e -> {
            Stage sd = new Stage();
            DishesView dishesView = new DishesView();
            dishesView.start(sd);
        });
        dishIngridients.setOnAction(e -> {
            Stage sdi = new Stage();
            DishIngridientsView dishIngridientsView = new DishIngridientsView();
            dishIngridientsView.start(sdi);
        });
        ingridients.setOnAction(e -> {
            Stage si = new Stage();
            IngridientsView ingridientsView = new IngridientsView();
            ingridientsView.start(si);
        });
        orderDishes.setOnAction(e -> {
            Stage sod = new Stage();
            OrderDishesView orderDishesView = new OrderDishesView();
            orderDishesView.start(sod);
        });
        orders.setOnAction(e -> {
            Stage so = new Stage();
            OrdersView ordersView = new OrdersView();
            ordersView.start(so);
        });
        tables.setOnAction(e -> {
            Stage st = new Stage();
            TablesView tablesView = new TablesView();
            tablesView.start(st);
        });
        users.setOnAction(e -> {
            Stage su = new Stage();
            UsersView usersView = new UsersView();
            usersView.start(su);
        });
        waiters.setOnAction(e -> {
            Stage sw = new Stage();
            WaitersView waitersView = new WaitersView();
            waitersView.start(sw);
        });

        root.add(clients, 0, 0);
        root.add(dishes, 0, 1);
        root.add(dishIngridients, 0, 2);
        root.add(ingridients, 0, 3);
        root.add(orderDishes, 0, 4);
        root.add(orders, 0, 5);
        root.add(tables, 0, 6);
        root.add(users, 0, 7);
        root.add(waiters, 0, 8);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

}
