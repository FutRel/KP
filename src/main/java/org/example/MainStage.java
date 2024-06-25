package org.example;

import javafx.stage.Stage;
import java.util.*;

public class MainStage {

    static String my_role = GraphicalInterface.getRole();

    public static void startStage(Stage stage) {
        stage.setTitle("Restaurant");
        stage.setFullScreen(true);
        stage.show();
    }

    public static void stageWorking(Stage stage) {
        System.out.println(123);

    }
}
