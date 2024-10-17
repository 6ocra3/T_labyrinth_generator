package backend.academy.labyrinth.inputOutput;

import backend.academy.labyrinth.extraStructures.BaseObject;
import backend.academy.labyrinth.extraStructures.point.Point;
import backend.academy.labyrinth.maze.Maze;
import backend.academy.labyrinth.solvers.Solver;
import backend.academy.labyrinth.visualizers.DefaultVisualizer;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.Setter;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class DefaultIO {
    protected Terminal terminal;
    @Setter
    protected LineReader lineReader;
    public DefaultIO(){
        try {
            terminal = TerminalBuilder.builder()
                .system(true)
                .build();
            lineReader = LineReaderBuilder.builder().terminal(terminal).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void visualizeMaze(Maze maze){
        clearOutput();
        terminal.writer().println(DefaultVisualizer.visualizeMaze(maze));
        terminal.flush();
    }

    public void visualizeStepByStep(Solver solver, Maze maze, AtomicBoolean interrupted){
        Iterator<Maze> solvIterator = solver.getIterator(maze);
        while (solvIterator.hasNext()){
            if(interrupted.get()){
                break;
            }
            this.visualizeMaze(solvIterator.next());
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Maze solvedMaze = solver.solve(maze);
        this.visualizeMaze(solvedMaze);
    }

    private void clearOutput() {
        terminal.writer().println("\033[H\033[2J");
    }

    public int chooseObjectByIndex(String title, List<BaseObject> objects){
        terminal.writer().println(title);
        for(int i = 0; i<objects.size();i++){
            String row = i + " " + (i == 0 ? "[default] ": "") + "— " + objects.get(i).getShortInfo();
            terminal.writer().println(row);
        }
        return getNumOrDefault(0,0, objects.size());
    }

    public int getSomeIntParams(String title, int defaultNum){
        terminal.writer().println(title);
        return getNumOrDefault(defaultNum);
    }

    public int getNumOrDefault(int defaultNum) {
        return getNumOrDefault(defaultNum, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public int getNumOrDefault(int defaultNum, int min, int max){
        int num;
        String input = lineReader.readLine();
        try {
            num = Integer.parseInt(input);
            if (num < min || num > max) {
                num = defaultNum;
            }
        } catch (NumberFormatException e) {
            num = defaultNum;
        }
        return num;
    }

    public Point getSomePoint(String title, int defaultX, int minX, int maxX, int defaultY, int minY, int maxY){
        terminal.writer().println(title);
        int X = getNumOrDefault(defaultX, minX, maxX);
        int Y = getNumOrDefault(defaultY, minY, maxY);
        return  new Point(Y, X);
    }




}
