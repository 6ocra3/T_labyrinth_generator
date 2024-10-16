package backend.academy.labyrinth;

import backend.academy.labyrinth.extraStructures.point.Point;
import backend.academy.labyrinth.generators.HuntAndKillGenerator;
import backend.academy.labyrinth.inputOutput.DefaultIO;
import backend.academy.labyrinth.maze.Maze;
import backend.academy.labyrinth.solvers.DFSSolver;
import java.util.Iterator;

public class Labyrinth {
    public Labyrinth(){
        System.out.println("Labyrinth запущен");
        int width = 16;
        int height = 16;
        HuntAndKillGenerator generator = new HuntAndKillGenerator(width, height);
        generator.generate();
        Point start = new Point(0, 0);
        Point end = new Point(width-1, height-1);
        Maze maze = new Maze(generator.maze(),width, height, start, end);
        DefaultIO defaultIO = new DefaultIO();
        defaultIO.visualizeMaze(maze);
        DFSSolver bfsSolver = new DFSSolver();
        Iterator<Maze> solvIterator = bfsSolver.getIterator(maze);
        while (solvIterator.hasNext()){
            defaultIO.visualizeMaze(solvIterator.next());
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
