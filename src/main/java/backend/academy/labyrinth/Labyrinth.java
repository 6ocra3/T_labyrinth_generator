package backend.academy.labyrinth;

import backend.academy.labyrinth.generators.KraskalGenerator;
import backend.academy.labyrinth.inputOutput.DefaultIO;
import backend.academy.labyrinth.maze.Maze;

public class Labyrinth {
    public Labyrinth(){
        System.out.println("Labyrinth запущен");
        int width = 5;
        int height = 5;
        KraskalGenerator generator = new KraskalGenerator(width, height);
        generator.generate();
        Maze maze = new Maze(generator.maze(),width, height);
        DefaultIO defaultIO = new DefaultIO();
        defaultIO.visualizeMaze(maze);

    }
}
