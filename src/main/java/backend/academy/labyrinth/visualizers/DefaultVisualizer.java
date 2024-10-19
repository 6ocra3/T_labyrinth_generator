package backend.academy.labyrinth.visualizers;

import backend.academy.labyrinth.maze.Cell;

public class DefaultVisualizer extends AbstractVisualizer {

    static final String VISITED_CELL = " * ";

    public DefaultVisualizer() {

    }

    @Override
    public String getCellInMaze(Cell cell) {
        return cell.visited() ? VISITED_CELL : defaultCell;
    }
}
