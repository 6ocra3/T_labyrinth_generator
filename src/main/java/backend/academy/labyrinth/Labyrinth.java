package backend.academy.labyrinth;

import backend.academy.labyrinth.extraStructures.BaseObject;
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
        DefaultIO defaultIO = new DefaultIO();
        int width = defaultIO.getSomeIntParams("Введите ширину лабиринта",16);
        int height = defaultIO.getSomeIntParams("Введите высоту лабиринта",16);

        int generatorInd = defaultIO.chooseObjectByIndex("Выберите генератор", new ArrayList<>(generatorsList));
        Generator generator = generatorsList.get(generatorInd);

        int solverInd = defaultIO.chooseObjectByIndex("Выберите алгоритм решения", new ArrayList<>(solversList));
        Solver solver = solversList.get(solverInd);

        Point start = defaultIO.getSomePoint("Введите координаты старта", 0, 0, width, 0, 0, height);
        Point end = defaultIO.getSomePoint("Введите координаты конца", width-1, 0, width, height-1, 0, height);
        Maze maze = new Maze(generator.generate(width, height),width, height, start, end);
        defaultIO.visualizeMaze(maze);
        Maze solvedMaze = solver.solve(maze);
        defaultIO.visualizeMaze(solvedMaze);

//        DFSSolver bfsSolver = new DFSSolver();
//        Iterator<Maze> solvIterator = bfsSolver.getIterator(maze);
//        while (solvIterator.hasNext()){
//            defaultIO.visualizeMaze(solvIterator.next());
//            try {
//                Thread.sleep(80);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
