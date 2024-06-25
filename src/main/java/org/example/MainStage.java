package org.example;

import javafx.stage.Stage;
import java.util.*;

public class MainStage {
    static String url = "jdbc:mysql://localhost:3306/kp";
    static String user = "root";
    static String password = "SQL1234";

    static String role = GraphicalInterface.getRole();

    public static void startStage(Stage stage) {
        stage.setTitle("Restaurant");
        stage.setFullScreen(true);
        stage.show();
    }

    public static void stageWorking(Stage stage) {
        System.out.println(123);

    }
}
