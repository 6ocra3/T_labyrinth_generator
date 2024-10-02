package backend.academy.labyrinth;

import backend.academy.labyrinth.extraStructures.point.Point;
import backend.academy.labyrinth.generators.KraskalGenerator;
import backend.academy.labyrinth.inputOutput.DefaultIO;
import backend.academy.labyrinth.maze.Maze;
import backend.academy.labyrinth.solvers.BFSSolver;

public class Labyrinth {
    public Labyrinth(){
        System.out.println("Labyrinth запущен");
        int width = 10;
        int height = 10;
        KraskalGenerator generator = new KraskalGenerator(width, height);
        generator.generate();
        Point start = new Point(0, 0);
        Point end = new Point(width-1, height-1);
        Maze maze = new Maze(generator.maze(),width, height, start, end);
        DefaultIO defaultIO = new DefaultIO();
        defaultIO.visualizeMaze(maze);
        Maze solvedMaze = BFSSolver.solve(maze);
        defaultIO.visualizeMaze(solvedMaze);
    }
}
