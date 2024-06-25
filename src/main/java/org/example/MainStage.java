package org.example;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.Tables.*;
import javafx.scene.Scene;
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

    private static TextArea outputTextArea;

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
        outputTextArea = new TextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setPrefSize(1000, 1000);

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        // Создание сеток для различных операций
        GridPane clientsGrid = createClientsGrid();
        GridPane dishesGrid = createDishesGrid();
        GridPane dishIngredientsGrid = createDishIngredientsGrid();
        GridPane ingredientsGrid = createIngredientsGrid();
        GridPane orderDishesGrid = createOrderDishesGrid();
        GridPane ordersGrid = createOrdersGrid();
        GridPane tablesGrid = createTablesGrid();
        GridPane usersGrid = createUsersGrid();
        GridPane waitersGrid = createWaitersGrid();

        // Добавление сеток в основной GridPane
        root.add(clientsGrid, 1, 0);
        root.add(dishesGrid, 2, 0);
        root.add(dishIngredientsGrid, 0, 1);
        root.add(ingredientsGrid, 1, 1);
        root.add(orderDishesGrid, 2, 1);
        root.add(ordersGrid, 0, 2);
        root.add(tablesGrid, 1, 2);
        root.add(usersGrid, 2, 2);
        root.add(waitersGrid, 2, 3);
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
            outputTextArea.setText("All Clients:\n");
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
            clientsDAO.addClient(new Clients(0, name, address), my_role);
            outputTextArea.setText("Client added:\n" + name + ", " + address);
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
            clientsDAO.deleteClient(clientId, my_role);
            outputTextArea.setText("Client deleted:\nID: " + clientId);
            dialog.close();
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
            outputTextArea.appendText(clientsDAO.getClient(clientId, my_role));
            dialog.close();
        });

        grid.add(getButton, 1, 1);

        Scene dialogScene = new Scene(grid, 300, 100);
        dialog.setScene(dialogScene);
        dialog.show();
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
            outputTextArea.setText("All Dishes:\n");
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
            dishesDAO.addDish(new Dishes(0, name), my_role);
            outputTextArea.setText("Dish added:\n" + name);
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
            dishesDAO.deleteDish(dishId, my_role);
            outputTextArea.setText("Client deleted:\nID: " + dishId);
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
            outputTextArea.appendText(dishesDAO.getDish(dishId, my_role));
            dialog.close();
        });

        grid.add(getButton, 1, 1);

        Scene dialogScene = new Scene(grid, 300, 100);
        dialog.setScene(dialogScene);
        dialog.show();
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
            dishIngridientsDAO.addDishIngredient(new DishIngridients(0, ingredientId, quantity), my_role);
            outputTextArea.appendText("Dish Ingredient added\n");
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
            dishIngridientsDAO.deleteDishIngredient(dishId, ingredientId, my_role);
            outputTextArea.appendText("Dish Ingredient deleted\n");
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
            ingridientsDAO.addIngredient(ingredientId, quantity, my_role);
            outputTextArea.appendText("Ingredient added\n");
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
            ingridientsDAO.addNewIngredient(new Ingridients(0, measure, amount, quantity), my_role);
            outputTextArea.appendText("New Ingredient added\n");
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
            orderDishesDAO.addOrderDish(new OrderDishes(0, dishId, amount), my_role);
            outputTextArea.appendText("Order Dish added\n");
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
            orderDishesDAO.deleteOrderDish(orderId, dishId, my_role);
            outputTextArea.appendText("Order Dish deleted\n");
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


    private static GridPane createOrdersGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button addButton = new Button("Add Order");
        addButton.setOnAction(e -> openAddOrderDialog());

        Button getAllButton = new Button("Get All Orders");
        getAllButton.setOnAction(e -> {
            List<String> ordersList = ordersDAO.getAllOrders(my_role);
            ordersList.forEach(order -> outputTextArea.appendText(order + "\n"));
        });

        grid.add(addButton, 0, 0);
        grid.add(getAllButton, 1, 0);

        return grid;
    }

    private static void openAddOrderDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Add Order");

        VBox dialogVBox = new VBox();
        dialogVBox.setSpacing(10);
        dialogVBox.setPadding(new Insets(20));

        TextField dateOrderField = new TextField();
        TextField timeStartField = new TextField();
        TextField timeEndField = new TextField();
        TextField waiterIdField = new TextField();
        TextField tableIdField = new TextField();
        TextField clientIdField = new TextField();

        Button addConfirmButton = new Button("Add");
        addConfirmButton.setOnAction(event -> {
            Date dateOrder = Date.valueOf(dateOrderField.getText());
            Time timeStart = Time.valueOf(timeStartField.getText());
            Time timeEnd = Time.valueOf(timeEndField.getText());
            int waiterId = Integer.parseInt(waiterIdField.getText());
            int tableId = Integer.parseInt(tableIdField.getText());
            int clientId = Integer.parseInt(clientIdField.getText());

            ordersDAO.addOrder(new Orders(0, dateOrder, timeStart, timeEnd, waiterId, tableId, clientId), my_role);
            outputTextArea.appendText("Order added\n");
            dialog.close();
        });

        dialogVBox.getChildren().addAll(
                new Label("Date Order (yyyy-MM-dd):"), dateOrderField,
                new Label("Time Start (HH:mm:ss):"), timeStartField,
                new Label("Time End (HH:mm:ss):"), timeEndField,
                new Label("Waiter ID:"), waiterIdField,
                new Label("Table ID:"), tableIdField,
                new Label("Client ID:"), clientIdField,
                addConfirmButton
        );

        Scene dialogScene = new Scene(dialogVBox, 300, 400);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }


    private static GridPane createTablesGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button updateButton = new Button("Update Table Employ");
        Button getButton = new Button("Get All Tables");

        updateButton.setOnAction(e -> openUpdateTableEmployDialog());
        getButton.setOnAction(e -> {
            List<String> tables = tablesDAO.getAllTables(my_role);
            tables.forEach(table -> outputTextArea.appendText(table + "\n"));
        });

        grid.add(updateButton, 0, 0);
        grid.add(getButton, 1, 0);

        return grid;
    }

    private static void openUpdateTableEmployDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Update Table Employ");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        TextField idField = new TextField();
        idField.setPromptText("Table ID");

        CheckBox employCheckBox = new CheckBox("Employ");

        Button submitButton = new Button("Update");
        submitButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            boolean employ = employCheckBox.isSelected();
            tablesDAO.updateTableEmploy(id, employ, my_role);
            System.out.println("Table employ status updated");
            dialog.close();
        });

        vbox.getChildren().addAll(idField, employCheckBox, submitButton);

        Scene dialogScene = new Scene(vbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
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
            usersDAO.deleteUser(id, my_role);
            outputTextArea.appendText("User deleted");
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
            usersDAO.updateUserRole(id, role, my_role);
            System.out.println("User role updated");
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
            usersDAO.updateUserData(id, username, password, fullName, my_role);
            System.out.println("User data updated");
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
            outputTextArea.appendText(usersDAO.getUser(id, my_role));
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
            usersDAO.updateSelfData(my_login, my_password, username, password, fullName);
            System.out.println("Self data updated");
            dialog.close();
        });

        vbox.getChildren().addAll(usernameField, passwordField, fullNameField, submitButton);

        Scene dialogScene = new Scene(vbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
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
            waitersDAO.addWaiter(new Waiters(0, firstName, lastName, phoneNumber), my_role);
            System.out.println("Waiter added");
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
            waitersDAO.deleteWaiter(id, my_role);
            System.out.println("Waiter deleted");
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
            waitersDAO.updateWaiterData(id, firstName, lastName, phoneNumber, my_role);
            System.out.println("Waiter data updated");
            dialog.close();
        });

        vbox.getChildren().addAll(idField, firstNameField, lastNameField, phoneNumberField, submitButton);

        Scene dialogScene = new Scene(vbox, 300, 300);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

}
