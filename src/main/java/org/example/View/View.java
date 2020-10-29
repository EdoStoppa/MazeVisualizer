package org.example.View;

import javafx.application.Application;
import javafx.stage.Stage;

public class View extends Application {
    public static Stage primaryStage;
    private static int height = 800, width = 1200;

    public static void begin(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setResizable(false);
        primaryStage.setTitle("MazeVisualizer");
        primaryStage.setOnCloseRequest(e -> primaryStage.close());
        primaryStage.setScene(SceneFactory.welcomeScene());
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
