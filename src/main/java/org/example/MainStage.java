package org.example;

import javafx.geometry.Insets;
import javafx.stage.Stage;
import org.example.Tables.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class MainStage {
    static ClientsDAO clientsDAO = new ClientsDAO();
    static DishesDAO dishesDAO = new DishesDAO();
    static DishIngridientsDAO dishIngridientsDAO = new DishIngridientsDAO();
    static IngridientsDAO ingridientsDAO = new IngridientsDAO();
    static OrderDishesDAO orderDishesDAO = new OrderDishesDAO();
    static OrdersDAO ordersDAO = new OrdersDAO();
    static TablesDAO tablesDAO = new TablesDAO();
    static UsersDAO usersDAO = new UsersDAO();
    static WaitersDAO waitersDAO = new WaitersDAO();

    static String my_role = GraphicalInterface.getRole();

    public static void startStage(Stage stage) {
        stage.setTitle("Restaurant");
        stage.setFullScreen(true);
        stage.show();
    }

    public static void stageWorking(Stage stage) {
        System.out.println(123);
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(root, 800, 600);

        GridPane clientsGrid = createClientsGrid();
        GridPane dishesGrid = createDishesGrid();
        GridPane dishIngredientsGrid = createDishIngredientsGrid();
        GridPane ingredientsGrid = createIngredientsGrid();
        GridPane orderDishesGrid = createOrderDishesGrid();
        GridPane ordersGrid = createOrdersGrid();
        GridPane tablesGrid = createTablesGrid();
        GridPane usersGrid = createUsersGrid();
        GridPane waitersGrid = createWaitersGrid();

        // Добавление сеток в корневой контейнер
        root.getChildren().addAll(
                clientsGrid,
                dishesGrid,
                dishIngredientsGrid,
                ingredientsGrid,
                orderDishesGrid,
                ordersGrid,
                tablesGrid,
                usersGrid,
                waitersGrid
        );

        stage.setScene(scene);
    }

    private static GridPane createClientsGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        Button addButton = new Button("Add Client");
        addButton.setOnAction(e -> {
            clientsDAO.addClient(new Clients(1,"John Doe", "123 Main St"), my_role); //???
            System.out.println("Client added");
        });
        Button deleteButton = new Button("Delete Client");  //???
        deleteButton.setOnAction(e -> {
            clientsDAO.deleteClient(1, my_role);
            System.out.println("Client deleted");
        });
        Button getOneButton = new Button("Get Client"); //???
        getOneButton.setOnAction(e -> {
            clientsDAO.getClient(1, my_role);
            System.out.println("Client getted");
        });
        Button getButton = new Button("Get Clients");
        getButton.setOnAction(e -> {
            clientsDAO.getAllClients(my_role).forEach(System.out::println);
        });


        grid.add(addButton, 0, 0);
        grid.add(deleteButton, 1, 0);
        grid.add(getOneButton, 2, 0);
        grid.add(getButton, 3, 0);

        return grid;
    }

    private static GridPane createDishesGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        Button addButton = new Button("Add Dish");
        addButton.setOnAction(e -> {
            dishesDAO.addDish(new Dishes(1, "Pizza"), my_role); //???
            System.out.println("Dish added");
        });
        Button deleteButton = new Button("Delete Dish");
        deleteButton.setOnAction(e -> {
            dishesDAO.deleteDish(1, my_role); //???
            System.out.println("Dish deleted");
        });
        Button getOneButton = new Button("Get Dish");
        getOneButton.setOnAction(e -> {
            dishesDAO.getDish(1, my_role); //???
            System.out.println("Dish getted");
        });
        Button getButton = new Button("Get Dishes");
        getButton.setOnAction(e -> {
            dishesDAO.getAllDishes(my_role).forEach(System.out::println);
        });

        grid.add(addButton, 0, 0);
        grid.add(deleteButton, 1, 0);
        grid.add(getOneButton, 2, 0);
        grid.add(getButton, 3, 0);

        return grid;
    }

    private static GridPane createDishIngredientsGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        Button addButton = new Button("Add Dish Ingredient");
        addButton.setOnAction(e -> {
            dishIngridientsDAO.addDishIngredient(new DishIngridients(1, 3, 5), my_role); //???
            System.out.println("Dish Ingredient added");
        });
        Button deleteButton = new Button("Delete Dish Ingredient");
        deleteButton.setOnAction(e -> {
            dishIngridientsDAO.deleteDishIngredient(1, 3, my_role); //???
            System.out.println("Dish Ingredient deleted");
        });
        Button getOneButton = new Button("Get Dish Ingredients");
        getOneButton.setOnAction(e -> {
            dishIngridientsDAO.getDishIngredient(1, my_role); //???
            System.out.println("Dish Ingredient getted");
        });
        Button getButton = new Button("Get Dishes Ingredients");
        getButton.setOnAction(e -> {
            dishIngridientsDAO.getAllDishIngredients(my_role).forEach(System.out::println);
        });

        grid.add(addButton, 0, 0);
        grid.add(deleteButton, 1, 0);
        grid.add(getOneButton, 2, 0);
        grid.add(getButton, 3, 0);

        return grid;
    }

    private static GridPane createIngredientsGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        Button addButton = new Button("Add Ingredient");
        addButton.setOnAction(e -> {
            ingridientsDAO.addIngredient(1, 5, my_role); //???
            System.out.println("Ingredient added");
        });

        Button addNewButton = new Button("Add New Ingredient");
        addNewButton.setOnAction(e -> {
            ingridientsDAO.addNewIngredient(new Ingridients(1, "1", "2", 5), my_role); //???
            System.out.println("Ingredient added");
        });
        Button getButton = new Button("Get Ingredients");
        getButton.setOnAction(e -> {
            ingridientsDAO.getAllIngredients(my_role).forEach(System.out::println);
        });

        grid.add(addButton, 0, 0);
        grid.add(addNewButton, 1, 0);
        grid.add(getButton, 2, 0);

        return grid;
    }

    private static GridPane createOrderDishesGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button addButton = new Button("Add Dish");
        Button deleteButton = new Button("Delete Dish");
        Button getOneButton = new Button("Get Order Dishes");
        Button getButton = new Button("Get All");

        addButton.setOnAction(e -> {
            orderDishesDAO.addOrderDish(new OrderDishes(1, 3, 5), my_role);//???
        });
        deleteButton.setOnAction(e -> {
            orderDishesDAO.deleteOrderDish(1, 3, my_role);//???
        });
        getOneButton.setOnAction(e ->{
            orderDishesDAO.getOrderDishes(1, my_role);//???
        });
        getButton.setOnAction(e -> {
            List<OrderDishes> orderDishes = orderDishesDAO.getAllOrderDishes(my_role);
            orderDishes.forEach(System.out::println);
        });

        grid.add(addButton, 0, 0);
        grid.add(deleteButton, 1, 0);
        grid.add(getOneButton, 2, 0);
        grid.add(getButton, 3, 0);

        return grid;
    }

    private static GridPane createOrdersGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button addButton = new Button("Add order");
        Button getButton = new Button("Get All orders");

        addButton.setOnAction(e -> {
            // Добавить правильные параметры
            ordersDAO.addOrder(new Orders(1, new Date(0), new Time(0), new Time(0),
                    3, 5, 7), my_role); //?
        });
        getButton.setOnAction(e -> {
            List<Orders> orders = ordersDAO.getAllOrders(my_role);
            orders.forEach(System.out::println);
        });

        grid.add(addButton, 0, 0);
        grid.add(getButton, 1, 0);

        return grid;
    }

    private static GridPane createTablesGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button updateButton = new Button("Upodate status");
        Button getButton = new Button("Get All");

        updateButton.setOnAction(e -> {
            tablesDAO.updateTableEmploy(1, true, my_role);
        });

        getButton.setOnAction(e -> {
            List<Tables> tables = tablesDAO.getAllTables(my_role);
            tables.forEach(System.out::println);
        });

        grid.add(updateButton, 0, 0);
        grid.add(getButton, 1, 0);

        return grid;
    }

    private static GridPane createUsersGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button deleteButton = new Button("Delete user");
        Button updateRoleButton = new Button("Update role user");
        Button updateDataButton = new Button("Update Data User");
        Button getButton = new Button("Get User");
        Button getAllButton = new Button("Get All Users");
        Button updateSelfDataButton = new Button("Update Self Data");

        deleteButton.setOnAction(e -> {
            usersDAO.deleteUser(1, my_role);
        });
        updateRoleButton.setOnAction(e -> {
            usersDAO.updateUserRole(1, 2, my_role);
        });
        updateDataButton.setOnAction(e -> {
            usersDAO.updateUserData(1, "1", "12", "123", my_role);
        });
        getButton.setOnAction(e -> {
            usersDAO.getUser(1, my_role);
        });
        getAllButton.setOnAction(e -> {
            List<String> users = usersDAO.getAllUsers(my_role);
            users.forEach(System.out::println);
        });
        updateSelfDataButton.setOnAction(e -> {
            usersDAO.updateSelfData("12", "123", "13", "321", "fg");
        });


        grid.add(deleteButton, 0, 0);
        grid.add(updateRoleButton, 1, 0);
        grid.add(updateDataButton, 2, 0);
        grid.add(getButton, 3, 0);
        grid.add(getAllButton, 4, 0);
        grid.add(updateSelfDataButton, 5, 0);

        return grid;
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

        addButton.setOnAction(e -> {
            // Добавить правильные параметры
            waitersDAO.addWaiter(new Waiters(1, "a", "s", "2"), my_role);
        });
        deleteButton.setOnAction(e -> {
            waitersDAO.deleteWaiter(1, my_role);
        });
        updateButton.setOnAction(e -> {
            waitersDAO.updateWaiterData(1, "1", "1", "1", my_role);
        });
        getButton.setOnAction(e -> {
            List<Waiters> waiters = waitersDAO.getAllWaiters(my_role);
            waiters.forEach(System.out::println);
        });

        grid.add(addButton, 0, 0);
        grid.add(deleteButton, 1, 0);
        grid.add(updateButton, 2, 0);
        grid.add(getButton, 3, 0);

        return grid;
    }

}
