package org.example;

import org.example.View.View;

public class AppGUI {
    // Due to a known bug with JavaFX I needed to create this class that lives between the start of the execution of the app and the start of JavaFX
    public static void main( String[] args ){
        View.begin(args);
    }
}
