package org.example;

import org.example.Model.Maze;
import org.example.Model.MazeGenerator.*;
import org.example.Model.MazeSolver.MazeSolver;
import org.example.Model.Model;
import org.example.View.View;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class AppCLI
{
    public static void main( String[] args ) {
        Scanner in = new Scanner(System.in);
        String input;

        View view = new View();
        Model model = new Model(view);

        List<MazeGenerator> generatorsList = Maze.getAllGenerators();
        List<MazeSolver> solversList = Maze.getAllSolvers();

        System.out.println("\n\n" + intro());
        System.out.println("Welcome to MazeVisualizer!");
        System.out.println("In this App you'll be able to generate and solve mazes using multiple algorithms!");

        while(true){
            System.out.println("\nPlease, write any of these numbers to select the generator Algorithm");
            for(int i=0; i<generatorsList.size(); i++){
                System.out.println(i + ") " + generatorsList.get(i).toString());
            }

            while(true){
                try{
                    input = in.nextLine();
                    if(Integer.parseInt(input) >= 0 && Integer.parseInt(input) < generatorsList.size()){
                        break;
                    } else {
                        System.out.println("Sorry, invalid choice, please try again!");
                    }
                } catch (NoSuchElementException | IllegalStateException e){
                    System.err.println("Error in getting input");
                    System.out.println("Invalid input, please try again!");
                }
            }

            System.out.println("\nThanks you for your choice, generating the maze...");
            try{
                Thread.sleep(1000);
            } catch(IllegalArgumentException | InterruptedException e) {
                System.err.println("Something went wrong while trying to sleep");
                return;
            }

            model.createMaze(15, generatorsList.get(Integer.parseInt(input)));
            try {
                model.getCurrentThread().join();
            } catch(InterruptedException e){
                System.err.println("Problem while waiting for end of maze generation");
                System.out.println("\n\nSomething very bad happened, please restart the App!\n\n");
                return;
            }

            System.out.println("\nPlease, write any of these numbers to select the solver Algorithm");
            for(int i=0; i<solversList.size(); i++){
                System.out.println(i + ") " + generatorsList.get(i).toString());
            }

            while(true){
                try{
                    input = in.nextLine();
                    if(Integer.parseInt(input) >= 0 && Integer.parseInt(input) < solversList.size()){
                        break;
                    } else {
                        System.out.println("Sorry, invalid choice, please try again!");
                    }
                } catch (NoSuchElementException | IllegalStateException e){
                    System.err.println("Error in getting input");
                    System.out.println("Invalid input, please try again!");
                }
            }

            model.solveMaze(solversList.get(Integer.parseInt(input)));
            try {
                model.getCurrentThread().join();
            } catch(InterruptedException e){
                System.err.println("Problem while waiting for end of maze solution");
                System.out.println("\n\nSomething very bad happened, please restart the App!\n\n");
                return;
            }

            System.out.println("\nThanks for using this App, if you want to generate another maze please type \"new\"!");
            System.out.println("To close the App please type \"exit\".");
            input = in.nextLine();
            while(true){
                if(input.equals("exit"))
                    return;

                if(input.equals("new"))
                    break;
                System.out.println("Please make a valid choice...");
                input = in.nextLine();
            }
        }

    }

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
