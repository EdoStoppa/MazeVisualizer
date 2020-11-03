package org.example.View;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.Controller.Controller;
import org.example.Model.Model;
import org.example.View.MazeView.MazePane;

public class View extends Application {
    public static Controller controller = null;
    public static Stage primaryStage = null;
    public static MazePane mazePane = null;
    private static int height = 900, width = 1200;

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

    public static int getHeight(){
        return height;
    }
    public static int getWidth(){
        return width;
    }
    public static void setHeight(int _height){
        height = _height;
    }
    public static void setWidth(int _width){
        width = _width;
    }

}
