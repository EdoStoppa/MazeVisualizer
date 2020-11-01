package org.example.View;

import javafx.event.ActionEvent;
import org.example.Message.Message;
import org.example.Model.MazeGenerator.MazeGenerator;
import org.example.Model.Model;
import org.example.Observ.Observer;

public class Controller implements Observer<Message> {
    Model model;

    public Controller(){
        this.model = new Model(this);
    }

    @Override
    public void update(Message message) {

    }

    public void createMaze(int dim, MazeGenerator gen) {
        // :)
        model.createMaze(dim, gen);
    }

}
