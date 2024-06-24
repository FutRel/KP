package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphicInterface extends Application {


    @Override
    public void start(Stage primaryStage) {
        // Создаем метку
        Label label = new Label("Hello, JavaFX!");

        // Создаем кнопку
        Button button = new Button("Click me");
        button.setOnAction(event -> label.setText("Button clicked!"));

        // Создаем контейнер и добавляем в него элементы
        VBox vbox = new VBox(label, button);
        vbox.setSpacing(10); // Устанавливаем расстояние между элементами

        // Создаем сцену с нашим контейнером
        Scene scene = new Scene(vbox, 300, 200);

        // Настраиваем основное окно (stage)
        primaryStage.setTitle("My JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}