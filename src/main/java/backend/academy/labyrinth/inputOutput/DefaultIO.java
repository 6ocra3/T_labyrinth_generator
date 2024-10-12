package backend.academy.labyrinth.inputOutput;

import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import backend.academy.labyrinth.visualizers.DefaultVisualizer;
import java.util.List;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class DefaultIO {
    protected Terminal terminal;
    public DefaultIO(){
        try {
            terminal = TerminalBuilder.builder()
                .system(true)
                .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void visualizeMaze(Maze maze){
        clearOutput();
        terminal.writer().println(DefaultVisualizer.visualizeMaze(maze));
        terminal.flush();
    }

    private void clearOutput() {
        terminal.writer().println("\033[H\033[2J");
    }



}
