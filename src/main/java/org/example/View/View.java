package org.example.View;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.View.MazeView.MazePane;

public class View extends Application {
    public static Controller controller;
    public static Stage primaryStage;
    public static MazePane mazePane;
    private static int height = 960, width = 1200;

    public static void begin(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        controller = new Controller();
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
