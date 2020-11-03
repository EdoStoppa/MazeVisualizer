package org.example;

import org.example.Controller.Controller;
import org.example.Model.Model;
import org.example.View.View;

public class AppGUI {
    // Due to a known bug with JavaFX I needed to create this class that lives between the start of the execution of the app and the start of JavaFX.
    // Otherwise this is pretty useless...
    public static void main( String[] args ){
        View.controller = new Controller();
        Model model = new Model(View.controller);
        View.controller.setModel(model);
        View.begin(args);
    }
}
