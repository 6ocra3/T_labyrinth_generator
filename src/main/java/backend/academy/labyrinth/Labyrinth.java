package backend.academy.labyrinth;

import backend.academy.labyrinth.extraStructures.point.Point;
import backend.academy.labyrinth.generators.HuntAndKillGenerator;
import backend.academy.labyrinth.generators.KraskalGenerator;
import backend.academy.labyrinth.inputOutput.DefaultIO;
import backend.academy.labyrinth.maze.Maze;
import backend.academy.labyrinth.solvers.BFSSolver;
import backend.academy.labyrinth.solvers.DFSSolver;

public class Labyrinth {
    public Labyrinth(){
        System.out.println("Labyrinth запущен");
        int width = 10;
        int height = 10;
//        KraskalGenerator generator = new KraskalGenerator(width, height);
        HuntAndKillGenerator generator = new HuntAndKillGenerator(width, height);
        generator.generate();
        Point start = new Point(0, 0);
        Point end = new Point(width-1, height-1);
        Maze maze = new Maze(generator.maze(),width, height, start, end);
        DefaultIO defaultIO = new DefaultIO();
        defaultIO.visualizeMaze(maze);
//        DFSSolver dfsSolver = new DFSSolver();
//        dfsSolver.maze(maze);
//        Maze solvedMaze = dfsSolver.stepByStepSolve();
//        while (!dfsSolver.isFinished()){
//            dfsSolver.nextStep();
//            defaultIO.visualizeMaze(solvedMaze);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
