package backend.academy.labyrinth.visualizers;

import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import java.util.List;

public class WeightedVisualizer extends AbstractVisualizer {

    public WeightedVisualizer() {
        this.CORNER = "○";
        this.TOP_BOT = "─────";
        this.EMPTY_TOP_BOT = "     ";
        this.DEFAULT_CELL = "     ";
        this.START_CELL = "  A  ";
        this.END_CELL = "  B  ";
    }

    @Override
    public String getCellInMaze(Cell cell) {
        return String.format("%d %" + (DEFAULT_CELL.length()-2) + "d",cell.getSurfaceModifier(), cell.pathCost());
    }
}
