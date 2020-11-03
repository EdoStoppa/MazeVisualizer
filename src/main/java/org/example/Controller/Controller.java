package org.example.Controller;

import javafx.event.ActionEvent;
import org.example.Message.Message;
import org.example.Model.MazeGenerator.MazeGenerator;
import org.example.Model.MazeSolver.MazeSolver;
import org.example.Model.Model;
import org.example.Observ.Observer;
import org.example.View.View;

public class Controller implements Observer<Message> {
    Model model;

    public void setModel(Model model){
        this.model = new Model(this);
    }

    public void createMaze(int dim, MazeGenerator gen) {
        // :)
        model.createMaze(dim, gen);
    }

    public void createMazeCLI(int dim, MazeGenerator gen){
        model.createMaze(dim, gen);
        model.printMaze();
    }

    public void solveMaze(MazeSolver solver){
        model.solveMaze(solver);
    }

    public void solveMazeCLI(MazeSolver solver){
        model.solveMaze(solver);
        model.printMaze();
    }

    @Override
    public void update(Message message) {
        if(View.primaryStage!=null)
            message.renderGUI();
    }
}
