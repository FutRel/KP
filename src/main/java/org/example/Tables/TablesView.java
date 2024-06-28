package org.example.Tables;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.GraphicalInterface;

import java.util.List;

public class TablesView extends Application {

    static TablesDAO tablesDAO = new TablesDAO();

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

        GridPane tablesGrid = createTablesGrid();

        root.add(tablesGrid, 1, 0);
        root.add(outputTextArea, 0, 0);

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
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
            outputTextArea.setText(
                    tablesDAO.updateTableEmploy(new Tables(id, 0,0), employ, my_role));
            dialog.close();
        });

        vbox.getChildren().addAll(idField, employCheckBox, submitButton);

        Scene dialogScene = new Scene(vbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

}
