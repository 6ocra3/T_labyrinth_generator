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

    static final double BORDER_MULTIPLY = 0.2;
    static final int DEFAULT_WIDTH = 16;
    static final int DEFAULT_HEIGHT = 16;

    DefaultIO defaultIO = new DefaultIO();
    List<Generator> generatorsList = Arrays.asList(new HuntAndKillGenerator(), new KruskalGenerator());
    List<Solver> solversList = Arrays.asList(new BFSSolver(), new DFSSolver());
    List<Solver> weightedSolversList = List.of(new WeightedDijkstraSolver());
    List<Solver> solversForLabyrinthType = solversList;
    List<String> labyrinthTypes = Arrays.asList("Простой лабиринт", "Лабиринт с циклами и взвешенными ребрами");

    int width;
    int height;
    Maze maze;
    Generator generator;
    Solver solver;
    int labyrinthType;

    public Labyrinth() {

        getBaseParams();

        getGeneratorAndSolver();

        createMaze();

        if (labyrinthType == 1) {
            modifyMaze();
        }

        defaultIO.visualizeMaze(maze);

        defaultIO.visualizeStepByStep(solver, maze);

    }

    private void createMaze() {
        Point start = defaultIO.getSomePoint("Введите координаты старта", 0, 0, width, 0, 0, height);
        Point end = defaultIO.getSomePoint("Введите координаты конца", width - 1, 0, width, height - 1, 0, height);
        maze = new Maze(generator.generate(width, height), width, height, start, end);
    }

    private void modifyMaze() {
        defaultIO.terminal().writer().println("Выберите количество плохих территорий");
        int badSurfaces = defaultIO.getNumOrDefault((int) (width * height * BORDER_MULTIPLY), 0,
            (int) (width * height * (BORDER_MULTIPLY * 2)));
        defaultIO.terminal().writer().println("Выберите количество хороших территорий");
        int goodSurfaces = defaultIO.getNumOrDefault((int) (width * height * BORDER_MULTIPLY), 0,
            (int) (width * height * (BORDER_MULTIPLY * 2)));
        maze.modifyMaze((int) ((width * height) * BORDER_MULTIPLY), badSurfaces, goodSurfaces);
    }

    private void getGeneratorAndSolver() {
        int generatorInd = defaultIO.chooseObjectByIndex("Выберите генератор", new ArrayList<>(generatorsList));
        generator = generatorsList.get(generatorInd);

        int solverInd =
            defaultIO.chooseObjectByIndex("Выберите алгоритм решения", new ArrayList<>(solversForLabyrinthType));
        solver = solversForLabyrinthType.get(solverInd);
    }

    private void getBaseParams() {
        labyrinthType = defaultIO.chooseObjectMeny("Выберите тип лабиринта", labyrinthTypes);

        width = defaultIO.getSomeIntParams("Введите ширину лабиринта", DEFAULT_WIDTH);
        height = defaultIO.getSomeIntParams("Введите высоту лабиринта", DEFAULT_HEIGHT);

        if (labyrinthType == 1) {
            solversForLabyrinthType = weightedSolversList;
            WeightedVisualizer weightedVisualizer = new WeightedVisualizer();
            defaultIO.visualizer(weightedVisualizer);
        }
    }
}
