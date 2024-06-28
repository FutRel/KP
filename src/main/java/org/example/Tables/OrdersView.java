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
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class OrdersView extends Application {

    static OrdersDAO ordersDAO = new OrdersDAO();

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

        GridPane ordersGrid = createOrdersGrid();

        root.add(ordersGrid, 1, 0);
        root.add(outputTextArea, 0, 0);

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
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

            outputTextArea.appendText(
                    ordersDAO.addOrder(new Orders(
                            0, dateOrder, timeStart, timeEnd, waiterId, tableId, clientId), my_role));
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

}
