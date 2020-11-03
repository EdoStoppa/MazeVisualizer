package org.example.View;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;

public class SceneFactory {

    public static Scene startScene() throws IOException {
        Parent root = FXMLLoader.load(SceneFactory.class.getResource("/FXML/startScene.fxml"));
        return new Scene(root);
    }

    public static Scene infoScene() throws IOException{
        Parent root = FXMLLoader.load(SceneFactory.class.getResource("/FXML/infoScene.fxml"));
        return new Scene(root);
    }

    public static Scene mainScene() throws IOException {
        Parent root = FXMLLoader.load(SceneFactory.class.getResource("/FXML/mainScene.fxml"));
        return new Scene(root);
    }

}
