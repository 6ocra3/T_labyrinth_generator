package backend.academy.labyrinth;

import backend.academy.labyrinth.extraStructures.point.Point;
import backend.academy.labyrinth.generators.Generator;
import backend.academy.labyrinth.generators.HuntAndKillGenerator;
import backend.academy.labyrinth.generators.KruskalGenerator;
import backend.academy.labyrinth.inputOutput.DefaultIO;
import backend.academy.labyrinth.maze.Maze;
import backend.academy.labyrinth.solvers.BFSSolver;
import backend.academy.labyrinth.solvers.DFSSolver;
import backend.academy.labyrinth.solvers.Solver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Labyrinth {

    List<Generator> generatorsList = Arrays.asList(new HuntAndKillGenerator(), new KruskalGenerator());
    List<Solver> solversList = Arrays.asList(new BFSSolver(), new DFSSolver());

    public Labyrinth(){
        System.out.println("Labyrinth запущен");
        int width = 16;
        int height = 16;
        HuntAndKillGenerator generator = new HuntAndKillGenerator();
        generator.generate(width, height);
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
