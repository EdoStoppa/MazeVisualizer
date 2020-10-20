package org.example;

import org.example.Model.Maze;
import org.example.Model.MazeGenerator.*;
import org.example.Model.MazeSolver.MazeSolver;
import org.example.Model.Model;
import org.example.View.View;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * MazeVisualizer!
 *
 * This project is a sort of toy project, aimed at learning different types of generation/solving algorithms, generally geared towards graphs.
 *
 * The app will be able to generate a maze (with length = width) with a dimension decided by the user (obviously between some constraints). The app will
 * generate a maze through 4 different algorithms, each one showing a very different technique. Then it will solve it again using 3 or 4 different algorithms.
 *
 * I'll be implementing 2 different user interfaces, one using command line (commonly called CLI), and the other using a more traditional GUI (probably using
 * Swing, because being a toy project I think it's better to suffer a little bit more creating the user interface, but gain a deeper knowledge about how GUI works).
 *
 */
public class AppCLI
{
    public static void main( String[] args ) {
        Scanner in = new Scanner(System.in);
        int input;
        String end;
        int dim;

        View view = new View();
        Model model = new Model(view);

        List<MazeGenerator> generatorsList = Maze.getAllGenerators();
        List<MazeSolver> solversList = Maze.getAllSolvers();

        System.out.println("\n\n" + intro());
        System.out.println("Welcome to MazeVisualizer!");
        System.out.println("In this App you'll be able to generate and solve mazes using multiple algorithms!");

        while(true){
            System.out.println("\nPlease, choose the maze's dimension! (max dimension: 30, min dimension: 8)");
            dim = getNumInput(in, 8,30);

            System.out.println("\nPlease, write any of these numbers to select the generator Algorithm");
            for(int i=0; i<generatorsList.size(); i++){
                System.out.println(i + ") " + generatorsList.get(i).toString());
            }

            input = getNumInput(in, 0, generatorsList.size());
            model.createMaze(dim, generatorsList.get(input));

            if(waitComputation(model))
                return;

            System.out.println("\nPlease, write any of these numbers to select the solver Algorithm");
            for(int i=0; i<solversList.size(); i++){
                System.out.println(i + ") " + solversList.get(i).toString());
            }

            input = getNumInput(in, 0, solversList.size());
            model.solveMaze(solversList.get(input));

            if(waitComputation(model))
                return;

            System.out.println("\nThanks for using this App, if you want to generate another maze please type \"n\"!");
            System.out.println("To quit the App please type \"q\".");
            end = in.nextLine();
            while(true){
                if(end.equals("q"))
                    return;

                if(end.equals("n"))
                    break;
                System.out.println("Please make a valid choice...");
                end = in.nextLine();
            }
        }

    }

    // ---------------    Helper methods used to clean main    ---------------
    static int getNumInput(Scanner in, int min, int max){
        String input;

        while(true){
            try{
                input = in.nextLine();
                if(Integer.parseInt(input) >= min && Integer.parseInt(input) < max){
                    break;
                } else {
                    System.out.println("Sorry, invalid choice, please try again!");
                }
            } catch (NoSuchElementException | IllegalStateException | NumberFormatException e){
                System.err.println("Error in getting input");
                System.out.println("Invalid input, please try again!");
            }
        }

        return Integer.parseInt(input);
    }
    static boolean waitComputation(Model model){
        //method returns true if something bad happen
        try {
            model.getCurrentThread().join();
        } catch(InterruptedException e){
            System.err.println("Problem while waiting for end of maze generation");
            System.out.println("\n\nSomething very bad happened, please restart the App!\n\n");
            return true;
        }

        return false;
    }

    // ---------------    Miscellaneous    ---------------
    private static String intro(){
        return " __  __            __      ___                 _ _              \n" +
                "|  \\/  |           \\ \\    / (_)               | (_)             \n" +
                "| \\  / | __ _ ______\\ \\  / / _ ___ _   _  __ _| |_ _______ _ __ \n" +
                "| |\\/| |/ _` |_  / _ \\ \\/ / | / __| | | |/ _` | | |_  / _ \\ '__|\n" +
                "| |  | | (_| |/ /  __/\\  /  | \\__ \\ |_| | (_| | | |/ /  __/ |   \n" +
                "|_|  |_|\\__,_/___\\___| \\/   |_|___/\\__,_|\\__,_|_|_/___\\___|_|   \n" +
                "                                                                \n" +
                "                                                                ";
    }
}
