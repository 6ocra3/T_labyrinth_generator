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
import backend.academy.labyrinth.solvers.WeightedDijkstraSolver;
import backend.academy.labyrinth.visualizers.WeightedVisualizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Labyrinth {

    static final int DEFAULT_WIDTH = 16;
    static final int DEFAULT_HEIGHT = 16;

    List<Generator> generatorsList = Arrays.asList(new HuntAndKillGenerator(), new KruskalGenerator());
    List<Solver> solversList = Arrays.asList(new BFSSolver(), new DFSSolver());

    public Labyrinth() {
        DefaultIO defaultIO = new DefaultIO();
        WeightedVisualizer weightedVisualizer = new WeightedVisualizer();
        defaultIO.visualizer(weightedVisualizer);

        int width = defaultIO.getSomeIntParams("Введите ширину лабиринта", DEFAULT_WIDTH);
        int height = defaultIO.getSomeIntParams("Введите высоту лабиринта", DEFAULT_HEIGHT);

        int generatorInd = defaultIO.chooseObjectByIndex("Выберите генератор", new ArrayList<>(generatorsList));
        Generator generator = generatorsList.get(generatorInd);

        int solverInd = defaultIO.chooseObjectByIndex("Выберите алгоритм решения", new ArrayList<>(solversList));
        Solver solver = solversList.get(solverInd);

        Point start = defaultIO.getSomePoint("Введите координаты старта", 0, 0, width, 0, 0, height);
        Point end = defaultIO.getSomePoint("Введите координаты конца", width - 1, 0, width, height - 1, 0, height);
        Maze maze = new Maze(generator.generate(width, height), width, height, start, end);

        maze.modifyMaze((int)((width*height) * 0.08), 4, 4);
        defaultIO.visualizeMaze(maze);

        WeightedDijkstraSolver weightedSolver = new WeightedDijkstraSolver();
        defaultIO.visualizeMaze(weightedSolver.solve(maze));

//        AtomicBoolean interrupted = new AtomicBoolean(false);

//        AtomicBoolean interrupted = new AtomicBoolean(false);
//
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            // Код для обработки прерывания (например, установка значения AtomicBoolean)
//            interrupted.set(true);
//        }));
//
//        defaultIO.visualizeStepByStep(solver, maze, interrupted, false);

    }
}
