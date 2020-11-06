package org.example.View;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.example.Controller.Controller;
import org.example.View.MazeView.MazePane;

import java.util.Timer;

public class View extends Application {
    public static final Timer timer = new Timer();
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
        primaryStage.setOnCloseRequest(e->{
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setScene(SceneFactory.startScene());
        primaryStage.show();
    }

}
