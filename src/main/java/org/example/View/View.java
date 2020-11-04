package org.example.View;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.Controller.Controller;
import org.example.View.MazeView.MazePane;

public class View extends Application {
    public static Controller controller = null;
    public static Stage primaryStage = null;
    public static MazePane mazePane = null;

    public static void begin(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setResizable(false);
        primaryStage.setTitle("MazeVisualizer");
        primaryStage.setOnCloseRequest(e -> primaryStage.close());
        primaryStage.setScene(SceneFactory.startScene());
        primaryStage.show();
    }

}
