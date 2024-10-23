package backend.academy.labyrinth.inputOutput;

import backend.academy.labyrinth.extraStructures.BaseObject;
import backend.academy.labyrinth.extraStructures.point.Point;
import backend.academy.labyrinth.maze.Maze;
import backend.academy.labyrinth.solvers.Solver;
import backend.academy.labyrinth.visualizers.AbstractVisualizer;
import backend.academy.labyrinth.visualizers.DefaultVisualizer;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class DefaultIO {
    @Getter
    protected Terminal terminal;
    @Setter
    AbstractVisualizer visualizer = new DefaultVisualizer();
    @Setter
    protected LineReader lineReader;
    static final int FRAME_DELAY = 200;

    public DefaultIO() {
        try {
            terminal = TerminalBuilder.builder()
                .system(true)
                .build();
            lineReader = LineReaderBuilder.builder().terminal(terminal).build();
        } catch (Exception e) {

        }
    }

    public void visualizeMaze(Maze maze) {
        clearOutput();
        terminal.writer().println(visualizer.visualizeMaze(maze));
        terminal.flush();
    }

    public void visualizeStepByStep(Solver solver, Maze maze) {
        Iterator<Maze> solvIterator = solver.getIterator(maze);
        while (solvIterator.hasNext()) {
            this.visualizeMaze(solvIterator.next());

            try {
                Thread.sleep(FRAME_DELAY);
            } catch (InterruptedException e) {

            }
        }
        Maze solvedMaze = solver.solve(maze);
        this.visualizeMaze(solvedMaze);
    }

    private void clearOutput() {
        terminal.writer().println("\033[H\033[2J");
    }

    public int chooseObjectByIndex(String title, List<BaseObject> objects) {
        List<String> points = objects.stream()
            .map(BaseObject::getShortInfo)
            .collect(Collectors.toList());
        return chooseObjectMeny(title, points);
    }

    public int chooseObjectMeny(String title, List<String> points) {
        terminal.writer().println(title);
        for (int i = 0; i < points.size(); i++) {
            String row = i + " " + (i == 0 ? "[default] " : "") + "— " + points.get(i);
            terminal.writer().println(row);
        }
        return getNumOrDefault(0, 0, points.size());
    }

    public int getSomeIntParams(String title, int defaultNum) {
        terminal.writer().println(title);
        return getNumOrDefault(defaultNum);
    }

    public int getNumOrDefault(int defaultNum) {
        return getNumOrDefault(defaultNum, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public int getNumOrDefault(int defaultNum, int min, int max) {
        int num;
        terminal.writer().print("(default - " + defaultNum + ") ");
        String input = lineReader.readLine();
        try {
            num = Integer.parseInt(input);
            if (num < min || num > max) {
                num = defaultNum;
            }
        } catch (NumberFormatException e) {
            num = defaultNum;
        }
        terminal.writer().println();
        return num;
    }

    public Point getSomePoint(String title, int defaultX, int minX, int maxX, int defaultY, int minY, int maxY) {
        terminal.writer().println(title);
        int x = getNumOrDefault(defaultX, minX, maxX);
        int y = getNumOrDefault(defaultY, minY, maxY);
        return new Point(y, x);
    }

}
