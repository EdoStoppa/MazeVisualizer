package org.example.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneFactory {

    public static Scene startScene() throws IOException {
        View.loader = new FXMLLoader(SceneFactory.class.getResource("/FXML/startScene.fxml"));
        Parent root = View.loader.load();
        return new Scene(root);
    }

    public static Scene infoScene() throws IOException{
        View.loader = new FXMLLoader(SceneFactory.class.getResource("/FXML/infoScene.fxml"));
        Parent root = View.loader.load();
        return new Scene(root);
    }

    public static Scene mainScene() throws IOException {
        View.loader = new FXMLLoader(SceneFactory.class.getResource("/FXML/mainScene.fxml"));
        Parent root = View.loader.load();
        return new Scene(root);
    }

}
