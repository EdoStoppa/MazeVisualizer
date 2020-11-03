package org.example.View.FXMLControllers;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import org.example.View.SceneFactory;
import org.example.View.View;

import java.io.IOException;

public class FXMLController {

    public void startScene(ActionEvent actionEvent) throws IOException {
        Scene scene = SceneFactory.startScene();
        View.primaryStage.setScene(scene);
    }

    public void mainScene(ActionEvent actionEvent) throws IOException {
        Scene scene = SceneFactory.mainScene();

        View.primaryStage.setScene(scene);
    }

    public void infoScene(ActionEvent actionEvent) throws IOException {
        Scene scene = SceneFactory.infoScene();
        View.primaryStage.setScene(scene);
    }
}
